package com.example.backend3nareplica.repository;
import com.example.backend3nareplica.entity.PlanoReceita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlanoReceitaRepository extends JpaRepository<PlanoReceita, Long> {
    List<PlanoReceita> findByPlanoIdPlano(Long idPlano);
}
