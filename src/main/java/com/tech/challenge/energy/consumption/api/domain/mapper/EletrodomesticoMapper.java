package com.tech.challenge.energy.consumption.api.domain.mapper;

import com.tech.challenge.energy.consumption.api.domain.dto.EletrodomesticoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.EletrodomesticoDetailDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.PessoaDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.UpdateEletrodomesticoDTO;
import com.tech.challenge.energy.consumption.api.domain.model.Eletrodomestico;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, componentModel = "spring")
public interface EletrodomesticoMapper {

    Eletrodomestico eletrodomesticoDTOToEletrodomesticoModel(EletrodomesticoDTO eletrodomesticoDTO, Long enderecoId);

    Eletrodomestico eletrodomesticoDTOToEletrodomesticoModel(EletrodomesticoDTO eletrodomesticoDTO);

    @Named("updateEletrodomesticoFromUpdateEletrodomesticoDTO")
    @Mapping(target = "id", ignore = true)
    Eletrodomestico updateEletrodomesticoFromUpdateEletrodomesticoDTO(
            UpdateEletrodomesticoDTO dto, @MappingTarget Eletrodomestico eletrodomestico);

    EletrodomesticoDetailDTO eletrodomesticoAndPessoaDTOsToEletrodomesticoDetailDTO(Eletrodomestico eletrodomestico,
                                                                                    List<PessoaDTO> usuarios);
}
