package com.tech.challenge.energy.consumption.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ParenteDTO {

    @JsonIgnore
    private Long pessoaId;
    @JsonIgnore
    private Long parenteId;
    private Long id;
    private String nome;
    private String parentesco;
}
