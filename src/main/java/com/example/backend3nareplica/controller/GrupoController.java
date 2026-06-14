import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/grupo")
@Tag(name = "Grupo", description = "Operações relacionadas a grupos")
public class GrupoController {
    @PostMapping
    @Operation(summary = "Criar um novo Grupo", description = "Cria um novo Grupo com os detalhes fornecidos.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Grupo criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public String createGrupo() {
        return "Grupo criado";
    }
    @GetMapping("/{id}")
    @Operation(summary = "Buscar Grupo por ID", description = "Obtém um Grupo pelo seu ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Grupo encontrado"),
        @ApiResponse(responseCode = "404", description = "Grupo não encontrado")
    })
    public String getGrupo(
        @Parameter(description = "ID do grupo a ser buscado", example = "1")
        @PathVariable Long id
    ) {
        return "Detalhes do Grupo";
    }
}
package com.example.backend3nareplica.entity;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name = "grupo")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "nutricionista_id")
    private Nutricionista nutricionista;
    @ManyToMany
    @JoinTable(
        name = "grupo_usuario",
        joinColumns = @JoinColumn(name = "grupo_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Nutricionista getNutricionista() {
        return nutricionista;
    }
    public void setNutricionista(Nutricionista nutricionista) {
        this.nutricionista = nutricionista;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
package com.example.backend3nareplica.repository;
import com.example.backend3nareplica.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}
