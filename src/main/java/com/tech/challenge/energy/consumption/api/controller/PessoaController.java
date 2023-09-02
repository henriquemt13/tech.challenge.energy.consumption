package com.tech.challenge.energy.consumption.api.controller;

import com.tech.challenge.energy.consumption.api.domain.dto.request.PessoaRequestDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.request.UpdatePessoaDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.response.CreateResponseDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.response.PessoaDetailDTO;
import com.tech.challenge.energy.consumption.api.domain.model.Pessoa;
import com.tech.challenge.energy.consumption.api.exceptions.PessoaNotFound;
import com.tech.challenge.energy.consumption.api.service.PessoaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pessoa")
@Slf4j
@AllArgsConstructor
public class PessoaController {

    private final PessoaService service;

    @PostMapping
    public ResponseEntity<CreateResponseDTO> createPessoa(@RequestBody @Valid PessoaRequestDTO pessoaRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateResponseDTO(service.save(pessoaRequestDTO)));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updatePessoa(@PathVariable("userId") Long userId,
                                                          @RequestBody @Valid UpdatePessoaDTO pessoaDTO) {
        Pessoa pessoa = validatePessoaById(userId);
        service.update(pessoaDTO, pessoa);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(validatePessoaById(userId));
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> getAllPessoas(PessoaRequestDTO pessoaRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByFilter(pessoaRequestDTO));
    }

    @GetMapping("/parentes/{userId}")
    public ResponseEntity<PessoaDetailDTO> getParentesById(@PathVariable("userId") Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findParentesById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Pessoa> deletePessoaById(@PathVariable("userId") Long userId) {
        service.delete(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public Pessoa validatePessoaById(Long userId) {
        Optional<Pessoa> optionalPessoa = service.findById(userId);
        if (optionalPessoa.isEmpty()) {
            throw new PessoaNotFound(userId);
        }
        return optionalPessoa.get();
    }


}
