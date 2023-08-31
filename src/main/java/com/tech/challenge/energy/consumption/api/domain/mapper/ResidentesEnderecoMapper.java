package com.tech.challenge.energy.consumption.api.domain.mapper;

import com.tech.challenge.energy.consumption.api.domain.dto.ResidentesEnderecoDTO;
import com.tech.challenge.energy.consumption.api.domain.model.ResidentesEndereco;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface ResidentesEnderecoMapper {

    ResidentesEnderecoDTO residentesEnderecoToResidentesEnderecoDTO(ResidentesEndereco residentesEndereco);

    List<ResidentesEnderecoDTO> residentesEnderecoToResidentesEnderecoDTO(List<ResidentesEndereco> residentesEndereco);
}
