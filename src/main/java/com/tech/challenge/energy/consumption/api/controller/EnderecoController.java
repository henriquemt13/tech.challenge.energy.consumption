package com.tech.challenge.energy.consumption.api.controller;

import com.tech.challenge.energy.consumption.api.domain.dto.EnderecoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.PessoaResponseDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.UpdateEnderecoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.UpdatePessoaDTO;
import com.tech.challenge.energy.consumption.api.domain.model.Endereco;
import com.tech.challenge.energy.consumption.api.domain.model.Pessoa;
import com.tech.challenge.energy.consumption.api.exceptions.EnderecoNotFound;
import com.tech.challenge.energy.consumption.api.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1/endereco")
@AllArgsConstructor
public class EnderecoController {

    private final EnderecoService service;

    @PostMapping("/{userId}")
    public ResponseEntity createEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO, @PathVariable("userId") Long userId) {
        service.save(enderecoDTO, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateEndereco(@PathVariable("userId") Long userId,
                                                          @RequestBody @Valid UpdateEnderecoDTO enderecoDTO) {
        Optional<Endereco> optionalEndereco = service.findById(userId);
        if (optionalEndereco.isEmpty()) {
            throw new EnderecoNotFound(userId);
        }
        service.update(enderecoDTO, optionalEndereco.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Endereco> getEnderecoById(@PathVariable("userId") Long userId) {
        Optional<Endereco> optionalEndereco = service.findById(userId);
        if (optionalEndereco.isEmpty()) {
            throw new EnderecoNotFound(userId);
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalEndereco.get());
    }

    @GetMapping
    public ResponseEntity getAllEnderecos(EnderecoDTO enderecoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByFilter(enderecoDTO));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Pessoa> deleteEnderecoByUserId(@PathVariable("userId") Long userId) {
        service.delete(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
