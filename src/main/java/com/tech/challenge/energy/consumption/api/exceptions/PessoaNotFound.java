package com.tech.challenge.energy.consumption.api.exceptions;

public class PessoaNotFound extends NotFoundException {

    public PessoaNotFound(Long userId) {
        super(String.format("Pessoa ID [%d] not found", userId));
    }
}
