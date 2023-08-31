package com.tech.challenge.energy.consumption.api.service;

import com.tech.challenge.energy.consumption.api.domain.dto.*;
import com.tech.challenge.energy.consumption.api.domain.mapper.EletrodomesticoMapper;
import com.tech.challenge.energy.consumption.api.domain.model.Eletrodomestico;
import com.tech.challenge.energy.consumption.api.exceptions.EletrodomesticoNotFound;
import com.tech.challenge.energy.consumption.api.repository.EletrodomesticoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class EletrodomesticoService {

    private final PessoaService pessoaService;
    private final EnderecoService enderecoService;
    private final EletrodomesticoRepository repository;
    private final EletrodomesticoMapper mapper;

    public void save(EletrodomesticoDTO eletrodomesticoDTO, Long userId) {
        PessoaDTO pessoaDTO = pessoaService.getPessoaById(userId);
        repository.save(mapper.eletrodomesticoDTOToEletrodomesticoModel(eletrodomesticoDTO, pessoaDTO.getEnderecoId()));
    }

    public void update(Long id, UpdateEletrodomesticoDTO eletrodomesticoDTO) {
        Optional<Eletrodomestico> eletrodomestico = findById(id);
        if (eletrodomestico.isEmpty()) {
            throw new EletrodomesticoNotFound(id);
        }
        repository.save(mapper
                .updateEletrodomesticoFromUpdateEletrodomesticoDTO(eletrodomesticoDTO, eletrodomestico.get()));
    }

    public List<Eletrodomestico> findByUserId(Long userId) {
        PessoaDTO pessoaDTO = pessoaService.getPessoaById(userId);
        return findByEnderecoId(pessoaDTO.getEnderecoId());
    }

    public List<Eletrodomestico> findByEnderecoId(Long userId) {
        return repository.findByEnderecoId(userId);
    }

    public List<Eletrodomestico> findAll() {
        return repository.findAll();
    }

    public Optional<Eletrodomestico> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        Optional<Eletrodomestico> eletrodomestico = findById(id);
        if (eletrodomestico.isEmpty()) {
            throw new EletrodomesticoNotFound(id);
        }
        repository.delete(eletrodomestico.get());
    }

//    public EletrodomesticoDetailDTO getEletrodomesticoDetailById(Long id) {
//        Optional<Eletrodomestico> optionalEletrodomestico = findById(id);
//        if (optionalEletrodomestico.isEmpty()) {
//            throw new EletrodomesticoNotFound(id);
//        }
//        Eletrodomestico eletrodomestico = optionalEletrodomestico.get();
//        List<PessoaRequestDTO> usuarios = enderecoService.findEnderecoDetailById(eletrodomestico.getId()).getResidentes();
//        return mapper
//                .eletrodomesticoAndPessoaDTOsToEletrodomesticoDetailDTO(eletrodomestico, usuarios);
//
//    }

    public List<Eletrodomestico> findByFilter(EletrodomesticoDTO eletrodomesticoDTO) {
        ExampleMatcher matcher = getExampleMatcher();
        Example<Eletrodomestico> example = Example.of(
                mapper.eletrodomesticoDTOToEletrodomesticoModel(eletrodomesticoDTO), matcher);
        return repository.findAll(example);
    }
    private ExampleMatcher getExampleMatcher() {
        return ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
    }

}
