package com.example.backend3nareplica.repository;

import com.example.backend3nareplica.entity.PlanoAlimentar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlanoAlimentarRepository extends JpaRepository<PlanoAlimentar, Long> {
    List<PlanoAlimentar> findByUsuarioIdUser(Long idUser);
    List<PlanoAlimentar> findByNutricionistaIdNutri(Long idNutri);
}