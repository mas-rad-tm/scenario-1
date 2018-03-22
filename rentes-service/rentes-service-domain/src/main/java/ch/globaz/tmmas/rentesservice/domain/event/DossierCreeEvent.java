package ch.globaz.tmmas.rentesservice.domain.event;

import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;



@EqualsAndHashCode
@ToString
@Getter
public class DossierCreeEvent implements DomainEvent {

    private String identifiant;
    private String dateEnregistrement;
    private Long requerantId;
    private String status;
    private Long id;

    public DossierCreeEvent(Long id,String identifiant, String dateEnregistrement, Long requerantId, String status) {
        this.identifiant = identifiant;
        this.dateEnregistrement = dateEnregistrement;
        this.requerantId = requerantId;
        this.status = status;
        this.id = id;
    }

    public DossierCreeEvent(){}



    public static DossierCreeEvent fromEntity(Dossier dossier) {
        return new DossierCreeEvent(dossier.getId(),
                dossier.getIdentifiant().identifiant(),dossier
                .getDateEnregistrementAsString(),
                dossier
                .requerantId(),
                dossier.status().toString());
    }

}
