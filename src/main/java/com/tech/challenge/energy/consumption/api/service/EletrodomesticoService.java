package com.tech.challenge.energy.consumption.api.service;

import com.tech.challenge.energy.consumption.api.domain.dto.EletrodomesticoDTO;
import com.tech.challenge.energy.consumption.api.domain.mapper.EletrodomesticoMapper;
import com.tech.challenge.energy.consumption.api.domain.model.Eletrodomestico;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EletrodomesticoService {

    private final PessoaService pessoaService;
    private List<Eletrodomestico> eletrodomesticos;

    public void createEletrodomestico(EletrodomesticoDTO eletrodomesticoDTO, Long userId) {
        pessoaService.validadeUserId(userId);
        eletrodomesticos.add(EletrodomesticoMapper.INSTANCE.enderecoDTOToEnderecoModel(eletrodomesticoDTO, userId));
    }

    public List<Eletrodomestico> getEletrodomesticosByUser(Long userId) {
        pessoaService.validadeUserId(userId);
        return eletrodomesticos.stream().filter(eletrodomestico -> eletrodomestico.getUserId() == userId).toList();
    }

    public List<Eletrodomestico> getEletrodomesticos() {
        return eletrodomesticos;
    }
}
