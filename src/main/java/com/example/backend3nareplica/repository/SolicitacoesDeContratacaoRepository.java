package com.example.backend3nareplica.repository;

import com.example.backend3nareplica.entity.SolicitacoesDeContratacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolicitacoesDeContratacaoRepository extends JpaRepository<SolicitacoesDeContratacao, Long> {
    List<SolicitacoesDeContratacao> findByUsuarioIdUser(Long idUser);
    List<SolicitacoesDeContratacao> findByNutricionistaIdNutri(Long idNutri);
}