package ch.globaz.tmmas.rentesservice.application.api.web.resources;

import ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate.LocalDateDeserializer;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate.LocalDateSerializer;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.DossierStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate.DateFormatter.DATE_FORMAT;

@Getter
public class DossierResource extends ResourceSupport{

	private String identifiant;
	private Long requerantId;

	@JsonProperty("id")
	private Long technicalId;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateEnregistrement;

	private DossierStatus status;


	public DossierResource(){}


	private DossierResource(Long id, String numero, Long requerantId, String dateEnregistrement, DossierStatus status){

		this.identifiant = numero;
		this.requerantId = requerantId;
		this.technicalId = id;
		this.status = status;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

		this.dateEnregistrement = LocalDate.parse(dateEnregistrement,formatter);
	}



	public static DossierResource fromEntity(Dossier dossier){

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		return new DossierResource(dossier.id(),
				dossier.identifiant().identifiant(), dossier.requerantId(),
				dossier.dateEnregistrement().format(formatter), dossier.status());
	}
}
