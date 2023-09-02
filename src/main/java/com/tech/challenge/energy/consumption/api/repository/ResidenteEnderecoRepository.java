package com.tech.challenge.energy.consumption.api.repository;

import com.tech.challenge.energy.consumption.api.domain.model.ResidenteEndereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResidenteEnderecoRepository extends JpaRepository<ResidenteEndereco, Long> {

    List<ResidenteEndereco> findByEnderecoId(Long enderecoId);

    List<ResidenteEndereco> findByPessoaId(Long pessoaId);

    Optional<ResidenteEndereco> findByEnderecoIdAndPessoaId(Long enderecoId, Long pessoaId);
}
