package com.tech.challenge.energy.consumption.api.domain.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Endereco {

    @NotNull(message = "userID should not be null")
    private Long userId;
    @NotNull(message = "rua should not be null")
    @NotEmpty(message = "rua should not be empty")
    private String rua;
    @NotNull(message = "numero should not be null")
    private Integer numero;
    @NotNull(message = "cidade should not be null")
    @NotEmpty(message = "cidade should not be null")
    private String cidade;
    @NotNull(message = "bairro should not be null")
    @NotEmpty(message = "bairro should not be null")
    private String bairro;
    @NotNull(message = "estado should not be null")
    @NotEmpty(message = "estado should not be null")
    private String estado;
}
