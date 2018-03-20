package ch.globaz.tmmas.rentesservice.application.api.web;


import ch.globaz.tmmas.rentesservice.application.api.web.dto.CreerDossierDto;
import ch.globaz.tmmas.rentesservice.application.api.web.dto.ValiderDossierDto;
import ch.globaz.tmmas.rentesservice.application.event.InternalCommandPublisher;
import ch.globaz.tmmas.rentesservice.application.service.DossierService;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.ValiderDossierCommand;
import ch.globaz.tmmas.rentesservice.infrastructure.dto.DossierDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.UriTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

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

		putSelfLink(dossier);

		return new ResponseEntity<>(dossier, putLocationHeader(dossier), HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{dossierId}/valider", method = RequestMethod.PUT)
	public ResponseEntity validerDossier(@PathVariable Long dossierId, @RequestBody ValiderDossierDto dto){

		LOGGER.info("validerDossier(), {}",dto);

		ValiderDossierCommand command = new ValiderDossierCommand(dto.getDateTraitement());

		commandPublisher.publishCommand(command);

		Optional<DossierDto> optionnalDossier = dossierService.validerDossier(command,dossierId);

		if(optionnalDossier.isPresent()){

			DossierDto dossierDto = optionnalDossier.get();
			putSelfLink(dossierDto);

			return new ResponseEntity<>(optionnalDossier,  HttpStatus.OK);

		}

		return new ResponseEntity<>("No entity found with id " + dossierId, HttpStatus.NOT_FOUND);

	}



	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DossierDto>> allDossiers(){

		List<DossierDto> dossiersDto = dossierService.getAll();

		dossiersDto.stream().forEach(dossierDto -> {
			putSelfLink(dossierDto);
		});

		return new ResponseEntity<>(dossiersDto, HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.GET, value = "/{dossierId}")
	public ResponseEntity dossierById(@PathVariable Long dossierId){

		LOGGER.debug("dossierById(), {}",dossierId);

		Optional<DossierDto> dossier = dossierService.getById(dossierId);

		if(dossier.isPresent()){

			DossierDto dto = dossier.get();

			putSelfLink(dto);

			LOGGER.debug("getDossierById() return  {}",dto);

			return new ResponseEntity<>(dto, HttpStatus.OK);
		}


		return new ResponseEntity<>("No entity found with id " + dossierId, HttpStatus.NOT_FOUND);


	}

	private DossierDto putSelfLink(DossierDto dossierDto) {

		dossierDto.add(linkTo(methodOn(
					DossiersController.class).dossierById(dossierDto.getTechnicalId()))
					.withSelfRel());


		return dossierDto;
	}

	private HttpHeaders putLocationHeader(DossierDto dossier) {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(new UriTemplate(DOSSIER).expand(dossier.getTechnicalId()));
		return headers;
	}


}
