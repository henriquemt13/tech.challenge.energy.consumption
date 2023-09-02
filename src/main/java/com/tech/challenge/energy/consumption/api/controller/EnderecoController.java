package com.tech.challenge.energy.consumption.api.controller;

import com.tech.challenge.energy.consumption.api.domain.dto.request.EnderecoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.request.UpdateEnderecoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.response.CreateResponseDTO;
import com.tech.challenge.energy.consumption.api.domain.model.Endereco;
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

    @PostMapping("/pessoa/{pessoaId}")
    public ResponseEntity<CreateResponseDTO> createEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO, @PathVariable("pessoaId") Long pessoaId) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateResponseDTO(service.save(enderecoDTO, pessoaId)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEndereco(@PathVariable("id") Long id,
                                                          @RequestBody @Valid UpdateEnderecoDTO enderecoDTO) {
        Optional<Endereco> optionalEndereco = service.findById(id);
        if (optionalEndereco.isEmpty()) {
            throw new EnderecoNotFound(id);
        }
        service.update(enderecoDTO, optionalEndereco.get());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> getEnderecoById(@PathVariable("id") Long id) {
        Optional<Endereco> optionalEndereco = service.findById(id);
        if (optionalEndereco.isEmpty()) {
            throw new EnderecoNotFound(id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalEndereco.get());
    }

    @GetMapping
    public ResponseEntity getAllEnderecos(EnderecoDTO enderecoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByFilter(enderecoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnderecoById(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{enderecoId}/pessoa/{pessoaId}")
    public ResponseEntity<Void> addResidente(@PathVariable("enderecoId") Long enderecoId,
                                             @PathVariable("pessoaId") Long pessoaId) {
        service.addResidente(enderecoId, pessoaId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/residentes")
    public ResponseEntity getResidentesById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findEnderecoDetailById(id));
    }

    @DeleteMapping("/{enderecoId}/pessoa/{pessoaId}")
    public ResponseEntity<Void> removeResidente(@PathVariable("enderecoId") Long enderecoId,
                                                @PathVariable("pessoaId") Long pessoaId) {
        service.removeResidente(enderecoId, pessoaId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
