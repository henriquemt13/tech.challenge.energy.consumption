package com.tech.challenge.energy.consumption.api.repository;

import com.tech.challenge.energy.consumption.api.domain.model.Parentesco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentescoRepository extends JpaRepository<Parentesco, Long> {

    List<Parentesco> findByPessoaId(Long pessoaId);

    List<Parentesco> findByParenteId(Long parenteId);
}
