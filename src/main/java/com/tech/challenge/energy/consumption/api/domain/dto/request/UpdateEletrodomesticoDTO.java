package com.tech.challenge.energy.consumption.api.domain.dto.request;

import lombok.Data;

@Data
public class UpdateEletrodomesticoDTO {
    private String nome;
    private String modelo;
    private Integer potencia;
}
