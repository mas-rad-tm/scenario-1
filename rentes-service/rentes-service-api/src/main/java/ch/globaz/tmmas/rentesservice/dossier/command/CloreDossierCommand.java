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
public class CloreDossierCommand implements DomainCommand, ValueObject<DomainCommand>{

	@NotNull
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateCloture;

	public CloreDossierCommand(){}


	@Override
	public boolean sameValueAs(DomainCommand other) {
		return false;
	}
}
