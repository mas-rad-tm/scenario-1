package ch.globaz.tmmas.rentesservice.domain.repository;

import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;

import java.util.List;
import java.util.Optional;

public interface DossierRepository {

	Dossier initieDossier(Dossier dossier);


	List<Dossier> getAll();


    Optional<Dossier> getById(Long dossierId);

	Dossier validerDossier(Dossier dossier);
}
