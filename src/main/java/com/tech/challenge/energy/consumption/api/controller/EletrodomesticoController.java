package com.tech.challenge.energy.consumption.api.controller;

import com.tech.challenge.energy.consumption.api.domain.dto.EletrodomesticoDTO;
import com.tech.challenge.energy.consumption.api.service.EletrodomesticoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/eletrodomestico")
@AllArgsConstructor
public class EletrodomesticoController {

    private final EletrodomesticoService service;

    @PostMapping("/{userId}")
    public ResponseEntity createEletrodomestico(@RequestBody @Valid EletrodomesticoDTO eletrodomesticoDTO,
                                                @PathVariable("userId") Long userId) {
        service.createEletrodomestico(eletrodomesticoDTO, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity getEletrodomesticoById(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getEletrodomesticosByUser(userId));
    }

    @GetMapping
    public ResponseEntity getAllEletrodomestico() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getEletrodomesticos());
    }
}
