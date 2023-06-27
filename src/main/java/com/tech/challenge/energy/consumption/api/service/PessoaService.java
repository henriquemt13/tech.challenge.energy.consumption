package com.tech.challenge.energy.consumption.api.service;

import com.tech.challenge.energy.consumption.api.domain.dto.PessoaDTO;
import com.tech.challenge.energy.consumption.api.domain.mapper.PessoaMapper;
import com.tech.challenge.energy.consumption.api.domain.model.Pessoa;
import com.tech.challenge.energy.consumption.api.exceptions.PessoaNotFound;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private List<Pessoa> pessoas = new ArrayList<>();

    public Long createPessoa(PessoaDTO pessoaDTO) {
        Long id = getNextId();
        pessoas.add(PessoaMapper.INSTANCE.pessoaDTOToPessoaModel(pessoaDTO, id));
        return id;
    }

    private Long getNextId() {
        long lastId = pessoas.size();
        return lastId + 1;
    }

    public void validadeUserId(Long userId) {
        if (pessoas.stream().noneMatch(pessoa -> (pessoa.getId() == userId))) {
            throw new PessoaNotFound(userId);
        }
    }

    public Pessoa getPessoaById(Long userId) {
        Optional<Pessoa> optionalPessoa = pessoas.stream().filter(pessoa -> pessoa.getId() == userId).findFirst();
        if (optionalPessoa.isEmpty()) {
            throw new PessoaNotFound(userId);
        }
        return optionalPessoa.get();
    }

    public List<Pessoa> getAllPessoas() {
        return pessoas;
    }
}
