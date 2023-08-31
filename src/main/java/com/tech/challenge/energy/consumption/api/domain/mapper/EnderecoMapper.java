package com.tech.challenge.energy.consumption.api.domain.mapper;

import com.tech.challenge.energy.consumption.api.domain.dto.*;
import com.tech.challenge.energy.consumption.api.domain.model.Endereco;
import org.mapstruct.*;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface EnderecoMapper {


    Endereco enderecoDTOToEnderecoModel(EnderecoDTO enderecoDTO);

    @Named("updateEnderecoFromUpdateEnderecoDTO")
    @Mapping(target = "id", ignore = true)
    Endereco updateEnderecoFromUpdateEnderecoDTO(UpdateEnderecoDTO dto, @MappingTarget Endereco endereco);

    EnderecoDetailDTO enderecoAndPessoaDTOtoEnderecoDetailDTO(Endereco endereco, List<PessoaDTO> pessoaRequestDTO);
}
