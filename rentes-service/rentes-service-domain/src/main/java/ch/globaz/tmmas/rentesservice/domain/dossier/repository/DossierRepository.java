package ch.globaz.tmmas.rentesservice.domain.dossier.repository;

import ch.globaz.tmmas.rentesservice.domain.dossier.model.Dossier;

import java.util.List;
import java.util.Optional;

public interface DossierRepository {

	Dossier initieDossier(Dossier dossier);

	List<Dossier> allDossiers();

    Optional<Dossier> dossierById(Long dossierId);

	Dossier validerDossier(Dossier dossier);

	Dossier cloreDossier(Dossier dossier);
}
