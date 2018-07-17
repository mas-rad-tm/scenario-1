package ch.globaz.tmmas.rentesservice.domain.model.dossier;

import ch.globaz.tmmas.rentesservice.domain.common.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;


@ToString
@EqualsAndHashCode
@Getter
public class Dossier implements Entity<Dossier> {


    private DossierId identifiant;
    private LocalDate dateEnregistrement;
    private LocalDate dateValidation;
    private LocalDate dateCloture;
    private Long requerantId;
    private DossierStatus status;


    public DossierId identifiant() {
        return identifiant;
    }

    public Long id() {
        return id;
    }

    public Long requerantId () {
        return requerantId;
    }

    public LocalDate dateEnregistrement() {
        return dateEnregistrement;
    }

    public DossierStatus status () {
        return status;
    }

    public Dossier(LocalDate dateEnregistrement, Long requerantId){
        this.requerantId = requerantId;
        this.dateEnregistrement = dateEnregistrement;
        this.identifiant = DossierId.aleatoire();
        this.status = DossierStatus.INITIE;
    }

    public static Dossier builder(Long requerantId, LocalDate dateEnregistrement) {
        return new Dossier(dateEnregistrement,requerantId);
    }

    public static Dossier builder(LocalDate dateEnregistrement, Long requerantId) {
        return new Dossier(dateEnregistrement,requerantId);
    }

    public Dossier validerDossier(LocalDate dateValidation){

        this.status = DossierStatus.VALIDE;
        this.dateValidation = dateValidation;
        return this;

    }

    public Dossier cloreDossier(LocalDate dateCloture){
        this.status = DossierStatus.CLOT;
        this.dateCloture = dateCloture;
        return this;
    }



    //hibernate
    private Long id;

    Dossier() {}



    @Override
    public boolean sameIdentityAs(Dossier dossier) {
        return this.identifiant.equals(dossier.identifiant());
    }
}
