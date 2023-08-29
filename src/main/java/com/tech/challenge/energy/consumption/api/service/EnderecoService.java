package com.tech.challenge.energy.consumption.api.service;

import com.tech.challenge.energy.consumption.api.domain.dto.EnderecoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.EnderecoDetailDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.PessoaDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.UpdateEnderecoDTO;
import com.tech.challenge.energy.consumption.api.domain.mapper.EnderecoMapper;
import com.tech.challenge.energy.consumption.api.domain.model.Endereco;
import com.tech.challenge.energy.consumption.api.exceptions.EnderecoNotFound;
import com.tech.challenge.energy.consumption.api.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EnderecoService {

    private final PessoaService pessoaService;
    private final EnderecoRepository repository;
    private final EnderecoMapper mapper;

    public void save(EnderecoDTO enderecoDTO, Long userId) {
        pessoaService.validateUserId(userId);
        repository.save(mapper.enderecoDTOToEnderecoModel(enderecoDTO, userId));
    }

    public void update(UpdateEnderecoDTO enderecoDTO, Endereco endereco) {
        repository.save(mapper.updateEnderecoFromUpdateEnderecoDTO(enderecoDTO, endereco));
    }

    public Optional<Endereco> findByUserId(Long userId) {
        PessoaDTO pessoaDTO = pessoaService.getPessoaById(userId);
        return findById(pessoaDTO.getEnderecoId());
    }

    public List<Endereco> findAll() {
        return repository.findAll();
    }

    public Optional<Endereco> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        Optional<Endereco> endereco = findById(id);
        if (endereco.isEmpty()) {
            throw new EnderecoNotFound(id);
        }
        repository.delete(endereco.get());
    }

    public List<Endereco> findByFilter(EnderecoDTO enderecoDTO) {
        ExampleMatcher matcher = getExampleMatcher();
        Example<Endereco> example = Example.of(
                mapper.enderecoDTOToEnderecoModel(enderecoDTO), matcher);
        return repository.findAll(example);
    }

    public EnderecoDetailDTO findEnderecoDetailById(Long id) {
        Optional<Endereco> endereco = findById(id);
        if (endereco.isEmpty()) {
            throw new EnderecoNotFound(id);
        }
        List<PessoaDTO> residentes = pessoaService.getPessoasByEnderecoId(endereco.get().getId());
        return mapper.enderecoAndPessoaDTOtoEnderecoDetailDTO(endereco.get(), residentes);
    }

    private ExampleMatcher getExampleMatcher() {
        return ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
    }
}
