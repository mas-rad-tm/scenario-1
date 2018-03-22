package ch.globaz.tmmas.rentesservice.application.api.web;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerExceptionHandler.class);

    /**

    @ExceptionHandler
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        LOGGER.warn(ex.getCause().toString());
        String bodyOfResponse = ex.getMessage();

        return new ResponseEntity<>(bodyOfResponse,HttpStatus.CONFLICT);
    }

*/


    @ExceptionHandler(value = InvalidFormatException.class)
    protected ResponseEntity<Object> handleCommandFormatException(RuntimeException ex, WebRequest request) {
        LOGGER.warn(ex.getCause().toString());
        String bodyOfResponse = ex.getMessage();
        /*return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
        */
        return new ResponseEntity<>(bodyOfResponse,HttpStatus.CONFLICT);
    }

}
