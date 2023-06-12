package com.tech.challenge.energy.consumption.api.controller;

import com.tech.challenge.energy.consumption.api.domain.dto.PessoaDTO;
import com.tech.challenge.energy.consumption.api.domain.model.Pessoa;
import com.tech.challenge.energy.consumption.api.service.PessoaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pessoa")
@Slf4j
@AllArgsConstructor
public class PessoaController {

    private final PessoaService service;

    @PostMapping
    public ResponseEntity<Void> createPessoa(@RequestBody @Valid PessoaDTO pessoaDTO) {
        service.createPessoa(pessoaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getPessoaById(userId));
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllPessoas());
    }
}
