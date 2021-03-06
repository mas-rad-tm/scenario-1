package ch.globaz.tmmas.rentesservice.domaine.model;

import ch.globaz.tmmas.rentesservice.domaine.model.dossier.Dossier;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class DossierRepositoryTest {

	@Test
	public void assertIfBuilderworkCorrectly () {

		Dossier dossier = Dossier.builder(1L,LocalDate.now());
		assertThat(dossier).isNotNull();
		assertThat(dossier.identifiant()).isNotNull();
		assertThat(dossier.dateEnregistrement()).isNotNull();
	}

}