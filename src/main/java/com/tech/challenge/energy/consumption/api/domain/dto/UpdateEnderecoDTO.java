package com.tech.challenge.energy.consumption.api.domain.dto;

import lombok.Data;

@Data
public class UpdateEnderecoDTO {
    private String rua;
    private Integer numero;
    private String cidade;
    private String bairro;
    private String estado;
}
