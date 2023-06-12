package com.tech.challenge.energy.consumption.api.service;

import com.tech.challenge.energy.consumption.api.domain.dto.EnderecoDTO;
import com.tech.challenge.energy.consumption.api.domain.mapper.EnderecoMapper;
import com.tech.challenge.energy.consumption.api.domain.model.Endereco;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EnderecoService {

    private final PessoaService pessoaService;
    private List<Endereco> enderecos;

    public void createEndereco(EnderecoDTO enderecoDTO, Long userId) {
        pessoaService.validadeUserId(userId);
        enderecos.add(EnderecoMapper.INSTANCE.enderecoDTOToEnderecoModel(enderecoDTO, userId));
    }

    public List<Endereco> getEnderecosByUser(Long userId) {
        pessoaService.validadeUserId(userId);
        return enderecos.stream().filter(endereco -> endereco.getUserId() == userId).toList();
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }
}
