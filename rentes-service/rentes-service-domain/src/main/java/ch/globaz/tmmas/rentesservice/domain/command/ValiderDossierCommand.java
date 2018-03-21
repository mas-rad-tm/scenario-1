package ch.globaz.tmmas.rentesservice.domain.command;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ValiderDossierCommand implements DomainCommand{

	private LocalDate dateValidation;

	public ValiderDossierCommand(){}
}
