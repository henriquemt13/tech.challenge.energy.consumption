package com.tech.challenge.energy.consumption.api.domain.mapper;

import com.tech.challenge.energy.consumption.api.domain.dto.ParenteDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.request.ParentescoRequestDTO;
import com.tech.challenge.energy.consumption.api.domain.model.Parentesco;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface ParentescoMapper {

    List<ParenteDTO> parentescoToParenteDTO(List<Parentesco> parentesco);

    ParenteDTO parentescoToParenteDTO(Parentesco parentesco);

    @Mapping(source = "parentesco.parenteId", target = "parenteId")
    @Mapping(source = "parentesco.parentesco", target = "parentesco")
    Parentesco pessoaDTOToParentesco(Long pessoaId, ParentescoRequestDTO parentesco);
}
