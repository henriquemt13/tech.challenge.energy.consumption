package com.tech.challenge.energy.consumption.api.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResidenteEnderecoDTO {

    private Long id;
    private Long pessoaId;
    private Long enderecoId;

    public ResidenteEnderecoDTO(Long pessoaId, Long enderecoId) {
        this.pessoaId = pessoaId;
        this.enderecoId = enderecoId;
    }
}
