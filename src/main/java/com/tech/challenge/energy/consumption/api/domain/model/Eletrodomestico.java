package com.tech.challenge.energy.consumption.api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "energy_consumption.eletrodomestico")
public class Eletrodomestico {

    @Id
    @SequenceGenerator(name = "energy_consumption.eletrodomestico_seq",
            sequenceName = "energy_consumption.eletrodomestico_seq", allocationSize = 1)
    @GeneratedValue(generator = "energy_consumption.eletrodomestico_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @JoinColumn(name = "id_endereco")
    private Long enderecoId;
    @NotNull(message = "nome should not be null")
    @NotEmpty(message = "nome should not be null")
    @Column(name = "nome")
    private String nome;
    @NotNull(message = "modelo should not be null")
    @NotEmpty(message = "modelo should not be null")
    @Column(name = "modelo")
    private String modelo;
    @NotNull(message = "potencia should not be null")
    @Column(name = "potencia")
    private Integer potencia;
    @NotNull(message = "Please enter createdAt")
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    @NotNull(message = "Please enter createdBy")
    @NotBlank(message = "Please enter createdBy")
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
    @Column(name = "updated_by")
    private String updatedBy;

}
