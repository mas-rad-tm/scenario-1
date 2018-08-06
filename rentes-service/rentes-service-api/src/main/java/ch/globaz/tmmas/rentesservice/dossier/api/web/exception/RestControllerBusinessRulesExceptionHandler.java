package ch.globaz.tmmas.rentesservice.dossier.api.web.exception;

import ch.globaz.tmmas.rentesservice.dossier.api.web.resources.ApiError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe gérant les diverses exceptions pouvant être généré lors du traitement de la requête REST
 */
@ControllerAdvice
public class RestControllerBusinessRulesExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerBusinessRulesExceptionHandler.class);



    @ExceptionHandler(RegleMetiersNonSatisfaite.class)
    protected ResponseEntity<Object> handleReglesMetiersException(RegleMetiersNonSatisfaite ex){

        ApiError errors = new ApiError(HttpStatus.CONFLICT,ex.getMessage(),ex.getReglesMetiersNonStaisfaite());

        return ResponseEntity
                .badRequest()
                .body(errors);
    }

}