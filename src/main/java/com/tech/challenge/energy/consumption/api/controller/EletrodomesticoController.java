package com.tech.challenge.energy.consumption.api.controller;

import com.tech.challenge.energy.consumption.api.domain.dto.request.EletrodomesticoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.request.UpdateEletrodomesticoDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.response.CreateResponseDTO;
import com.tech.challenge.energy.consumption.api.domain.dto.response.EletrodomesticoDetailDTO;
import com.tech.challenge.energy.consumption.api.domain.model.Eletrodomestico;
import com.tech.challenge.energy.consumption.api.exceptions.EletrodomesticoNotFound;
import com.tech.challenge.energy.consumption.api.service.EletrodomesticoService;
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
@Slf4j
@RequestMapping("/api/v1/eletrodomestico")
@AllArgsConstructor
public class EletrodomesticoController {

    private final EletrodomesticoService service;

    @PostMapping("/pessoa/{pessoaId}")
    @ApiResponse(description = "Eletrodomestico Response", responseCode = "201")
    @Operation(summary = "Create Eletrodomestico", description = """
          # Cria novo Eletrodomestico
          ---
          notes:
          - Para adicionar um eletrodomestico, uma Pessoa préviamente precisa existir salva no banco;
          """)
    public ResponseEntity<CreateResponseDTO> createEletrodomestico(@RequestBody @Valid EletrodomesticoDTO eletrodomesticoDTO,
                                                                   @PathVariable("pessoaId") Long pessoaId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CreateResponseDTO(service.save(eletrodomesticoDTO, pessoaId)));
    }

    @PutMapping("/{id}")
    @ApiResponse(description = "Endereco Detail Response", responseCode = "200")
    @Operation(summary = "Update Eletrodomestico by ID", description = """
          # Atualiza Eletrodomestico por ID
          ---
          notes:
          - Eletrodomestico precisa existir no banco;
          """)
    public ResponseEntity<Void> updateEletrodomesticoById(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateEletrodomesticoDTO eletrodomesticoDTO) {
        service.update(id, eletrodomesticoDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/pessoa/{pessoaId}")
    @ApiResponse(description = "Endereco Detail Response", responseCode = "200")
    @Operation(summary = "Get Eletrodomestico By Pessoa ID", description = """
          # Busca Eletrodomestico por ID Pessoa
          ---
          notes:
          - Pessoa precisa existir no banco;
          """)
    public ResponseEntity<List<Eletrodomestico>> getEletrodomesticoByPessoaId(@PathVariable("pessoaId") Long pessoaId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByPessoaId(pessoaId));
    }

    @GetMapping("/{id}")
    @ApiResponse(description = "Endereco Detail Response", responseCode = "200")
    @Operation(summary = "Get Eletrodomestico By ID", description = """
          # Busca Eletrodomestico por ID
          ---
          notes:
          - Eletrodomestico precisa existir no banco;
          """)
    public ResponseEntity<EletrodomesticoDetailDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getEletrodomesticoDetail(id));
    }

    @GetMapping
    @ApiResponse(description = "Endereco Detail Response", responseCode = "200")
    @Operation(summary = "Get All Eletrodomestico", description = """
          # Busca todos os Eletrodomesticos
          ---
          notes:
          - Para busca sem filtros, apenas deixe "{}" nos parametros da requisição no Swagger,
            ou não envie nada via, se estiver dia Postman ou Swagger;
          """)
    public ResponseEntity<List<Eletrodomestico>> getAllEletrodomesticos(EletrodomesticoDTO eletrodomesticoDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByFilter(eletrodomesticoDTO));
    }


    @DeleteMapping("/{id}")
    @ApiResponse(description = "Void", responseCode = "200")
    @Operation(summary = "Delete Eletrodomestico By ID", description = """
          # Apaga Eletrodomestico por ID
          ---
          notes:
          - Eletrodomestico precisa existir no banco;
          """)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
