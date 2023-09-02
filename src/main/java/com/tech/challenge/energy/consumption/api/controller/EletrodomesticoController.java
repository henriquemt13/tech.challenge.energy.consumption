package com.tech.challenge.energy.consumption.api.controller;

import com.tech.challenge.energy.consumption.api.domain.dto.request.EletrodomesticoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.request.UpdateEletrodomesticoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.response.CreateResponseDTO;
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

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1/eletrodomestico")
@AllArgsConstructor
public class EletrodomesticoController {

    private final EletrodomesticoService service;

    @PostMapping("/pessoa/{pessoaId}")
    public ResponseEntity<CreateResponseDTO> createEletrodomestico(@RequestBody @Valid EletrodomesticoDTO eletrodomesticoDTO,
                                                                   @PathVariable("pessoaId") Long pessoaId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateResponseDTO(service.save(eletrodomesticoDTO, pessoaId)));
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

    @GetMapping("/{id}")
    public ResponseEntity<EletrodomesticoDetailDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getEletrodomesticoDetail(id));
    }

    @GetMapping
    public ResponseEntity<List<Eletrodomestico>> getAllEletrodomesticos(EletrodomesticoDTO eletrodomesticoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByFilter(eletrodomesticoDTO));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
