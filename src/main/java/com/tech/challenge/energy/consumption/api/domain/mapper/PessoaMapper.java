package com.tech.challenge.energy.consumption.api.domain.mapper;

import com.tech.challenge.energy.consumption.api.domain.dto.ParenteDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.PessoaDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.PessoaDetailDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.UpdatePessoaDTO;
import com.tech.challenge.energy.consumption.api.domain.model.Parentesco;
import com.tech.challenge.energy.consumption.api.domain.model.Pessoa;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface PessoaMapper {

    Pessoa pessoaDTOToPessoaModel(PessoaDTO pessoaDTO);

    @Named("updateUserFromUserDto")
    @Mapping(target = "id", ignore = true)
    Pessoa updatePessoaFromUpdatePessoaDTO(UpdatePessoaDTO dto, @MappingTarget Pessoa pessoa);

    PessoaDetailDTO pessoaAndParenteDTOsToPessoaDetailDTO(Pessoa pessoa, List<ParenteDTO> parentes);

    PessoaDTO pessoaToPessoaDTO(Pessoa pessoa);

    List<PessoaDTO> pessoasToPessoaDTOs(List<Pessoa> pessoas);
}
