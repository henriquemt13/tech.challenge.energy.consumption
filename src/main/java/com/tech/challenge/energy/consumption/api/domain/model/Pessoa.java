package com.tech.challenge.energy.consumption.api.domain.model;

import com.tech.challenge.energy.consumption.api.enums.SexoEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Pessoa {
    @NotNull(message = "id should not be null")
    private Long id;
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
