package com.tech.challenge.energy.consumption.api.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnderecoDTO {

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
