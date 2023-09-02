package com.tech.challenge.energy.consumption.api.domain.dto;

import com.tech.challenge.energy.consumption.api.domain.dto.request.ParentescoRequestDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PessoaDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String genero;
    private List<ParentescoRequestDTO> parentes;
}
