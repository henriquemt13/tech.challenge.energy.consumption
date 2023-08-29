package com.tech.challenge.energy.consumption.api.repository;

import com.tech.challenge.energy.consumption.api.domain.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>, QueryByExampleExecutor<Endereco> {

}
