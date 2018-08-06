package ch.globaz.tmmas.rentesservice.dossier.command;

import ch.globaz.tmmas.rentesservice.dossier.api.web.resources.localdate.LocalDateSerializer;
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
public class ValiderDossierCommand implements DomainCommand,ValueObject<ValiderDossierCommand>{

	@NotNull
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateValidation;

	public ValiderDossierCommand(){}

	@Override
	public boolean sameValueAs(ValiderDossierCommand other) {
		return this.equals(other);
	}
}
