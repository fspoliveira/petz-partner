package br.com.petz.partner.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.petz.partner.exception.PetzCustomerNotFoundException;
import br.com.petz.partner.model.ErrorModel;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {PetzCustomerNotFoundException.class, EmptyResultDataAccessException.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
            ex,
            ErrorModel.builder().message("Address not found").build(),
            new HttpHeaders(),
            HttpStatus.NOT_FOUND,
            request);
    }

    @ExceptionHandler(value = {InvalidDataAccessApiUsageException.class})
    protected ResponseEntity<Object> invalidQueryStringField(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
            ex,
            ErrorModel.builder()
                .message("Invalid field name passed in query string! The possible values are [zipcode, streetName, city, latitude, longitude, country, neighbourhood, number, state, complement]. Bear in mind that it is case sensitive.")
                .build(),
            new HttpHeaders(),
            HttpStatus.INTERNAL_SERVER_ERROR,
            request);
    }
}
