package com.tech.challenge.energy.consumption.api.domain.dto;

import com.tech.challenge.energy.consumption.api.enums.GeneroEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdatePessoaDTO {
    private String nome;
    private LocalDate dataNascimento;
    private GeneroEnum generoEnum;
    private String parentescoComUsuario;
    private Long parenteId;
}
