package com.viaro.prueba_tecnica.services;

import com.viaro.prueba_tecnica.models.Alumno;
import com.viaro.prueba_tecnica.models.Profesor;
import com.viaro.prueba_tecnica.repository.AlumnoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlumnosServ {
    @Autowired
    AlumnoRep alumnoRep;

    public List<Alumno> listar() {
        return this.alumnoRep.findAll();
    }

    public Alumno buscarPorId(Integer id) {
        return this.alumnoRep.findById(id).get();
    }


    public void guardar(Alumno alumno){
        this.alumnoRep.save(alumno);
    }

    public void editar(Alumno alumno){
        this.alumnoRep.save(alumno);
    }

    public void eliminar(Integer id){
        this.alumnoRep.deleteById(id);
    }

    public boolean existePorId(Integer id) {
        return this.alumnoRep.existsById(id);
    }
}
