package com.viaro.prueba_tecnica.services;

import com.viaro.prueba_tecnica.models.AlumnoGrado;
import com.viaro.prueba_tecnica.repository.AlumnoGradoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlumnoGradoServ {

    @Autowired
    AlumnoGradoRep alumnoGradoRep;

    public void agregar(AlumnoGrado nuevo) {
        this.alumnoGradoRep.save(nuevo);
    }

    public List<AlumnoGrado> listar(){
        return this.alumnoGradoRep.findAll();
    }

    public void eliminar(Integer id){
        this.alumnoGradoRep.deleteById(id);
    }
}
