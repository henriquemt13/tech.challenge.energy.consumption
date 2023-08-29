package com.tech.challenge.energy.consumption.api.service;

import com.tech.challenge.energy.consumption.api.domain.dto.PessoaDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.PessoaDetailDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.UpdatePessoaDTO;
import com.tech.challenge.energy.consumption.api.domain.mapper.PessoaMapper;
import com.tech.challenge.energy.consumption.api.domain.model.Pessoa;
import com.tech.challenge.energy.consumption.api.exceptions.PessoaNotFound;
import com.tech.challenge.energy.consumption.api.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;
    private final ParentescoService parentescoService;
    private final PessoaMapper mapper;

    public Long save(PessoaDTO pessoaDTO) {
        Pessoa pessoa = repository.save(mapper.pessoaDTOToPessoaModel(pessoaDTO));
        parentescoService.saveParentescos(pessoa.getId(), pessoaDTO.getParentes());
        return pessoa.getId();
    }

    public void update(UpdatePessoaDTO pessoaDTO, Pessoa pessoa) {
        repository.save(mapper.updatePessoaFromUpdatePessoaDTO(pessoaDTO, pessoa));
    }

    public void validateUserId(Long userId) {
        if (!repository.existsById(userId)) {
            throw new PessoaNotFound(userId);
        }
    }

    public Optional<Pessoa> findById(Long userId) {
        return repository.findById(userId);
    }

    public PessoaDTO getPessoaById(Long id) {
        Optional<Pessoa> optionalPessoa = findById(id);
        if (optionalPessoa.isEmpty()) {
            throw new PessoaNotFound(id);
        }
        return mapper.pessoaToPessoaDTO(optionalPessoa.get());
    }

    public List<Pessoa> findPessoaByEnderecoId(Long enderecoId) {
        return repository.findByEnderecoId(enderecoId);
    }

    public List<PessoaDTO> getPessoasByEnderecoId(Long enderecoId) {
        return mapper.pessoasToPessoaDTOs(findPessoaByEnderecoId(enderecoId));
    }

    public PessoaDetailDTO findParentesById(Long userId) {
        Optional<Pessoa> optionalPessoa = findById(userId);
        if (optionalPessoa.isEmpty()) {
            throw new PessoaNotFound(userId);
        }
        Pessoa pessoa = optionalPessoa.get();
        return mapper.pessoaAndParenteDTOsToPessoaDetailDTO(pessoa, parentescoService.getParentes(userId));
    }

    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    public void delete(Long userId) {
        Optional<Pessoa> optionalPessoa = findById(userId);
        if (optionalPessoa.isEmpty()) {
            throw new PessoaNotFound(userId);
        }
        Pessoa pessoa = optionalPessoa.get();
        repository.delete(pessoa);
    }

    public List<Pessoa> findByFilter(PessoaDTO pessoaDTO) {
        ExampleMatcher matcher = getExampleMatcher();
        Example<Pessoa> example = Example.of(
                mapper.pessoaDTOToPessoaModel(pessoaDTO), matcher);
        return repository.findAll(example);
    }

    private ExampleMatcher getExampleMatcher() {
        return ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
    }

}
