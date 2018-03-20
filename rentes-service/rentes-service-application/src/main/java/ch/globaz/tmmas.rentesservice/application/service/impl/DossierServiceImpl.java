package ch.globaz.tmmas.rentesservice.application.service.impl;

import ch.globaz.tmmas.rentesservice.application.service.DossierService;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.ValiderDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.model.Dossier;
import ch.globaz.tmmas.rentesservice.domain.repository.DossierRepository;
import ch.globaz.tmmas.rentesservice.infrastructure.dto.DossierDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DossierServiceImpl implements DossierService {

	@Autowired
	DossierRepository repository;

	@Override
	public Dossier sauve(Dossier dossier) {

		return repository.initieDossier(dossier);
	}

	@Override
	public List<DossierDto> getAll() {
		List<Dossier> dossiers =  repository.getAll();

		return dossiers.stream().map(dossier -> {
			return DossierDto.fromEntity(dossier);
		}).collect(Collectors.toList());
	}

	@Override
	public Optional<DossierDto> getById(Long id) {

		Optional<Dossier> dossier = repository.getById(id);

		if(dossier.isPresent()){
			DossierDto dto = DossierDto.fromEntity(dossier.get());
			return Optional.of(dto);
		}else{
			return Optional.ofNullable(null);
		}

	}

	@Override
	public DossierDto creerDossier(CreerDossierCommand command) {

		Dossier dossier = Dossier.builder(command);

		dossier =  repository.initieDossier(dossier);

		return DossierDto.fromEntity(dossier);

	}

	@Override
	public Optional<DossierDto> validerDossier(ValiderDossierCommand command, Long dossierId) {

		Optional<Dossier> optionnalDossier = repository.getById(dossierId);

		if(optionnalDossier.isPresent()){
			Dossier dossier = optionnalDossier.get();
			dossier.traiterDossier(command.getDateValidation());

			repository.validerDossier(dossier);

			DossierDto dto = DossierDto.fromEntity(dossier);
			return Optional.of(dto);
		}else{
			return Optional.ofNullable(null);
		}

	}


}
