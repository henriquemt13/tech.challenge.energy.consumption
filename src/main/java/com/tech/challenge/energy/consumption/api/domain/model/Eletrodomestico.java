package com.tech.challenge.energy.consumption.api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
@Table(name = "eletrodomestico")
public class Eletrodomestico {

    @Id
    @SequenceGenerator(name = "eletrodomestico_seq",
            sequenceName = "eletrodomestico_seq", allocationSize = 1)
    @GeneratedValue(generator = "eletrodomestico_seq")
    @Column(name = "id", unique = true)
    @NotNull(message = "id should not be null")
    private Long id;
    @NotNull(message = "pessoaId should not be null")
    @Column(name = "id_pessoa")
    private Long pessoaId;
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
    @NotNull(message = "horasUsoDia should not be null")
    @Column(name = "horas_uso_dia")
    private Integer horasUsoDia;
    @CreationTimestamp
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
