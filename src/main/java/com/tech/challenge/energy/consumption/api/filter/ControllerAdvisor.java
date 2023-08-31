package com.tech.challenge.energy.consumption.api.filter;

import com.tech.challenge.energy.consumption.api.exceptions.EletrodomesticoNotFound;
import com.tech.challenge.energy.consumption.api.exceptions.EnderecoNotFound;
import com.tech.challenge.energy.consumption.api.exceptions.PessoaNotFound;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(PessoaNotFound.class)
    public ResponseEntity handlePessoaNotFoundException(PessoaNotFound ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(EletrodomesticoNotFound.class)
    public ResponseEntity handleEletrodomesticoNotFoundException(EletrodomesticoNotFound ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(EnderecoNotFound.class)
    public ResponseEntity handleEnderecoNotFoundException(EnderecoNotFound ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleContraintViolationException(ConstraintViolationException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getLocalizedMessage());
    }
}
