package ch.globaz.tmmas.rentesservice.application.service;


import ch.globaz.tmmas.rentesservice.application.api.web.resources.DossierResource;
import ch.globaz.tmmas.rentesservice.application.command.CloreDossierCommand;
import ch.globaz.tmmas.rentesservice.application.command.CreerDossierCommand;
import ch.globaz.tmmas.rentesservice.application.command.ValiderDossierCommand;


import java.util.List;
import java.util.Optional;

public interface DossierService {


	List<DossierResource> getAll();

    Optional<DossierResource> getById(Long id);

	DossierResource creerDossier(CreerDossierCommand command);

	Optional<DossierResource> validerDossier(ValiderDossierCommand command, Long dossierId);

	Optional<DossierResource> cloreDossier(CloreDossierCommand command, Long dossierId);

}
