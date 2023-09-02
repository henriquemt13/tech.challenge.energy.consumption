package com.tech.challenge.energy.consumption.api.domain.mapper;

import com.tech.challenge.energy.consumption.api.domain.dto.ResidenteEnderecoDTO;
import com.tech.challenge.energy.consumption.api.domain.model.ResidenteEndereco;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface ResidentesEnderecoMapper {

    ResidenteEnderecoDTO residenteEnderecoToResidenteEnderecoDTO(ResidenteEndereco residenteEndereco);

    List<ResidenteEnderecoDTO> residenteEnderecoToResidenteEnderecoDTO(List<ResidenteEndereco> residenteEndereco);

    ResidenteEndereco residenteEnderecoDTOToResidenteEndereco(ResidenteEnderecoDTO residenteEnderecoDTO);
}
