package ch.globaz.tmmas.rentesservice.service;


import ch.globaz.tmmas.rentesservice.api.web.resources.DossierResource;
import ch.globaz.tmmas.rentesservice.command.CloreDossierCommand;
import ch.globaz.tmmas.rentesservice.command.CreerDossierCommand;
import ch.globaz.tmmas.rentesservice.command.ValiderDossierCommand;


import java.util.List;
import java.util.Optional;

public interface DossierService {


	List<DossierResource> getAll();

    Optional<DossierResource> getById(Long id);

	DossierResource creerDossier(CreerDossierCommand command);

	Optional<DossierResource> validerDossier(ValiderDossierCommand command, Long dossierId);

	Optional<DossierResource> cloreDossier(CloreDossierCommand command, Long dossierId);

}
