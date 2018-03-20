package ch.globaz.tmmas.rentesservice.application.api.web;


import ch.globaz.tmmas.rentesservice.application.api.web.dto.CreerDossierDto;
import ch.globaz.tmmas.rentesservice.application.event.InternalCommandPublisher;
import ch.globaz.tmmas.rentesservice.application.service.DossierService;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.model.Dossier;
import ch.globaz.tmmas.rentesservice.infrastructure.dto.DossierDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.UriTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/dossiers")
public class DossiersController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DossiersController.class);

	private static final String DOSSIERS = "/dossiers";
	private static final String DOSSIER = DOSSIERS + "/{id}";

	@Autowired
	DossierService dossierService;

	@Autowired
	InternalCommandPublisher commandPublisher;




	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity creerDossier(@RequestBody CreerDossierDto dto){

		LOGGER.info("creerDossier(), {}",dto);

		CreerDossierCommand command = new CreerDossierCommand(dto.getDateEnregistrement(),dto.getRequerantId());

		commandPublisher.publishCommand(command);

		DossierDto dossier = dossierService.creerDossier(command);

		dossier.add(linkTo(
				methodOn(DossiersController.class).dossierById(dossier.getTechnicalId()))
				.withSelfRel());

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(new UriTemplate(DOSSIER).expand(dossier.getTechnicalId()));

		return new ResponseEntity<>(dossier, headers, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<?>> getAllDossier(@RequestParam(value = "etendu", required = false)
			                                            String etendu){
		Boolean dossierEtendu = Boolean.valueOf(etendu);

		List<Dossier> dossiers = dossierService.getAll();

		return new ResponseEntity<>(getAllAsDto(dossiers), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{dossierId}")
	public ResponseEntity dossierById(@PathVariable Long dossierId){

		LOGGER.debug("dossierById(), {}",dossierId);

		Optional<DossierDto> dossier = dossierService.getById(dossierId);

		if(dossier.isPresent()){

			DossierDto dto = dossier.get();

			dto.add(linkTo(methodOn(
					DossiersController.class).dossierById(dto.getTechnicalId()))
					.withSelfRel());

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(new UriTemplate(DOSSIER).expand(dto.getTechnicalId()));


			LOGGER.debug("getDossierById() return  {}",dto);

			return new ResponseEntity<>(dto, HttpStatus.OK);
		}else{

			return new ResponseEntity<>("No entity found with id " + dossier, HttpStatus.NOT_FOUND);
		}

	}

	private List<DossierDto> getAllAsDto (List<Dossier> dossierList) {
		return dossierList.stream().map(DossierDto::fromEntity).collect(Collectors.toList());
	}


}
