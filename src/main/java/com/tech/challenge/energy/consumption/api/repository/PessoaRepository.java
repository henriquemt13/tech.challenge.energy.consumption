package com.tech.challenge.energy.consumption.api.repository;

import com.tech.challenge.energy.consumption.api.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, QueryByExampleExecutor<Pessoa> {

}
