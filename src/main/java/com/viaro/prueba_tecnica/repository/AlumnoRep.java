package com.viaro.prueba_tecnica.repository;

import com.viaro.prueba_tecnica.models.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRep extends JpaRepository<Alumno, Integer> {
    boolean existsById(Integer id);
}
