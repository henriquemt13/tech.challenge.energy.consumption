package com.tech.challenge.energy.consumption.api.domain.model;

import com.tech.challenge.energy.consumption.api.enums.GeneroEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@Entity
@Table(name = "energy_consumption.parentesco")
public class Parentesco {

    @Id
    @SequenceGenerator(name = "energy_consumption.pessoa_seq",
            sequenceName = "energy_consumption.pessoa_seq", allocationSize = 1)
    @GeneratedValue(generator = "energy_consumption.pessoa_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @NotNull(message = "idPessoa should not be null")
    @NotEmpty(message = "idPessoa should not be empty")
    @Column(name = "id_pessoa")
    private Long idPessoa;
    @NotNull(message = "idParente should not be null")
    @Column(name = "id_parente")
    private Long idParente;
    @NotNull(message = "parentesco should not be null")
    @Column(name = "parentesco")
    private GeneroEnum parentesco;
    @NotNull(message = "Please enter createdAt")
    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();
    @NotNull(message = "Please enter createdBy")
    @NotBlank(message = "Please enter createdBy")
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
    @Column(name = "updated_by")
    private String updatedBy;
}
