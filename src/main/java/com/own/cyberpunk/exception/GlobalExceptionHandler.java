package com.own.cyberpunk.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final MessageSource messageSource;

    @Autowired
    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ValidationError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.error("A validation error occurred: ", ex);
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return new ResponseEntity<>(processFieldErrors(fieldErrors), HttpStatus.BAD_REQUEST);
    }

    private ValidationError processFieldErrors(List<FieldError> fieldErrors) {
        ValidationError validationError = new ValidationError();

        for (FieldError fieldError : fieldErrors) {
            validationError.addFieldError(fieldError.getField(), messageSource.getMessage(fieldError, Locale.getDefault()));
        }

        return validationError;
    }

    @ExceptionHandler(FighterNotFoundException.class)
    public ResponseEntity<List<Violation>> onApartmentNotFoundException(FighterNotFoundException e) {
        return createResponseEntity("fighterId", "no fighter found with id: " + e.getId());
    }

    @ExceptionHandler(GunNotFoundException.class)
    public ResponseEntity<List<Violation>> onGunNotFoundException(GunNotFoundException e) {
        return createResponseEntity("gunId", "no gun found with id: " + e.getId());
    }

    private ResponseEntity<List<Violation>> createResponseEntity(String field, String errorMessage) {
        List<Violation> violations = new ArrayList<>();
        Violation violation = new Violation(field, errorMessage);
        violations.add(violation);
        log.error("Error in validation: " + violation.getField() + ": " + violation.getErrorMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(violations);
    }
}