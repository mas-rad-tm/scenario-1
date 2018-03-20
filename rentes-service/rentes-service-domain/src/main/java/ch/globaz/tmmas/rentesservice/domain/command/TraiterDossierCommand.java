package ch.globaz.tmmas.rentesservice.domain.command;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TraiterDossierCommand implements DomainCommand{

	private LocalDate dateTraitement;

	public TraiterDossierCommand(LocalDate dateTraitement){
		this.dateTraitement = dateTraitement;

	}
}
