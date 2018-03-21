package ch.globaz.tmmas.rentesservice.domain.command;


import lombok.Getter;


import java.time.LocalDate;


@Getter
public class CreerDossierCommand implements DomainCommand {


	private LocalDate dateEnregistrement;
	private Long requerantId;

	CreerDossierCommand () {}

}
