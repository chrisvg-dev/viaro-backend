package com.viaro.prueba_tecnica.repository;

import com.viaro.prueba_tecnica.models.AlumnoGrado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoGradoRep extends JpaRepository<AlumnoGrado, Integer> {
}
