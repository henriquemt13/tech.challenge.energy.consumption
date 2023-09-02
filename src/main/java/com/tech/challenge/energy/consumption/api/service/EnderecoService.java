package com.tech.challenge.energy.consumption.api.service;

import com.tech.challenge.energy.consumption.api.domain.dto.*;
import com.tech.challenge.energy.consumption.api.domain.dto.request.EnderecoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.request.UpdateEnderecoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.response.EnderecoDetailDTO;
import com.tech.challenge.energy.consumption.api.domain.mapper.EnderecoMapper;
import com.tech.challenge.energy.consumption.api.domain.model.Endereco;
import com.tech.challenge.energy.consumption.api.exceptions.EnderecoNotFound;
import com.tech.challenge.energy.consumption.api.exceptions.NotFoundException;
import com.tech.challenge.energy.consumption.api.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class EnderecoService {

    private final PessoaService pessoaService;
    private final EnderecoRepository repository;
    private final EnderecoMapper mapper;
    private final ResidenteEnderecoService residenteEnderecoService;

    public Long save(EnderecoDTO enderecoDTO, Long pessoaId) {
        pessoaService.validatePessoaId(pessoaId);
        Endereco endereco = mapper.enderecoDTOToEnderecoModel(enderecoDTO);
        endereco.setCreatedBy("System");
        repository.save(endereco);
        addResidente(endereco.getId(), pessoaId);
        return endereco.getId();
    }

    public void addResidente(Long enderecoId, Long pessoaId) {
        pessoaService.validatePessoaId(pessoaId);
        if (isValidToAddResidente(enderecoId, pessoaId)) {
            residenteEnderecoService.save(enderecoId, pessoaId);
        } else {
            throw new NotFoundException(String.format("Pessoa ID [%s] and Endereco ID [%s] are already related",
                    pessoaId, enderecoId));
        }
    }

    public void removeResidente(Long enderecoId, Long pessoaId) {
        pessoaService.validatePessoaId(pessoaId);
        if (isValidToRemoveResidente(enderecoId, pessoaId)) {
            residenteEnderecoService.deleteByEnderecoIdAndPessoaId(enderecoId, pessoaId);
        } else {
            throw new NotFoundException(String.format("Pessoas ID [%s] or Endereco ID [%s] relation not found",
                    pessoaId, enderecoId));
        }
    }

    public boolean isValidToAddResidente(Long enderecoId, Long pessoaId) {
        return residenteEnderecoService.findByPessoaId(pessoaId).isEmpty()
                && findById(enderecoId).isPresent();
    }

    public boolean isValidToRemoveResidente(Long enderecoId, Long pessoaId) {
        return residenteEnderecoService.findByEnderecoIdAndPessoaId(enderecoId, pessoaId).isPresent()
                && findById(enderecoId).isPresent();
    }

    public void update(UpdateEnderecoDTO enderecoDTO, Endereco endereco) {
        repository.save(mapper.updateEnderecoFromUpdateEnderecoDTO(enderecoDTO, endereco));
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
        residenteEnderecoService.deleteByEnderecoId(id);
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
        List<ResidenteEnderecoDTO> residentes = residenteEnderecoService.getByEnderecoId(endereco.get().getId());
        return mapper.enderecoAndPessoaDTOtoEnderecoDetailDTO(endereco.get(), getResidentesInfo(residentes));
    }

    public List<PessoaDTO> getResidentesInfo(List<ResidenteEnderecoDTO> residentes) {
        List<PessoaDTO> pessoas = new ArrayList<>();
        for (ResidenteEnderecoDTO residente : residentes ) {
            pessoas.add(pessoaService.getPessoaById(residente.getPessoaId()));
        }
        return pessoas;
    }

    private ExampleMatcher getExampleMatcher() {
        return ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
    }
}
