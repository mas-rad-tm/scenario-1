package ch.globaz.tmmas.rentesservice.domain.reglesmetiers;

import ch.globaz.tmmas.rentesservice.domain.common.specification.AbstractSpecification;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DateValidationPlusRecenteDateEnregistrement extends AbstractSpecification<Dossier> {


    private LocalDate dateValidation;

    public DateValidationPlusRecenteDateEnregistrement(LocalDate dateValidation) {
         this.dateValidation = dateValidation;
    }

    @Override
    public boolean isSatisfiedBy(Dossier dossier) {

        return (dossier.dateEnregistrement().isBefore(dateValidation));

    }
}
