package com.tech.challenge.energy.consumption.api.controller;

import com.tech.challenge.energy.consumption.api.domain.dto.request.EletrodomesticoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.request.UpdateEletrodomesticoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.response.EletrodomesticoDetailDTO;
import com.tech.challenge.energy.consumption.api.domain.model.Eletrodomestico;
import com.tech.challenge.energy.consumption.api.exceptions.EletrodomesticoNotFound;
import com.tech.challenge.energy.consumption.api.service.EletrodomesticoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1/eletrodomestico")
@AllArgsConstructor
public class EletrodomesticoController {

    private final EletrodomesticoService service;

    @PostMapping("/pessoa/{pessoaId}")
    public ResponseEntity createEletrodomestico(@RequestBody @Valid EletrodomesticoDTO eletrodomesticoDTO,
                                                @PathVariable("pessoaId") Long pessoaId) {
        service.save(eletrodomesticoDTO, pessoaId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEletrodomesticoById(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateEletrodomesticoDTO eletrodomesticoDTO) {
        service.update(id, eletrodomesticoDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<Eletrodomestico>> getEletrodomesticoByPessoaId(@PathVariable("pessoaId") Long pessoaId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByPessoaId(pessoaId));
    }

    @GetMapping("/{id}/consumo")
    public ResponseEntity<EletrodomesticoDetailDTO> getConsumoByTimeRange(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getEletrodomesticoDetail(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Eletrodomestico> getById(@PathVariable("id") Long id) {
        Optional<Eletrodomestico> eletrodomestico = service.findById(id);
        if (eletrodomestico.isEmpty()) {
            throw new EletrodomesticoNotFound(id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(eletrodomestico.get());
    }

    @GetMapping
    public ResponseEntity<List<Eletrodomestico>> getAllEletrodomestico(EletrodomesticoDTO eletrodomesticoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByFilter(eletrodomesticoDTO));
    }


    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Void> deleteEletrodomesticoByPessoaId(@PathVariable("pessoaId") Long pessoaId) {
        service.delete(pessoaId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
