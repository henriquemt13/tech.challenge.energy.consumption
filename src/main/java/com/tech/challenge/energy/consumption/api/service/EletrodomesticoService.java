package com.tech.challenge.energy.consumption.api.service;

import com.tech.challenge.energy.consumption.api.domain.dto.request.EletrodomesticoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.request.UpdateEletrodomesticoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.response.EletrodomesticoDetailDTO;
import com.tech.challenge.energy.consumption.api.domain.mapper.EletrodomesticoMapper;
import com.tech.challenge.energy.consumption.api.domain.model.Eletrodomestico;
import com.tech.challenge.energy.consumption.api.exceptions.EletrodomesticoNotFound;
import com.tech.challenge.energy.consumption.api.repository.EletrodomesticoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class EletrodomesticoService {

    private final PessoaService pessoaService;
    private final EletrodomesticoRepository repository;
    private final EletrodomesticoMapper mapper;

    public Long save(EletrodomesticoDTO eletrodomesticoDTO, Long pessoaId) {
        pessoaService.validatePessoaId(pessoaId);
        Eletrodomestico eletrodomestico = mapper.eletrodomesticoDTOToEletrodomesticoModel(eletrodomesticoDTO, pessoaId);
        eletrodomestico.setCreatedBy("System");
        return repository.save(eletrodomestico).getId();
    }

    public void update(Long id, UpdateEletrodomesticoDTO eletrodomesticoDTO) {
        Optional<Eletrodomestico> eletrodomestico = findById(id);
        if (eletrodomestico.isEmpty()) {
            throw new EletrodomesticoNotFound(id);
        }
        repository.save(mapper
                .updateEletrodomesticoFromUpdateEletrodomesticoDTO(eletrodomesticoDTO, eletrodomestico.get()));
    }

    public EletrodomesticoDetailDTO getEletrodomesticoDetail(Long id) {
        Optional<Eletrodomestico> eletrodomestico = findById(id);
        if (eletrodomestico.isEmpty()) {
            throw new EletrodomesticoNotFound(id);
        }
        EletrodomesticoDetailDTO detailDTO = mapper.eletrodomesticoToEletrodomesticoDetailDTO(eletrodomestico.get());
        setConsumoValues(detailDTO, eletrodomestico.get());
        return detailDTO;
    }

    private void setConsumoValues(EletrodomesticoDetailDTO detailDTO, Eletrodomestico eletrodomestico) {
        detailDTO.setPotencia(String.format("%s W", eletrodomestico.getPotencia()));
        detailDTO.setMediaConsumoEnergia(String.format("%s kWh",
                calculateConsumo(eletrodomestico.getHorasUsoDia(), eletrodomestico.getPotencia())));
    }

    private BigDecimal calculateConsumo(Integer horasConsumoDia, Integer potencia) {
        BigDecimal kW = BigDecimal.valueOf((long) potencia * horasConsumoDia);
        return kW.divide(BigDecimal.valueOf(1000)).round(MathContext.DECIMAL32);
    }

    public List<Eletrodomestico> findByPessoaId(Long pessoaId) {
        return repository.findByPessoaId(pessoaId);
    }

    public Optional<Eletrodomestico> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        Optional<Eletrodomestico> eletrodomestico = findById(id);
        if (eletrodomestico.isEmpty()) {
            throw new EletrodomesticoNotFound(id);
        }
        repository.delete(eletrodomestico.get());
    }

    public List<Eletrodomestico> findByFilter(EletrodomesticoDTO eletrodomesticoDTO) {
        ExampleMatcher matcher = getExampleMatcher();
        Example<Eletrodomestico> example = Example.of(
                mapper.eletrodomesticoDTOToEletrodomesticoModel(eletrodomesticoDTO), matcher);
        return repository.findAll(example);
    }
    private ExampleMatcher getExampleMatcher() {
        return ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
    }

}
