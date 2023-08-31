package com.tech.challenge.energy.consumption.api.service;

import com.tech.challenge.energy.consumption.api.domain.dto.ParenteDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.ParentescoRequestDTO;
import com.tech.challenge.energy.consumption.api.domain.mapper.ParentescoMapper;
import com.tech.challenge.energy.consumption.api.domain.model.Parentesco;
import com.tech.challenge.energy.consumption.api.repository.ParentescoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Transactional
public class ParentescoService {

    private final ParentescoRepository repository;
    private final ParentescoMapper mapper;

    public List<ParenteDTO> getParentes(Long pessoaId) {
        return mapper.parentescoToParenteDTO(filterParentesco(pessoaId));
    }

    public List<Parentesco> filterParentesco(Long pessoaId) {
        List<Parentesco> parentes = new ArrayList<>();
        parentes.addAll(findByParenteId(pessoaId));
        parentes.addAll(findByPessoaId(pessoaId));
        parentes.removeAll(parentes.stream()
                .filter(parentesco -> Objects.equals(parentesco.getPessoaId(), pessoaId)).toList());
        return parentes.stream().distinct().toList();
    }

    public void saveParentescos(Long pessoaId, List<ParentescoRequestDTO> parentes) {
        if (parentes != null) {
            for (ParentescoRequestDTO parente : parentes) {
                saveParentesco(pessoaId, parente);
            }
        }
    }

    public void saveParentesco(Long pessoaId, ParentescoRequestDTO parentesco) {
        Parentesco parentescoModel = mapper.pessoaDTOToParentesco(pessoaId, parentesco);
        parentescoModel.setCreatedBy("System");
        parentescoModel.setCreatedAt(OffsetDateTime.now());
        repository.save(parentescoModel);
    }

    public List<Parentesco> findByParenteId(Long parenteId) {
        return repository.findByParenteId(parenteId);
    }

    public void deleteParentescosByPessoaId(Long pessoaId) {
        List<Parentesco> parentescos = findByPessoaId(pessoaId);
        for (Parentesco parentesco : parentescos) {
            deleteParentesco(parentesco);
        }
    }

    public void deleteParentesco(Parentesco parentesco) {
        repository.delete(parentesco);
    }

    public List<Parentesco> findByPessoaId(Long pessoaId) {
        return repository.findByPessoaId(pessoaId);
    }
}
