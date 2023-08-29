package com.tech.challenge.energy.consumption.api.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateEletrodomesticoDTO {
    private String nome;
    private String modelo;
    private Integer potencia;
}
