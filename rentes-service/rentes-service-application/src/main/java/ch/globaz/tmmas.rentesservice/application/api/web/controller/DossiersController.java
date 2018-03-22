package ch.globaz.tmmas.rentesservice.application.api.web.controller;


import ch.globaz.tmmas.rentesservice.application.api.web.resources.ApiError;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.DossierResource;
import ch.globaz.tmmas.rentesservice.application.event.InternalCommandPublisher;
import ch.globaz.tmmas.rentesservice.application.service.DossierService;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.ValiderDossierCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.hateoas.UriTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity creerDossier(@Valid @RequestBody CreerDossierCommand command){

		LOGGER.info("creerDossier(), command= {}",command);

		commandPublisher.publishCommand(command);

		DossierResource dossierResource = dossierService.creerDossier(command);

		putSelfLink(dossierResource);

		return new ResponseEntity<>(dossierResource, putLocationHeader(dossierResource), HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{dossierId}/valider", method = RequestMethod.PUT)
	public ResponseEntity validerDossier(@PathVariable Long dossierId,@Valid @RequestBody ValiderDossierCommand
			validerDossierCommand){

		LOGGER.info("validerDossier(), command={}",validerDossierCommand);

		commandPublisher.publishCommand(validerDossierCommand);

		Optional<DossierResource> optionnalDossier = dossierService.validerDossier(validerDossierCommand,dossierId);

		if(optionnalDossier.isPresent()){

			DossierResource dossierResource = optionnalDossier.get();
			putSelfLink(dossierResource);

			return new ResponseEntity<>(dossierResource,  HttpStatus.OK);

		}

		return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND,"No entity found with id " +
				dossierId), HttpStatus.NOT_FOUND);

	}



	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DossierResource>> allDossiers(){

		List<DossierResource> dossiersResource = dossierService.getAll();

		dossiersResource.stream().forEach(resource -> {
			putSelfLink(resource);
		});

		return new ResponseEntity<>(dossiersResource, HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.GET, value = "/{dossierId}")
	public ResponseEntity dossierById(@PathVariable Long dossierId){

		LOGGER.debug("dossierById(), {}",dossierId);

		Optional<DossierResource> optionnalDossier = dossierService.getById(dossierId);

		if(optionnalDossier.isPresent()){

			DossierResource dossierResource = optionnalDossier.get();

			putSelfLink(dossierResource);

			LOGGER.debug("getDossierById() return  {}",dossierResource);

			return new ResponseEntity<>(dossierResource, HttpStatus.OK);
		}


		return new ResponseEntity<>("No entity found with id " + dossierId, HttpStatus.NOT_FOUND);


	}

	private DossierResource putSelfLink(DossierResource dossierResource) {

		dossierResource.add(linkTo(methodOn(
					DossiersController.class).dossierById(dossierResource.getTechnicalId()))
					.withSelfRel());

		dossierResource.add(linkTo(methodOn(
				DossiersController.class).validerDossier(dossierResource.getTechnicalId(),null))
				.withRel("valider"));


		return dossierResource;
	}

	private HttpHeaders putLocationHeader(DossierResource dossierResource) {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(new UriTemplate(DOSSIER).expand(dossierResource.getTechnicalId()));
		return headers;
	}


}
