package ch.globaz.tmmas.rentesservice.domain.command;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@ToString
public class CreerDossierCommand implements DomainCommand {


	private LocalDate dateEnregistrement;
	private Long requerantId;

	CreerDossierCommand () {}

}
