package com.tech.challenge.energy.consumption.api.domain.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Eletrodomestico {

    @NotNull(message = "userId should not be null")
    private Long userId;
    @NotNull(message = "nome should not be null")
    @NotEmpty(message = "nome should not be null")
    private String nome;
    @NotNull(message = "modelo should not be null")
    @NotEmpty(message = "modelo should not be null")
    private String modelo;
    @NotNull(message = "potencia should not be null")
    private Integer potencia;

}
