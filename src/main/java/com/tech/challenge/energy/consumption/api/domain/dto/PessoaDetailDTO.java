package com.tech.challenge.energy.consumption.api.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class PessoaDetailDTO {

    private Long id;
    private String nome;
    private List<ParenteDTO> parentes;
}
