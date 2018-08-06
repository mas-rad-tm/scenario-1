package ch.globaz.tmmas.rentesservice.api.dossier.command;


import ch.globaz.tmmas.rentesservice.api.dossier.web.resources.localdate.LocalDateSerializer;
import ch.globaz.tmmas.rentesservice.domain.common.ValueObject;
import ch.globaz.tmmas.rentesservice.domain.common.localdate.LocalDateDeserializer;
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
public class CreerDossierCommand implements DomainCommand,ValueObject<CreerDossierCommand> {


	@NotNull
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateEnregistrement;
	@NotNull
	private Long requerantId;

	CreerDossierCommand () {}


	@Override
	public boolean sameValueAs(CreerDossierCommand other) {
		return this.equals(other);
	}
}
