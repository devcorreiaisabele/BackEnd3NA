package com.example.backend3nareplica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import com.example.backend3nareplica.entity.PlanoReceita;
import com.example.backend3nareplica.entity.PlanoAlimentar;
import com.example.backend3nareplica.entity.Receita;
import com.example.backend3nareplica.repository.PlanoReceitaRepository;
import com.example.backend3nareplica.repository.PlanoAlimentarRepository;
import com.example.backend3nareplica.repository.ReceitaRepository;

@RestController
@RequestMapping("/planoreceita")
public class PlanoReceitaController {

    @Autowired
    private PlanoReceitaRepository planoReceitaRepository;

    @Autowired
    private PlanoAlimentarRepository planoAlimentarRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    @PostMapping
    public ResponseEntity<?> adicionarReceita(@RequestBody java.util.Map<String, Long> body) {
        Long planoId   = body.get("planoId");
        Long receitaId = body.get("receitaId");

        PlanoAlimentar plano   = planoAlimentarRepository.findById(planoId).orElse(null);
        Receita        receita = receitaRepository.findById(receitaId).orElse(null);

        if (plano == null)   return ResponseEntity.status(404).body("Plano não encontrado.");
        if (receita == null) return ResponseEntity.status(404).body("Receita não encontrada.");

        PlanoReceita pr = new PlanoReceita();
        pr.setPlano(plano);
        pr.setReceita(receita);
        pr.setDataInclusao(LocalDate.now());

        planoReceitaRepository.save(pr);
        return ResponseEntity.status(201).body("Receita adicionada ao plano!");
    }

    @GetMapping("/plano/{idPlano}")
    public ResponseEntity<List<PlanoReceita>> getReceitasDoPlano(@PathVariable Long idPlano) {
        return ResponseEntity.ok(planoReceitaRepository.findByPlanoIdPlano(idPlano));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerReceita(@PathVariable Long id) {
        if (planoReceitaRepository.existsById(id)) {
            planoReceitaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
