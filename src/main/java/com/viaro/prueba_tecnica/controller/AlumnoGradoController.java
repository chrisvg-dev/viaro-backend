package com.viaro.prueba_tecnica.controller;

import com.viaro.prueba_tecnica.dto.GradoAlumnoDto;
import com.viaro.prueba_tecnica.dto.GradoDto;
import com.viaro.prueba_tecnica.dto.Mensaje;
import com.viaro.prueba_tecnica.models.Alumno;
import com.viaro.prueba_tecnica.models.AlumnoGrado;
import com.viaro.prueba_tecnica.models.Grado;
import com.viaro.prueba_tecnica.models.Profesor;
import com.viaro.prueba_tecnica.services.AlumnoGradoServ;
import com.viaro.prueba_tecnica.services.AlumnosServ;
import com.viaro.prueba_tecnica.services.GradoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/alumno-grado")
public class AlumnoGradoController {

    @Autowired
    AlumnoGradoServ gradoAlumnoServ;

    @Autowired
    AlumnosServ alumnosServ;

    @Autowired
    GradoServ gradoServ;

    @GetMapping
    public List<AlumnoGrado> get(){ return this.gradoAlumnoServ.listar(); }

    @PostMapping
    public ResponseEntity<Mensaje> guardar(@RequestBody GradoAlumnoDto gradoAlumno) {

        List<AlumnoGrado> alumnosConGrado = this.gradoAlumnoServ.listar();
        List<Alumno> alumnosGrado =
                alumnosConGrado.stream().map(item -> item.getAlumno())
                        .filter(x -> x.getId() == gradoAlumno.getAlumno())
                        .collect(Collectors.toList());

        if (alumnosGrado.size() > 0) {
            Mensaje mensaje = new Mensaje("El alumno ya tiene asignado un grado...!!!");
            return new ResponseEntity<Mensaje>(mensaje, HttpStatus.BAD_REQUEST);
        }

        AlumnoGrado nuevo = new AlumnoGrado();
        nuevo.setSeccion(gradoAlumno.getSeccion());
        Alumno a = alumnosServ.buscarPorId(gradoAlumno.getAlumno());
        nuevo.setAlumno(a);
        Grado grado = gradoServ.buscarPorId(gradoAlumno.getGrado());
        nuevo.setGrado(grado);
        this.gradoAlumnoServ.agregar(nuevo);
        Mensaje mensaje = new Mensaje("EL GRADO HA SIDO ASIGNADO AL ALUMNO!!!");
        return new ResponseEntity<Mensaje>(mensaje, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensaje> editar(@PathVariable("id") Integer id, @RequestBody GradoAlumnoDto gradoAlumno) {
        AlumnoGrado nuevo = new AlumnoGrado();
        nuevo.setId(id);
        nuevo.setSeccion(gradoAlumno.getSeccion());
        Alumno a = alumnosServ.buscarPorId(gradoAlumno.getAlumno());
        nuevo.setAlumno(a);
        Grado grado = gradoServ.buscarPorId(gradoAlumno.getGrado());
        nuevo.setGrado(grado);
        this.gradoAlumnoServ.agregar(nuevo);
        Mensaje mensaje = new Mensaje("EL GRADO DEL ALUMNO HA SIDO MODIFICADO!!!");
        return new ResponseEntity<Mensaje>(mensaje, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable("id") Integer id) {
        this.gradoAlumnoServ.eliminar(id);
        Mensaje mensaje = new Mensaje("Se ha eliminado el grado del alumno!!!");
        return new ResponseEntity<Mensaje>(mensaje, HttpStatus.OK);
    }
}
