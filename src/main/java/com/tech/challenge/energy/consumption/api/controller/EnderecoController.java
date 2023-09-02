package com.tech.challenge.energy.consumption.api.controller;

import com.tech.challenge.energy.consumption.api.domain.dto.request.EnderecoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.request.UpdateEnderecoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.response.CreateResponseDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.response.EnderecoDetailDTO;
import com.tech.challenge.energy.consumption.api.domain.model.Endereco;
import com.tech.challenge.energy.consumption.api.exceptions.EnderecoNotFound;
import com.tech.challenge.energy.consumption.api.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @ApiResponse(description = "Endereco Response", responseCode = "201")
    @Operation(summary = "Create Endereco", description = """
          # Cria novo Endereco
          ---
          notes:
          - Para adicionar um endereco, uma Pessoa préviamente precisa existir salva no banco;
          """)
    public ResponseEntity<CreateResponseDTO> createEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO, @PathVariable("pessoaId") Long pessoaId) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateResponseDTO(service.save(enderecoDTO, pessoaId)));
    }

    @PutMapping("/{id}")
    @ApiResponse(description = "Void", responseCode = "200")
    @Operation(summary = "Update Endereco by ID", description = """
          # Atualiza Endereco por ID
          ---
          notes:
          - Endereco precisa existir no banco;
          """)
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
    @ApiResponse(description = "Endereco Response", responseCode = "200")
    @Operation(summary = "Get Endereco By ID", description = """
          # Busca Endereco por ID
          ---
          notes:
          - Endereco precisa existir no banco;
          """)
    public ResponseEntity<Endereco> getEnderecoById(@PathVariable("id") Long id) {
        Optional<Endereco> optionalEndereco = service.findById(id);
        if (optionalEndereco.isEmpty()) {
            throw new EnderecoNotFound(id);
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalEndereco.get());
    }

    @GetMapping
    @ApiResponse(description = "Endereco Response", responseCode = "200")
    @Operation(summary = "Get All Enderecos", description = """
          # Busca todos os Enderecos
          ---
          notes:
          - Para busca sem filtros, apenas deixe "{}" nos parametros da requisição no Swagger,
            ou não envie nada via, se estiver dia Postman ou Swagger;
          """)
    public ResponseEntity getAllEnderecos(EnderecoDTO enderecoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByFilter(enderecoDTO));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(description = "Endereco Response", responseCode = "200")
    @Operation(summary = "Delete Endereco By ID", description = """
          # Apaga Endereco por ID
          ---
          notes:
          - Endereco precisa existir no banco;
          """)
    public ResponseEntity<Void> deleteEnderecoById(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{enderecoId}/pessoa/{pessoaId}")
    @ApiResponse(description = "Endereco Response", responseCode = "201")
    @Operation(summary = "Add Residente By Endereco And Pessoa ID", description = """
          # Adiciona novo residente ao endereço por ID Endereco e ID Pessoa
          ---
          notes:
          - Endereco e Pessoa precisam existir no banco;
          - Pessoa não pode ser residente do endereco enviado
          """)
    public ResponseEntity<Void> addResidente(@PathVariable("enderecoId") Long enderecoId,
                                             @PathVariable("pessoaId") Long pessoaId) {
        service.addResidente(enderecoId, pessoaId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/residentes")
    @ApiResponse(description = "Endereco Detail Response", responseCode = "200")
    @Operation(summary = "Get Residentes By Endereco ID", description = """
          # Busca residentes de um endereco por ID Endereco
          ---
          notes:
          - Endereco precisa existir no banco;
          """)
    public ResponseEntity<EnderecoDetailDTO> getResidentesById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findEnderecoDetailById(id));
    }

    @DeleteMapping("/{enderecoId}/pessoa/{pessoaId}")
    @ApiResponse(description = "Endereco Response", responseCode = "200")
    @Operation(summary = "Remove Residente by Endereco and Pessoa ID", description = """
          # Remove residente do endereço por ID Endereco e ID Pessoa
          ---
          notes:
          - Endereco e Pessoa precisam existir no banco;
          - Pessoa deve ser residente do endereco enviado
          """)
    public ResponseEntity<Void> removeResidente(@PathVariable("enderecoId") Long enderecoId,
                                                @PathVariable("pessoaId") Long pessoaId) {
        service.removeResidente(enderecoId, pessoaId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
