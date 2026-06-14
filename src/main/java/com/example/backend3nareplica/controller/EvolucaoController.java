package com.example.backend3nareplica.controller;

import com.example.backend3nareplica.entity.Grupo;
import com.example.backend3nareplica.repository.GrupoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/grupo")
@Tag(name = "Grupo", description = "Operações relacionadas a grupos")
public class GrupoController {

    private final GrupoRepository grupoRepository;

    public GrupoController(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    @PostMapping
    @Operation(summary = "Criar um novo Grupo", description = "Cria um novo Grupo com os detalhes fornecidos.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Grupo criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<Grupo> createGrupo(@RequestBody Grupo grupo) {
        Grupo salvo = grupoRepository.save(grupo);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Grupo por ID", description = "Obtém um Grupo pelo seu ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Grupo encontrado"),
        @ApiResponse(responseCode = "404", description = "Grupo não encontrado")
    })
    public ResponseEntity<Grupo> getGrupo(
        @Parameter(description = "ID do grupo a ser buscado", example = "1")
        @PathVariable Long id
    ) {
        Optional<Grupo> grupo = grupoRepository.findById(id);
        return grupo.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
}
