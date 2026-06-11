package com.example.backend3nareplica.repository;

import com.example.backend3nareplica.entity.VinculoNutriPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VinculoNutriPacienteRepository extends JpaRepository<VinculoNutriPaciente, Long> {
    List<VinculoNutriPaciente> findByUsuarioIdUser(Long idUser);
    List<VinculoNutriPaciente> findByNutricionistaIdNutri(Long idNutri);
}