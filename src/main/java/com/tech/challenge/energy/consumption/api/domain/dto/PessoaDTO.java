package com.tech.challenge.energy.consumption.api.domain.dto;

import com.tech.challenge.energy.consumption.api.enums.GeneroEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class PessoaDTO {
    private Long id;
    @NotNull(message = "nome should not be null")
    @NotEmpty(message = "nome should not be empty")
    private String nome;
    @NotNull(message = "dataNascimento should not be null")
    private LocalDate dataNascimento;
    @NotNull(message = "sexoEnum should not be null")
    private GeneroEnum generoEnum;
    private List<ParentescoRequestDTO> parentes;
    private Long enderecoId;
}
