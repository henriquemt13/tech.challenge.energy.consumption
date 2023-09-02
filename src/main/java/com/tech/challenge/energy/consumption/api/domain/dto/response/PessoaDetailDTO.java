package com.tech.challenge.energy.consumption.api.domain.dto.response;

import com.tech.challenge.energy.consumption.api.domain.dto.ParenteDTO;
import lombok.Data;

import java.util.List;

@Data
public class PessoaDetailDTO {

    private Long id;
    private String nome;
    private List<ParenteDTO> parentes;
}
