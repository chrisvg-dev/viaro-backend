package com.viaro.prueba_tecnica.repository;

import com.viaro.prueba_tecnica.models.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRep extends JpaRepository<Profesor, Integer> {
    boolean existsById(Integer id);
}
