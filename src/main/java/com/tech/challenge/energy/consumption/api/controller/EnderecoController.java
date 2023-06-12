package com.tech.challenge.energy.consumption.api.controller;

import com.tech.challenge.energy.consumption.api.domain.dto.EnderecoDTO;
import com.tech.challenge.energy.consumption.api.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/v1/endereco")
@AllArgsConstructor
public class EnderecoController {

    private final EnderecoService service;

    @PostMapping("/{userId}")
    public ResponseEntity createEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO, @PathVariable("userId") Long userId) {
        service.createEndereco(enderecoDTO, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity getEnderecoById(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getEnderecosByUser(userId));
    }

    @GetMapping
    public ResponseEntity getAllEnderecos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getEnderecos());
    }
}
