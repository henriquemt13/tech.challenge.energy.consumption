package com.tech.challenge.energy.consumption.api.service;

import com.tech.challenge.energy.consumption.api.domain.dto.ParenteDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.PessoaDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.request.PessoaRequestDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.response.PessoaDetailDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.request.UpdatePessoaDTO;
import com.tech.challenge.energy.consumption.api.domain.mapper.PessoaMapper;
import com.tech.challenge.energy.consumption.api.domain.model.Parentesco;
import com.tech.challenge.energy.consumption.api.domain.model.Pessoa;
import com.tech.challenge.energy.consumption.api.exceptions.PessoaNotFound;
import com.tech.challenge.energy.consumption.api.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class PessoaService {

    private final PessoaRepository repository;
    private final ParentescoService parentescoService;
    private final ResidenteEnderecoService residenteEnderecoService;
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

    public void validatePessoaId(Long pessoaId) {
        if (!repository.existsById(pessoaId)) {
            throw new PessoaNotFound(pessoaId);
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

    public PessoaDetailDTO findParentesById(Long pessoaId) {
        Optional<Pessoa> optionalPessoa = findById(pessoaId);
        if (optionalPessoa.isEmpty()) {
            throw new PessoaNotFound(pessoaId);
        }
        Pessoa pessoa = optionalPessoa.get();
        var parentes = parentescoService.getParentes(pessoaId);
        return mapper.pessoaAndParenteDTOsToPessoaDetailDTO(pessoa, validateParentes(parentes, pessoaId));
    }

    public List<ParenteDTO> validateParentes(List<ParenteDTO> parentes, Long pessoaId) {
        for (ParenteDTO parente : parentes) {
            if (Objects.equals(parente.getParenteId(), pessoaId)) {
                parente.setNome(getPessoaById(parente.getPessoaId()).getNome());
                parente.setId(parente.getPessoaId());
            } else {
                parente.setNome(getPessoaById(parente.getParenteId()).getNome());
                parente.setId(parente.getParenteId());
            }
        }
        return parentes;
    }

    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    public void delete(Long pessoaId) {
        Optional<Pessoa> optionalPessoa = findById(pessoaId);
        if (optionalPessoa.isEmpty()) {
            throw new PessoaNotFound(pessoaId);
        }
        Pessoa pessoa = optionalPessoa.get();
        parentescoService.deleteParentescosByPessoaId(pessoaId);
        residenteEnderecoService.deleteResidentes(residenteEnderecoService.findByPessoaId(pessoaId));
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
