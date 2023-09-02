package com.tech.challenge.energy.consumption.api.service;

import com.tech.challenge.energy.consumption.api.domain.dto.ResidenteEnderecoDTO;
import com.tech.challenge.energy.consumption.api.domain.mapper.ResidentesEnderecoMapper;
import com.tech.challenge.energy.consumption.api.domain.model.ResidenteEndereco;
import com.tech.challenge.energy.consumption.api.exceptions.ResidenteEnderecoNotFound;
import com.tech.challenge.energy.consumption.api.repository.ResidenteEnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class ResidenteEnderecoService {

    private final ResidenteEnderecoRepository repository;
    private final ResidentesEnderecoMapper mapper;

    public List<ResidenteEndereco> findByEnderecoId(Long enderecoId) {
        return repository.findByEnderecoId(enderecoId);
    }

    public List<ResidenteEndereco> findByPessoaId(Long pessoaId) {
        return repository.findByPessoaId(pessoaId);
    }

    public List<ResidenteEnderecoDTO> getByEnderecoId(Long enderecoId) {
        return mapper.residenteEnderecoToResidenteEnderecoDTO(findByEnderecoId(enderecoId));
    }

    public Optional<ResidenteEndereco> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<ResidenteEndereco> findByEnderecoIdAndPessoaId(Long enderecoId, Long pessoaId) {
        return repository.findByEnderecoIdAndPessoaId(enderecoId, pessoaId);
    }

    public void save(Long enderecoId, Long pessoaId) {
        ResidenteEndereco residenteEndereco = mapper.residenteEnderecoDTOToResidenteEndereco(
                new ResidenteEnderecoDTO(pessoaId, enderecoId));
        residenteEndereco.setCreatedBy("System");
        repository.save(residenteEndereco);
    }

    public void deleteByEnderecoIdAndPessoaId(Long enderecoId, Long pessoaId) {
        Optional<ResidenteEndereco> residente = findByEnderecoIdAndPessoaId(enderecoId, pessoaId);
        if (residente.isEmpty()) {
            throw new ResidenteEnderecoNotFound(String
                    .format("Relation between Pessoa ID [%d] or Endereco ID [%s] not found", pessoaId, enderecoId));
        }
        repository.delete(residente.get());
    }

    public void deleteByEnderecoId(Long enderecoId) {
        List<ResidenteEndereco> residentes = findByEnderecoId(enderecoId);
        for (ResidenteEndereco residente : residentes) {
            repository.delete(residente);
        }
    }

}
