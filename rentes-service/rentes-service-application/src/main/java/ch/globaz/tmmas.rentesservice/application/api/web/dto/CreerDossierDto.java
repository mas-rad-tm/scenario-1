package ch.globaz.tmmas.rentesservice.application.api.web.dto;


import ch.globaz.tmmas.rentesservice.application.api.web.dto.localdate.LocalDateDeserializer;
import ch.globaz.tmmas.rentesservice.application.api.web.dto.localdate.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class CreerDossierDto {

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateEnregistrement;

	private Long requerantId;

	public CreerDossierDto(){}
}
