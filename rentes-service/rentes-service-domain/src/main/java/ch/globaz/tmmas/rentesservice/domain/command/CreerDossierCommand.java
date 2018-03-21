package ch.globaz.tmmas.rentesservice.domain.command;


import lombok.Getter;


import java.time.LocalDate;


@Getter
public class CreerDossierCommand implements DomainCommand {


	private LocalDate dateEnregistrement;
	private Long requerantId;


	public CreerDossierCommand(LocalDate dateEnregistrement, Long requerantId){
		this.dateEnregistrement = dateEnregistrement;
		this.requerantId = requerantId;
	}

}
