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


/**
	public static DossierDto fromEntity(Dossier dossier){

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		return new DossierDto(dossier.id(),
				dossier.identifiant().identifiant(), dossier.requerantId(),
				dossier.dateEnregistrement().format(formatter), dossier.status());
	}
 */

}
