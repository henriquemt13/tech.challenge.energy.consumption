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
@Table(name = "energy_consumption.endereco")
public class Endereco {

    @Id
    @SequenceGenerator(name = "energy_consumption.endereco_seq",
            sequenceName = "energy_consumption.endereco_seq", allocationSize = 1)
    @GeneratedValue(generator = "energy_consumption.endereco_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @NotNull(message = "rua should not be null")
    @NotEmpty(message = "rua should not be empty")
    private String rua;
    @NotNull(message = "numero should not be null")
    private Integer numero;
    @NotNull(message = "cidade should not be null")
    @NotEmpty(message = "cidade should not be null")
    private String cidade;
    @NotNull(message = "bairro should not be null")
    @NotEmpty(message = "bairro should not be null")
    private String bairro;
    @NotNull(message = "estado should not be null")
    @NotEmpty(message = "estado should not be null")
    private String estado;

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
