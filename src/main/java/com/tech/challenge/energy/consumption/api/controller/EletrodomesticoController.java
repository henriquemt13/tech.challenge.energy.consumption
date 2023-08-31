package com.tech.challenge.energy.consumption.api.controller;

import com.tech.challenge.energy.consumption.api.domain.dto.EletrodomesticoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.EletrodomesticoDetailDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.UpdateEletrodomesticoDTO;
import com.tech.challenge.energy.consumption.api.domain.model.Eletrodomestico;
import com.tech.challenge.energy.consumption.api.domain.model.Pessoa;
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

    @PostMapping("/{userId}")
    public ResponseEntity createEletrodomestico(@RequestBody @Valid EletrodomesticoDTO eletrodomesticoDTO,
                                                @PathVariable("userId") Long userId) {
        service.save(eletrodomesticoDTO, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEletrodomesticoById(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateEletrodomesticoDTO eletrodomesticoDTO) {
        service.update(id, eletrodomesticoDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Eletrodomestico>> getEletrodomesticoByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByUserId(userId));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<EletrodomesticoDetailDTO> getEletrodomesticoDetailById(@PathVariable("id") Long id) {
//        return ResponseEntity.status(HttpStatus.OK).body(service.getEletrodomesticoDetailById(id));
//    }

    @GetMapping
    public ResponseEntity<List<Eletrodomestico>> getAllEletrodomestico(EletrodomesticoDTO eletrodomesticoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByFilter(eletrodomesticoDTO));
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteEletrodomesticoByUserId(@PathVariable("userId") Long userId) {
        service.delete(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
