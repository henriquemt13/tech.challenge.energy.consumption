package com.tech.challenge.energy.consumption.api.domain.dto.response;

import com.tech.challenge.energy.consumption.api.domain.dto.request.PessoaRequestDTO;
import lombok.Data;

import java.util.List;

@Data
public class EletrodomesticoDetailDTO {

    private Long id;
    private String nome;
    private String modelo;
    private Integer potencia;
    private List<PessoaRequestDTO> usuarios;
}
