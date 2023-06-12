package com.tech.challenge.energy.consumption.api.domain.mapper;

import com.tech.challenge.energy.consumption.api.domain.dto.EletrodomesticoDTO;
import com.tech.challenge.energy.consumption.api.domain.model.Eletrodomestico;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EletrodomesticoMapper {

    EletrodomesticoMapper INSTANCE = Mappers.getMapper(EletrodomesticoMapper.class);

    Eletrodomestico enderecoDTOToEnderecoModel(EletrodomesticoDTO eletrodomesticoDTO, Long userId);
}
