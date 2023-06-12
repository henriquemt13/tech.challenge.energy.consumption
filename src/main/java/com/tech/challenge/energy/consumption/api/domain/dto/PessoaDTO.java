package com.tech.challenge.energy.consumption.api.domain.dto;

import com.tech.challenge.energy.consumption.api.enums.SexoEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class PessoaDTO {
    @NotNull(message = "nome should not be null")
    @NotEmpty(message = "nome should not be empty")
    private String nome;
    @NotNull(message = "dataNascimento should not be null")
    private LocalDate dataNascimento;
    @NotNull(message = "sexoEnum should not be null")
    private SexoEnum sexoEnum;
    @NotNull(message = "parentescoComUsuario should not be null")
    @NotEmpty(message = "parentescoComUsuario should not be empty")
    private String parentescoComUsuario;
}
