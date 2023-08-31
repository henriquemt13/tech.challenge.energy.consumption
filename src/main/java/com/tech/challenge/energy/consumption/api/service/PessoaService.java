package com.tech.challenge.energy.consumption.api.service;

import com.tech.challenge.energy.consumption.api.domain.dto.PessoaDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.PessoaRequestDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.PessoaDetailDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.UpdatePessoaDTO;
import com.tech.challenge.energy.consumption.api.domain.mapper.PessoaMapper;
import com.tech.challenge.energy.consumption.api.domain.model.Pessoa;
import com.tech.challenge.energy.consumption.api.exceptions.PessoaNotFound;
import com.tech.challenge.energy.consumption.api.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class PessoaService {

    private final PessoaRepository repository;
    private final ParentescoService parentescoService;
    private final PessoaMapper mapper;

    public Long save(PessoaRequestDTO pessoaRequestDTO) {
        Pessoa pessoa = mapper.pessoaRequestDTOToPessoaModel(pessoaRequestDTO);
        pessoa.setCreatedBy("System");
        repository.save(pessoa);
        parentescoService.saveParentescos(pessoa.getId(), pessoaRequestDTO.getParentes());
        return pessoa.getId();
    }

    public void update(UpdatePessoaDTO pessoaDTO, Pessoa pessoa) {
        repository.save(mapper.updatePessoaFromUpdatePessoaDTO(pessoaDTO, pessoa));
        if (pessoaDTO.getParentes() != null) {
            parentescoService.saveParentescos(pessoa.getId(), pessoaDTO.getParentes());
        }
    }

    public void updateEndereco(PessoaDTO pessoaDTO) {
        repository.save(mapper.pessoaToPessoaModel(pessoaDTO));
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

    public PessoaDetailDTO findParentesById(Long userId) {
        Optional<Pessoa> optionalPessoa = findById(userId);
        if (optionalPessoa.isEmpty()) {
            throw new PessoaNotFound(userId);
        }
        Pessoa pessoa = optionalPessoa.get();
        var parentes = parentescoService.getParentes(userId);
        parentes.forEach(parente -> parente.setNome(getPessoaById(parente.getId()).getNome()));
        return mapper.pessoaAndParenteDTOsToPessoaDetailDTO(pessoa, parentes);
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
        parentescoService.deleteParentescosByPessoaId(userId);
        repository.delete(pessoa);
    }

    public List<Pessoa> findByFilter(PessoaRequestDTO pessoaRequestDTO) {
        ExampleMatcher matcher = getExampleMatcher();
        Example<Pessoa> example = Example.of(
                mapper.pessoaRequestDTOToPessoaModel(pessoaRequestDTO), matcher);
        return repository.findAll(example);
    }

    private ExampleMatcher getExampleMatcher() {
        return ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
    }

}
