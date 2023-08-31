package com.tech.challenge.energy.consumption.api.service;

import com.tech.challenge.energy.consumption.api.domain.dto.ResidentesEnderecoDTO;
import com.tech.challenge.energy.consumption.api.domain.mapper.ResidentesEnderecoMapper;
import com.tech.challenge.energy.consumption.api.domain.model.ResidentesEndereco;
import com.tech.challenge.energy.consumption.api.exceptions.ResidentesEnderecoNotFound;
import com.tech.challenge.energy.consumption.api.repository.ResidentesEnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ResidentesEnderecoService {

    private final ResidentesEnderecoRepository repository;
    private final ResidentesEnderecoMapper mapper;

    public List<ResidentesEndereco> findByEnderecoId(Long enderecoId) {
        return repository.findByEnderecoId(enderecoId);
    }


    public List<ResidentesEnderecoDTO> getByEnderecoId(Long enderecoId) {
        return mapper.residentesEnderecoToResidentesEnderecoDTO(findByEnderecoId(enderecoId));
    }

    public Optional<ResidentesEndereco> findById(Long id) {
        return repository.findById(id);
    }
    public Optional<ResidentesEndereco> findByEnderecoIdAndPessoaId(Long enderecoId, Long pessoaId) {
        return repository.findByEnderecoIdAndPessoaId(enderecoId, pessoaId);
    }

    public void deleteByEnderecoIdAndPessoaId(Long enderecoId, Long pessoaId) {
        Optional<ResidentesEndereco> residente = findByEnderecoIdAndPessoaId(enderecoId, pessoaId);
        if (residente.isEmpty()) {
            throw new ResidentesEnderecoNotFound(String
                    .format("Relation between Pessoa ID [%d] or Endereco ID [%s] not found", pessoaId, enderecoId));
        }
        repository.delete(residente.get());
    }

}
