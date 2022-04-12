package com.viaro.prueba_tecnica.services;

import com.viaro.prueba_tecnica.models.Alumno;
import com.viaro.prueba_tecnica.models.Grado;
import com.viaro.prueba_tecnica.models.Profesor;
import com.viaro.prueba_tecnica.repository.AlumnoRep;
import com.viaro.prueba_tecnica.repository.GradoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GradoServ {
    @Autowired
    GradoRep gradoRep;

    public List<Grado> listar() {
        return this.gradoRep.findAll();
    }
    public Grado buscarPorId(Integer id) {
        return this.gradoRep.findById(id).get();
    }


    public void guardar(Grado alumno){
        this.gradoRep.save(alumno);
    }

    public void editar(Grado alumno){
        this.gradoRep.save(alumno);
    }

    public void eliminar(Integer id){
        this.gradoRep.deleteById(id);
    }

    public boolean existePorId(Integer id) {
        return this.gradoRep.existsById(id);
    }

    public boolean existePorNombre(String nombre) {
        return this.gradoRep.existsByNombre(nombre);
    }
}
