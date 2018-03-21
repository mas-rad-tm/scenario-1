package ch.globaz.tmmas.rentesservice.domain.command;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@ToString
public class ValiderDossierCommand implements DomainCommand{

	private LocalDate dateValidation;

	public ValiderDossierCommand(){}
}
