package ch.globaz.tmmas.rentesservice.application.service.impl;

import ch.globaz.tmmas.rentesservice.application.api.web.resources.DossierResource;
import ch.globaz.tmmas.rentesservice.application.event.impl.DomainEventPublisher;
import ch.globaz.tmmas.rentesservice.application.service.DossierService;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.ValiderDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.event.DossierCreeEvent;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import ch.globaz.tmmas.rentesservice.domain.repository.DossierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DossierServiceImpl implements DossierService {

	@Autowired
	DossierRepository repository;

	@Autowired
	DomainEventPublisher eventPublisher;

	@Override
	public Dossier sauve(Dossier dossier) {

		return repository.initieDossier(dossier);
	}

	@Override
	public List<DossierResource> getAll() {
		List<Dossier> dossiers =  repository.getAll();

		return dossiers.stream().map(dossier -> {
			return DossierResource.fromEntity(dossier);
		}).collect(Collectors.toList());
	}

	@Override
	public Optional<DossierResource> getById(Long id) {

		Optional<Dossier> dossier = repository.getById(id);

		if(dossier.isPresent()){
			DossierResource dto = DossierResource.fromEntity(dossier.get());
			return Optional.of(dto);
		}else{
			return Optional.ofNullable(null);
		}

	}

	@Override
	public DossierResource creerDossier(CreerDossierCommand command) {

		Dossier dossier = Dossier.builder(command);

		dossier =  repository.initieDossier(dossier);

		eventPublisher.publishEvent(DossierCreeEvent.fromEntity(dossier));

		return DossierResource.fromEntity(dossier);

	}

	@Override
	public Optional<DossierResource> validerDossier(ValiderDossierCommand command, Long dossierId) {

		Optional<Dossier> optionnalDossier = repository.getById(dossierId);

		if(optionnalDossier.isPresent()){
			Dossier dossier = optionnalDossier.get();
			dossier.traiterDossier(command.getDateValidation());

			repository.validerDossier(dossier);

			DossierResource dto = DossierResource.fromEntity(dossier);
			return Optional.of(dto);
		}else{
			return Optional.ofNullable(null);
		}

	}


}
