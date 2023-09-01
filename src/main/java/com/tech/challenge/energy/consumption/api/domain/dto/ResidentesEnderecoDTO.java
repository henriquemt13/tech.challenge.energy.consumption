package com.tech.challenge.energy.consumption.api.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResidentesEnderecoDTO {

    private Long id;
    private Long pessoaId;
    private Long enderecoId;

    public ResidentesEnderecoDTO(Long pessoaId, Long enderecoId) {
        this.pessoaId = pessoaId;
        this.enderecoId = enderecoId;
    }
}
