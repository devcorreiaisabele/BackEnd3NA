package com.example.backend3nareplica.entity;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "plano_receita")
public class PlanoReceita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plano_receita")
    private Long idPlanoReceita;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_id_plano", nullable = false)
    private PlanoAlimentar plano;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fk_id_receita", nullable = false)
    private Receita receita;

    @Column(name = "data_inclusao", nullable = false)
    private LocalDate dataInclusao;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public PlanoReceita() {}

    public Long getIdPlanoReceita() { return idPlanoReceita; }
    public void setIdPlanoReceita(Long idPlanoReceita) { this.idPlanoReceita = idPlanoReceita; }
    public PlanoAlimentar getPlano() { return plano; }
    public void setPlano(PlanoAlimentar plano) { this.plano = plano; }
    public Receita getReceita() { return receita; }
    public void setReceita(Receita receita) { this.receita = receita; }
    public LocalDate getDataInclusao() { return dataInclusao; }
    public void setDataInclusao(LocalDate dataInclusao) { this.dataInclusao = dataInclusao; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Long getFkIdPlano() {
        return plano != null ? plano.getIdPlano() : null;
    }
    public Long getFkIdReceita() {
        return receita != null ? receita.getIdReceita() : null;
    }
    public String getReceitaTitulo() {
        return receita != null ? receita.getTitulo() : null;
    }
    public String getReceitaIngredientes() {
        return receita != null ? receita.getIngredientes() : null;
    }
    public java.math.BigDecimal getReceitaCalorias() {
        return receita != null ? receita.getCalorias() : null;
    }

     @Column(name = "tipo_refeicao", length = 50)
    private String tipoRefeicao;

    public String getTipoRefeicao() { return tipoRefeicao; }
    public void setTipoRefeicao(String tipoRefeicao) { this.tipoRefeicao = tipoRefeicao; }
}
