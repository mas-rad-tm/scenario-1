package ch.globaz.tmmas.rentesservice.application.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value= HttpStatus.CONFLICT, reason="No such Order")
public class RegleMetiersNonSatisfaite extends RuntimeException {

    public RegleMetiersNonSatisfaite(String message) {
        super(message);
    }
}
