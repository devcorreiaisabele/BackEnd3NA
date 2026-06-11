package com.example.backend3nareplica.repository;

import com.example.backend3nareplica.entity.Evolucao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EvolucaoRepository extends JpaRepository<Evolucao, Long> {
    List<Evolucao> findByUsuarioIdUser(Long idUser);
}