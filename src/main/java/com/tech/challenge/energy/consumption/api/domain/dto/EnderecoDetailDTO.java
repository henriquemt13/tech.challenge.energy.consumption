package com.tech.challenge.energy.consumption.api.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class EnderecoDetailDTO {

    private Long id;
    private String rua;
    private String numero;
    private String cidade;
    private String bairro;
    private String estado;
    private List<PessoaDTO> residentes;

}
