package com.tech.challenge.energy.consumption.api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parentesco")
public class Parentesco {

    @Id
    @SequenceGenerator(name = "energy_consumption.pessoa_seq",
            sequenceName = "energy_consumption.pessoa_seq", allocationSize = 1)
    @GeneratedValue(generator = "energy_consumption.pessoa_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @NotNull(message = "pessoaId should not be null")
    @Column(name = "id_pessoa")
    private Long pessoaId;
    @NotNull(message = "parenteId should not be null")
    @Column(name = "id_parente")
    private Long parenteId;
    @NotNull(message = "parentesco should not be null")
    @Column(name = "parentesco")
    private String parentesco;
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
