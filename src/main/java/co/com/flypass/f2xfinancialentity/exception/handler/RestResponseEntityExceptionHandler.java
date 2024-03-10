package co.com.flypass.f2xfinancialentity.exception.handler;

import co.com.flypass.f2xfinancialentity.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 09/03/2024, 11:37 PM
 **/
@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    private static final Logger LOG_EXCEPTION = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
    private static final String GENERAL_MESSAGE_ERROR = "Unknown error exception";
    private static final ConcurrentHashMap<String, Integer> STATUS_CODES_MAP = new ConcurrentHashMap<>();

    public RestResponseEntityExceptionHandler(){
        STATUS_CODES_MAP.put(MandatoryValueException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        STATUS_CODES_MAP.put(DuplicityValueException.class.getSimpleName(), HttpStatus.CONFLICT.value());
        STATUS_CODES_MAP.put(InvalidValueException.class.getSimpleName(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        STATUS_CODES_MAP.put(NotAllowedOperationException.class.getSimpleName(), HttpStatus.METHOD_NOT_ALLOWED.value());
        STATUS_CODES_MAP.put(NotFindException.class.getSimpleName(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handleConflict(Exception exception) {
        ResponseEntity<Error> errorResponseEntity;
        String nameException = exception.getClass().getSimpleName();
        String errorMessage = exception.getMessage();
        Integer httpCode = STATUS_CODES_MAP.get(nameException);
        if (httpCode != null) {
            Error error = new Error(nameException, errorMessage);
            errorResponseEntity = new ResponseEntity<>(error, HttpStatus.valueOf(httpCode));
        } else {
            LOG_EXCEPTION.error(nameException, exception);
            Error error = new Error(nameException, GENERAL_MESSAGE_ERROR);
            errorResponseEntity = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return errorResponseEntity;
    }
}
