package ch.globaz.tmmas.rentesservice.application.service;


import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.TraiterDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.model.Dossier;
import ch.globaz.tmmas.rentesservice.infrastructure.dto.DossierDto;

import java.util.List;
import java.util.Optional;

public interface DossierService {

	Dossier sauve(Dossier dossier);

	List<DossierDto> getAll();

    Optional<DossierDto> getById(Long id);

	DossierDto creerDossier(CreerDossierCommand command);

	DossierDto traiterDossier(TraiterDossierCommand command,  Long dossierId);
}
