package com.tech.challenge.energy.consumption.api.exceptions;

public class EletrodomesticoNotFound extends RuntimeException {

    public EletrodomesticoNotFound(Long id) {
        super(String.format("Eletrodomestico ID [%d] not found", id));
    }
}
