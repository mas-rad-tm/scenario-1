package ch.globaz.tmmas.rentesservice.infrastructure.repository;


import ch.globaz.tmmas.rentesservice.domaine.model.dossier.Dossier;
import ch.globaz.tmmas.rentesservice.domaine.repository.DossierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class DossierHibernateRepository extends HibernateRepository implements DossierRepository {


	@Autowired
	ObjectMapper mapper;


	private static final Logger LOGGER = LoggerFactory.getLogger(DossierHibernateRepository.class);


	@Override
	public Dossier initieDossier(Dossier dossier) {

		LOGGER.debug("initieDossier (): {}", dossier);

		getSession().saveOrUpdate(dossier);

		return dossier;
	}


	@Override
	public List<Dossier> allDossiers() {
		return getSession().createQuery("FROM " + Dossier.class.getSimpleName()).list();
	}


	@Override
	public Optional<Dossier> dossierById(Long dossierId) {

		LOGGER.debug("{}#getBiyId, dossierId:{}",this.getClass().getName(),dossierId);

		Optional<Dossier> dossier = Optional.ofNullable(getSession().get(Dossier.class,dossierId));

		return dossier;
	}


	@Override
	public Dossier validerDossier(Dossier dossier) {

		getSession().saveOrUpdate(dossier);

		return dossier;
	}

	@Override
	public Dossier cloreDossier(Dossier dossier) {

		getSession().saveOrUpdate(dossier);

		return dossier;
	}

}
