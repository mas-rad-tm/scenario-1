package ch.globaz.tmmas.rentesservice.application.service.impl;

import ch.globaz.tmmas.rentesservice.domaine.common.specification.Specification;

import java.util.List;

//@ResponseStatus(value= HttpStatus.CONFLICT, reason="No such Order")
public class RegleMetiersNonSatisfaite extends RuntimeException {

    private List<String> reglesMetiersNonStaisfaite;

    public RegleMetiersNonSatisfaite(Specification specification) {
        super("Regle(s) métiers non staisfaite(s)");
        reglesMetiersNonStaisfaite = specification.getDescriptionReglesMetier();    }

    public List<String> getReglesMetiersNonStaisfaite() {
        return reglesMetiersNonStaisfaite;
    }
}
