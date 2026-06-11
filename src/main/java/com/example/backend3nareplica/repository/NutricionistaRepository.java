package com.example.backend3nareplica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend3nareplica.entity.Nutricionista;
import java.util.Optional;

public interface NutricionistaRepository extends JpaRepository<Nutricionista, Long> {
    Optional<Nutricionista> findByEmailProfissional(String emailProfissional);
}