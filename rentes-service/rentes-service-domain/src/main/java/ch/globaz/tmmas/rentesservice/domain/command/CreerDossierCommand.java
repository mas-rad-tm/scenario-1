package ch.globaz.tmmas.rentesservice.domain.command;


import ch.globaz.tmmas.rentesservice.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@ToString
public class CreerDossierCommand implements DomainCommand,ValueObject<CreerDossierCommand> {


	private LocalDate dateEnregistrement;
	private Long requerantId;

	CreerDossierCommand () {}


	@Override
	public boolean sameValueAs(CreerDossierCommand other) {
		return this.equals(other);
	}
}
