package com.tech.challenge.energy.consumption.api.controller;

import com.tech.challenge.energy.consumption.api.domain.dto.request.PessoaRequestDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.request.UpdatePessoaDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.response.CreateResponseDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.response.PessoaDetailDTO;
import com.tech.challenge.energy.consumption.api.domain.model.Pessoa;
import com.tech.challenge.energy.consumption.api.exceptions.PessoaNotFound;
import com.tech.challenge.energy.consumption.api.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @ApiResponse(description = "Pessoa Response", responseCode = "201")
    @Operation(summary = "Create Pessoa", description = """
          # Cria nova Pessoa
          ---
          notes:
          - Para adicionar um parente, ele préviamente precisa existir como uma outra pessoa salva no banco;
          """)
    public ResponseEntity<CreateResponseDTO> createPessoa(@RequestBody @Valid PessoaRequestDTO pessoaRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateResponseDTO(service.save(pessoaRequestDTO)));
    }

    @PutMapping("/{pessoaId}")
    @ApiResponse(description = "Void", responseCode = "200")
    @Operation(summary = "Update Pessoa by id", description = """
          # Atualiza pessoa pelo ID
          ---
          notes:
          - Pessoa precisa existir no banco;
          """)
    public ResponseEntity<Void> updatePessoa(@PathVariable("pessoaId") Long pessoaId,
                                                          @RequestBody @Valid UpdatePessoaDTO pessoaDTO) {
        Pessoa pessoa = validatePessoaById(pessoaId);
        service.update(pessoaDTO, pessoa);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{pessoaId}")
    @ApiResponse(description = "Pessoa response", responseCode = "200")
    @Operation(summary = "Get Pessoa by id", description = """
          # Busca pessoa Pelo Id
          ---
          notes:
          - Pessoa precisa existir no banco;
          """)
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable("pessoaId") Long pessoaId) {
        return ResponseEntity.status(HttpStatus.OK).body(validatePessoaById(pessoaId));
    }

    @GetMapping
    @ApiResponse(description = "Pessoa response", responseCode = "200")
    @Operation(summary = "Find All Pessoa", description = """
          # Busca todas as Pessoas
          """)
    public ResponseEntity<List<Pessoa>> getAllPessoas(PessoaRequestDTO pessoaRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByFilter(pessoaRequestDTO));
    }

    @GetMapping("/parentes/{pessoaId}")
    @ApiResponse(description = "Pessoa Detail response", responseCode = "200")
    @Operation(summary = "Get Parentes by Pessoa ID", description = """
          # Busca parentes a partir da Pessoa ID
          ---
          notes:
          - Pessoa precisa existir no banco;
          """)
    public ResponseEntity<PessoaDetailDTO> getParentesById(@PathVariable("pessoaId") Long pessoaId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findParentesById(pessoaId));
    }

    @DeleteMapping("/{pessoaId}")
    @ApiResponse(description = "Void", responseCode = "200")
    @Operation(summary = "Delete Pessoa by id", description = """
          # Apaga Pessoa por ID
          ---
          notes:
          - Pessoa precisa existir no banco;
          """)
    public ResponseEntity<Void> deletePessoaById(@PathVariable("pessoaId") Long pessoaId) {
        service.delete(pessoaId);
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
