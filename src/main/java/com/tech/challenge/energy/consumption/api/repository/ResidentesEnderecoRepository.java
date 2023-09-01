package com.tech.challenge.energy.consumption.api.repository;

import com.tech.challenge.energy.consumption.api.domain.model.ResidentesEndereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResidentesEnderecoRepository extends JpaRepository<ResidentesEndereco, Long> {

    List<ResidentesEndereco> findByEnderecoId(Long enderecoId);

    List<ResidentesEndereco> findByPessoaId(Long pessoaId);

    Optional<ResidentesEndereco> findByEnderecoIdAndPessoaId(Long enderecoId, Long pessoaId);
}
