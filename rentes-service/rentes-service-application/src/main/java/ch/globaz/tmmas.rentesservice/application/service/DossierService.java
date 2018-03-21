package ch.globaz.tmmas.rentesservice.application.service;


import ch.globaz.tmmas.rentesservice.application.api.web.resources.DossierResource;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.command.ValiderDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;


import java.util.List;
import java.util.Optional;

public interface DossierService {

	Dossier sauve(Dossier dossier);

	List<DossierResource> getAll();

    Optional<DossierResource> getById(Long id);

	DossierResource creerDossier(CreerDossierCommand command);

	Optional<DossierResource> validerDossier(ValiderDossierCommand command, Long dossierId);
}
