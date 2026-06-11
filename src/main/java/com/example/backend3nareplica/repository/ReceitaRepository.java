package com.example.backend3nareplica.repository;

import com.example.backend3nareplica.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByNutricionistaIdNutri(Long idNutri);
    List<Receita> findByTagsContainingIgnoreCase(String tag);
}