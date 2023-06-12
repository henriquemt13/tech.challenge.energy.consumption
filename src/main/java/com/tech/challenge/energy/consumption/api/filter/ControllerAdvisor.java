package com.tech.challenge.energy.consumption.api.filter;

import com.tech.challenge.energy.consumption.api.exceptions.PessoaNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(PessoaNotFound.class)
    public ResponseEntity handleNotFoundException(PessoaNotFound ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

}
