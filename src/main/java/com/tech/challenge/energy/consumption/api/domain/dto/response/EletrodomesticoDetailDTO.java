package com.tech.challenge.energy.consumption.api.domain.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;

@Data
@Builder
public class EletrodomesticoDetailDTO {

    private Long id;
    private String nome;
    private String modelo;
    private String potencia;
    private Integer horasUsoDia;
    private String mediaConsumoEnergia;
}
