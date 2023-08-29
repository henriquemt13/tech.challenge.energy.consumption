package com.tech.challenge.energy.consumption.api.domain.mapper;

import com.tech.challenge.energy.consumption.api.domain.dto.EnderecoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.EnderecoDetailDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.PessoaDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.UpdateEnderecoDTO;
import com.tech.challenge.energy.consumption.api.domain.model.Endereco;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface EnderecoMapper {

    Endereco enderecoDTOToEnderecoModel(EnderecoDTO enderecoDTO, Long userId);

    Endereco enderecoDTOToEnderecoModel(EnderecoDTO enderecoDTO);

    @Named("updateEnderecoFromUpdateEnderecoDTO")
    @Mapping(target = "id", ignore = true)
    Endereco updateEnderecoFromUpdateEnderecoDTO(UpdateEnderecoDTO dto, @MappingTarget Endereco endereco);

    EnderecoDetailDTO enderecoAndPessoaDTOtoEnderecoDetailDTO(Endereco endereco, List<PessoaDTO> pessoaDTO);
}
