package com.tech.challenge.energy.consumption.api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa {
    @Id
    @SequenceGenerator(name = "pessoa_seq",
            sequenceName = "pessoa_seq", allocationSize = 1)
    @GeneratedValue(generator = "pessoa_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @NotNull(message = "nome should not be null")
    @NotEmpty(message = "nome should not be empty")
    @Column(name = "nome")
    private String nome;
    @NotNull(message = "dataNascimento should not be null")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    @NotNull(message = "generoEnum should not be null")
    @Column(name = "pessoa_genero")
    private String genero;
    @Column(name = "created_at")
    @CreationTimestamp
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
