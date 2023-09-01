package com.tech.challenge.energy.consumption.api.domain.dto.request;

import com.tech.challenge.energy.consumption.api.domain.dto.request.ParentescoRequestDTO;
import com.tech.challenge.energy.consumption.api.enums.GeneroEnum;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UpdatePessoaDTO {
    private String nome;
    private LocalDate dataNascimento;
    private GeneroEnum generoEnum;
    private List<ParentescoRequestDTO> parentes;
    private Long enderecoId;
}
