package com.tech.challenge.energy.consumption.api.domain.mapper;

import com.tech.challenge.energy.consumption.api.domain.dto.request.EletrodomesticoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.response.EletrodomesticoDetailDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.request.PessoaRequestDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.request.UpdateEletrodomesticoDTO;
import com.tech.challenge.energy.consumption.api.domain.model.Eletrodomestico;
import org.mapstruct.*;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface EletrodomesticoMapper {


    Eletrodomestico eletrodomesticoDTOToEletrodomesticoModel(EletrodomesticoDTO eletrodomesticoDTO);

    Eletrodomestico eletrodomesticoDTOToEletrodomesticoModel(EletrodomesticoDTO eletrodomesticoDTO, Long pessoaId);

    @Named("updateEletrodomesticoFromUpdateEletrodomesticoDTO")
    @Mapping(target = "id", ignore = true)
    Eletrodomestico updateEletrodomesticoFromUpdateEletrodomesticoDTO(
            UpdateEletrodomesticoDTO dto, @MappingTarget Eletrodomestico eletrodomestico);

    EletrodomesticoDetailDTO eletrodomesticoToEletrodomesticoDetailDTO(Eletrodomestico eletrodomestico);
}
