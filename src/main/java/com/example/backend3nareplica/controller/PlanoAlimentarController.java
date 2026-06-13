package com.example.backend3nareplica.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import com.example.backend3nareplica.entity.PlanoAlimentar;
import com.example.backend3nareplica.entity.Usuario;
import com.example.backend3nareplica.entity.Nutricionista;
import com.example.backend3nareplica.repository.PlanoAlimentarRepository;
import com.example.backend3nareplica.repository.UsuarioRepository;
import com.example.backend3nareplica.repository.NutricionistaRepository;
@RestController
@RequestMapping("/plano")
public class PlanoAlimentarController {
    @Autowired
    private PlanoAlimentarRepository planoAlimentarRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private NutricionistaRepository nutricionistaRepository;
    @PostMapping
    public ResponseEntity<?> criarPlano(@RequestBody Map<String, Object> body) {
        Long usuarioId      = ((Number) ((Map<?,?>) body.get("usuario")).get("idUser")).longValue();
        Long nutricionistaId = ((Number) ((Map<?,?>) body.get("nutricionista")).get("idNutri")).longValue();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        Nutricionista nutricionista = nutricionistaRepository.findById(nutricionistaId).orElse(null);
        if (usuario == null)       return ResponseEntity.status(404).body("Usuário não encontrado.");
        if (nutricionista == null) return ResponseEntity.status(404).body("Nutricionista não encontrado.");
        PlanoAlimentar plano = new PlanoAlimentar();
        plano.setUsuario(usuario);
        plano.setNutricionista(nutricionista);
        if (body.get("caloriasAlvo") != null) plano.setCaloriasAlvo(new java.math.BigDecimal(body.get("caloriasAlvo").toString()));
        if (body.get("proteinaAlvo") != null) plano.setProteinaAlvo(new java.math.BigDecimal(body.get("proteinaAlvo").toString()));
        if (body.get("carboAlvo")    != null) plano.setCarboAlvo(new java.math.BigDecimal(body.get("carboAlvo").toString()));
        if (body.get("gorduraAlvo")  != null) plano.setGorduraAlvo(new java.math.BigDecimal(body.get("gorduraAlvo").toString()));
        if (body.get("status")       != null) plano.setStatus(body.get("status").toString());
        PlanoAlimentar salvo = planoAlimentarRepository.save(plano);
        return ResponseEntity.status(201).body(salvo);
    }
    @GetMapping
    public ResponseEntity<List<PlanoAlimentar>> getPlanos() {
        return ResponseEntity.ok(planoAlimentarRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PlanoAlimentar> getPlanoPorId(@PathVariable Long id) {
        return planoAlimentarRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/usuario/{idUser}")
    public ResponseEntity<List<PlanoAlimentar>> getPlanosPorUsuario(@PathVariable Long idUser) {
        return ResponseEntity.ok(planoAlimentarRepository.findByUsuarioIdUser(idUser));
    }
    @GetMapping("/nutricionista/{idNutri}")
    public ResponseEntity<List<PlanoAlimentar>> getPlanosPorNutricionista(@PathVariable Long idNutri) {
        return ResponseEntity.ok(planoAlimentarRepository.findByNutricionistaIdNutri(idNutri));
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> putPlano(@PathVariable Long id, @RequestBody PlanoAlimentar dados) {
        return planoAlimentarRepository.findById(id).map(p -> {
            p.setCaloriasAlvo(dados.getCaloriasAlvo());
            p.setProteinaAlvo(dados.getProteinaAlvo());
            p.setGorduraAlvo(dados.getGorduraAlvo());
            p.setCarboAlvo(dados.getCarboAlvo());
            p.setStatus(dados.getStatus());
            planoAlimentarRepository.save(p);
            return ResponseEntity.ok("Plano alimentar atualizado com sucesso!");
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlano(@PathVariable Long id) {
        if (planoAlimentarRepository.existsById(id)) {
            planoAlimentarRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
