package com.viaro.prueba_tecnica.services;

import com.viaro.prueba_tecnica.models.Alumno;
import com.viaro.prueba_tecnica.models.Profesor;
import com.viaro.prueba_tecnica.repository.AlumnoRep;
import com.viaro.prueba_tecnica.repository.ProfesorRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProfesorServ {

    @Autowired
    ProfesorRep profesorRep;

    public List<Profesor> listar() {
        return this.profesorRep.findAll();
    }

    public Profesor buscarPorId(Integer id) {
        return this.profesorRep.findById(id).get();
    }

    public void guardar(Profesor profesor){
        this.profesorRep.save(profesor);
    }

    public void editar(Profesor profesor){
        this.profesorRep.save(profesor);
    }

    public void eliminar(Integer id){
        this.profesorRep.deleteById(id);
    }

    public boolean existePorId(Integer id) {
        return this.profesorRep.existsById(id);
    }

}
