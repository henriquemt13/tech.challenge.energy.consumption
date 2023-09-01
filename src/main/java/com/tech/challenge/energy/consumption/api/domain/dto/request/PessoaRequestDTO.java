package com.tech.challenge.energy.consumption.api.domain.dto.request;

import com.tech.challenge.energy.consumption.api.enums.GeneroEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class PessoaRequestDTO {
    @NotNull(message = "nome should not be null")
    @NotEmpty(message = "nome should not be empty")
    private String nome;
    @NotNull(message = "dataNascimento should not be null")
    private LocalDate dataNascimento;
    @NotNull(message = "genero should not be null")
    private GeneroEnum genero;
    private List<ParentescoRequestDTO> parentes;
}
