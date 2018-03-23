package ch.globaz.tmmas.rentesservice.domain.model.dossier;

import ch.globaz.tmmas.rentesservice.domain.common.Entity;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.common.specification.Specification;
import ch.globaz.tmmas.rentesservice.domain.reglesmetiers.DateValidationPlusRecenteDateEnregistrement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ToString
@EqualsAndHashCode
@Getter
public class Dossier implements Entity<Dossier> {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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

    public static Dossier builder(CreerDossierCommand command) {
        return new Dossier(command.getDateEnregistrement(),command.getRequerantId());
    }

    public Dossier validerDossier(DateValidationPlusRecenteDateEnregistrement specification){

        if(specification.isSatisfiedBy(this)){
            this.status = DossierStatus.VALIDE;
            this.dateValidation = specification.getDateValidation();
        }

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

    public String getDateEnregistrementAsString() {
        return this.dateEnregistrement.format(formatter);
    }

    @Override
    public boolean sameIdentityAs(Dossier dossier) {
        return this.identifiant.equals(dossier.identifiant());
    }
}
