package ch.globaz.tmmas.rentesservice.application.command;


import ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate.LocalDateDeserializer;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate.LocalDateSerializer;
import ch.globaz.tmmas.rentesservice.domain.common.ValueObject;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@ToString
public class CreerDossierCommand implements DomainCommand {


	@NotNull
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateEnregistrement;
	@NotNull
	private Long requerantId;

	CreerDossierCommand () {}


}
