package ch.globaz.tmmas.rentesservice.api.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//@ResponseStatus(value= HttpStatus.CONFLICT, reason="No such Order")
public class RegleMetiersNonSatisfaite extends RuntimeException {

    private List<String> reglesMetiersNonStaisfaite;

    public RegleMetiersNonSatisfaite(List<String> reglesMetiers) {
        super("Regle(s) métiers non staisfaite(s)");
        reglesMetiersNonStaisfaite = reglesMetiers.stream().collect(Collectors.toList());    }

    public List<String> getReglesMetiersNonStaisfaite() {
        return reglesMetiersNonStaisfaite;
    }
}
