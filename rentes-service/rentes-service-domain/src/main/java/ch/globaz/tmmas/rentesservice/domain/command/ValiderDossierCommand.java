package ch.globaz.tmmas.rentesservice.domain.command;

import ch.globaz.tmmas.rentesservice.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@ToString
public class ValiderDossierCommand implements DomainCommand,ValueObject<ValiderDossierCommand>{

	private LocalDate dateValidation;

	public ValiderDossierCommand(){}


	@Override
	public boolean sameValueAs(ValiderDossierCommand other) {
		return this.equals(other);
	}
}
