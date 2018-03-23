package ch.globaz.tmmas.rentesservice.domain.reglesmetiers;

import ch.globaz.tmmas.rentesservice.domain.common.specification.AbstractSpecification;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DateValidationPlusRecenteDateEnregistrement extends AbstractSpecification<Dossier> {


    private Dossier dossier;
    private LocalDate dateValidation;

    public DateValidationPlusRecenteDateEnregistrement(Dossier dossier, LocalDate dateValidation) {
        this.dossier = dossier;
        this.dateValidation = dateValidation;
    }

    @Override
    public boolean isSatisfiedBy(Dossier dossier) {

        return (dossier.dateEnregistrement().isAfter(dateValidation));

    }
}
