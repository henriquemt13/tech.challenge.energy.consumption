package com.tech.challenge.energy.consumption.api.service;

import com.tech.challenge.energy.consumption.api.domain.dto.ParenteDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.ParentescoRequestDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.PessoaDTO;
import com.tech.challenge.energy.consumption.api.domain.mapper.ParentescoMapper;
import com.tech.challenge.energy.consumption.api.domain.model.Parentesco;
import com.tech.challenge.energy.consumption.api.repository.ParentescoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ParentescoService {

    private final ParentescoRepository repository;
    private final ParentescoMapper mapper;

    public List<ParenteDTO> getParentes(Long idPessoa) {
        return mapper.parentescoToParenteDTO(findByIdPessoa(idPessoa));
    }

    public void saveParentescos(Long pessoaId, List<ParentescoRequestDTO> parentes) {
        for (ParentescoRequestDTO parente : parentes) {
            saveParentesco(pessoaId, parente);
        }
    }

    public void saveParentesco(Long pessoaId, ParentescoRequestDTO parentesco) {
        Parentesco parentescoModel = mapper.pessoaDTOToParentesco(pessoaId, parentesco);
        parentescoModel.setCreatedBy("System");
        repository.save(parentescoModel);
    }

    public List<Parentesco> findByIdPessoa(Long idPessoa) {
        return repository.findByIdPessoa(idPessoa);
    }
}
