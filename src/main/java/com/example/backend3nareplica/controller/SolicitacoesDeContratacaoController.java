package com.example.backend3nareplica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.backend3nareplica.entity.SolicitacoesDeContratacao;
import com.example.backend3nareplica.entity.Usuario;
import com.example.backend3nareplica.dto.SolicitacoesDeContratacaoRequestDTO;
import com.example.backend3nareplica.entity.Nutricionista;
import com.example.backend3nareplica.repository.SolicitacoesDeContratacaoRepository;
import com.example.backend3nareplica.repository.UsuarioRepository;
import com.example.backend3nareplica.repository.NutricionistaRepository;

@RestController
@RequestMapping("/solicitacao")
public class SolicitacoesDeContratacaoController {

    @Autowired
    private SolicitacoesDeContratacaoRepository solicitacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NutricionistaRepository nutricionistaRepository;

    @PostMapping
    public ResponseEntity<?> criarSolicitacao(@RequestBody SolicitacoesDeContratacaoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElse(null);
        if (usuario == null) return ResponseEntity.status(404).body("Usuário não encontrado.");

        Nutricionista nutricionista = nutricionistaRepository.findById(dto.getNutricionistaId()).orElse(null);
        if (nutricionista == null) return ResponseEntity.status(404).body("Nutricionista não encontrado.");

        SolicitacoesDeContratacao solicitacao = new SolicitacoesDeContratacao();
        solicitacao.setUsuario(usuario);
        solicitacao.setNutricionista(nutricionista);
        solicitacao.setDataSolicitacao(dto.getDataSolicitacao());
        solicitacao.setStatus(dto.getStatus() != null ? dto.getStatus() : "Pendente");

        SolicitacoesDeContratacao salva = solicitacaoRepository.save(solicitacao);
        return ResponseEntity.status(201).body(salva);
    }

    @GetMapping
    public ResponseEntity<List<SolicitacoesDeContratacao>> getSolicitacoes() {
        return ResponseEntity.ok(solicitacaoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacoesDeContratacao> getSolicitacaoPorId(@PathVariable Long id) {
        return solicitacaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{idUser}")
    public ResponseEntity<List<SolicitacoesDeContratacao>> getSolicitacoesPorUsuario(@PathVariable Long idUser) {
        return ResponseEntity.ok(solicitacaoRepository.findByUsuarioIdUser(idUser));
    }

    @GetMapping("/nutricionista/{idNutri}")
    public ResponseEntity<List<SolicitacoesDeContratacao>> getSolicitacoesPorNutricionista(@PathVariable Long idNutri) {
        return ResponseEntity.ok(solicitacaoRepository.findByNutricionistaIdNutri(idNutri));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putSolicitacao(@PathVariable Long id, @RequestBody SolicitacoesDeContratacao dados) {
        return solicitacaoRepository.findById(id).map(s -> {
            s.setStatus(dados.getStatus());
            s.setDataSolicitacao(dados.getDataSolicitacao());
            solicitacaoRepository.save(s);
            return ResponseEntity.ok("Solicitação atualizada com sucesso!");
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitacao(@PathVariable Long id) {
        if (solicitacaoRepository.existsById(id)) {
            solicitacaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}