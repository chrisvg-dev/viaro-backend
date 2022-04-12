package com.viaro.prueba_tecnica.repository;

import com.viaro.prueba_tecnica.models.Grado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradoRep extends JpaRepository<Grado, Integer> {
    boolean existsById(Integer id);
    boolean existsByNombre(String nombre);
}
