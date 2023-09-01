package com.tech.challenge.energy.consumption.api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "residentes_endereco")
public class ResidentesEndereco {

    @Id
    @SequenceGenerator(name = "residentes_endereco_seq",
            sequenceName = "residentes_endereco_seq", allocationSize = 1)
    @GeneratedValue(generator = "residentes_endereco_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @NotNull(message = "pessoaId should not be null")
    @Column(name = "id_pessoa")
    private Long pessoaId;
    @NotNull(message = "enderecoId should not be null")
    @Column(name = "id_endereco")
    private Long enderecoId;
    @CreationTimestamp
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
