package com.viaro.prueba_tecnica.controller;

import com.viaro.prueba_tecnica.dto.AlumnoDto;
import com.viaro.prueba_tecnica.dto.Mensaje;
import com.viaro.prueba_tecnica.dto.ProfesorDto;
import com.viaro.prueba_tecnica.models.Alumno;
import com.viaro.prueba_tecnica.models.Profesor;
import com.viaro.prueba_tecnica.services.ProfesorServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    ProfesorServ profesorServ;

    @GetMapping
    public List<Profesor> get(){ return this.profesorServ.listar(); }

    @PostMapping
    public ResponseEntity<Mensaje> guardar(@RequestBody ProfesorDto profesor) {

        Profesor nuevoProfesor = new Profesor();
        nuevoProfesor.setNombre(profesor.getNombre());
        nuevoProfesor.setApellidos(profesor.getApellidos());
        nuevoProfesor.setGenero(profesor.getGenero());

        this.profesorServ.guardar(nuevoProfesor);

        Mensaje mensaje = new Mensaje("El profesor se ha registrado de forma correcta!!!");
        return new ResponseEntity<Mensaje>(mensaje, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable("id") Integer id) {
        if (this.profesorServ.existePorId(id)) {
            this.profesorServ.eliminar(id);
            Mensaje mensaje = new Mensaje("El profesor se ha eliminado de forma correcta!!!");
            return new ResponseEntity<Mensaje>(mensaje, HttpStatus.OK);
        } else {
            Mensaje mensaje = new Mensaje("El profesor no existe!!!");
            return new ResponseEntity<Mensaje>(mensaje, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensaje> editar(@PathVariable("id") Integer id,  @RequestBody ProfesorDto profesorDto) {

        if (this.profesorServ.existePorId(id)) {
            Profesor editarProfesor = new Profesor();
            editarProfesor.setId(id);
            editarProfesor.setNombre(profesorDto.getNombre());
            editarProfesor.setApellidos(profesorDto.getApellidos());
            editarProfesor.setGenero(profesorDto.getGenero());
            this.profesorServ.guardar(editarProfesor);

            Mensaje mensaje = new Mensaje("El profesor se ha editado de forma correcta!!!");
            return new ResponseEntity<Mensaje>(mensaje, HttpStatus.OK);

        } else {
            Mensaje mensaje = new Mensaje("ERROR EN LA EDICION DEL PROFESOR!!!");
            return new ResponseEntity<Mensaje>(mensaje, HttpStatus.NOT_FOUND);
        }
    }
}
