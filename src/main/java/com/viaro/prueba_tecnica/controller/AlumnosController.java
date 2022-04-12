package com.viaro.prueba_tecnica.controller;

import com.viaro.prueba_tecnica.dto.AlumnoDto;
import com.viaro.prueba_tecnica.dto.Mensaje;
import com.viaro.prueba_tecnica.models.Alumno;
import com.viaro.prueba_tecnica.services.AlumnosServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/alumnos")
public class AlumnosController {

    @Autowired
    private AlumnosServ alumnosServ;


    /** Este método permite al usuario visualizar la información de todos
     * los alumnos existentes
     *
     * @return devuelve la lista de alumnos existentes en la abse de datos
     */
    @GetMapping
    public List<Alumno> get(){ return this.alumnosServ.listar(); }

    @PostMapping
    public ResponseEntity<Mensaje> guardar(@RequestBody AlumnoDto alumno) {
        Alumno nuevoAlumno = new Alumno();
        nuevoAlumno.setNombre(alumno.getNombre());
        nuevoAlumno.setApellidos(alumno.getApellidos());
        nuevoAlumno.setGenero(alumno.getGenero());
        LocalDate fecha = LocalDate.parse(alumno.getFechaNacimiento());
        nuevoAlumno.setFechaNacimiento(fecha);
        this.alumnosServ.guardar(nuevoAlumno);
        Mensaje mensaje = new Mensaje("El alumno se ha registrado de forma correcta!!!");
        return new ResponseEntity<Mensaje>(mensaje, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable("id") Integer id) {
        if (this.alumnosServ.existePorId(id)) {
            this.alumnosServ.eliminar(id);
            Mensaje mensaje = new Mensaje("El alumno se ha eliminado de forma correcta!!!");
            return new ResponseEntity<Mensaje>(mensaje, HttpStatus.OK);
        } else {
            Mensaje mensaje = new Mensaje("El ID no existe!!!");
            return new ResponseEntity<Mensaje>(mensaje, HttpStatus.OK);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Mensaje> editar(@PathVariable("id") Integer id,  @RequestBody AlumnoDto alumno) {
        if (this.alumnosServ.existePorId(id)) {
            Alumno editarAlumno = new Alumno();
            editarAlumno.setId(id);
            editarAlumno.setNombre(alumno.getNombre());
            editarAlumno.setApellidos(alumno.getApellidos());
            editarAlumno.setGenero(alumno.getGenero());
            LocalDate fecha = LocalDate.parse(alumno.getFechaNacimiento());
            editarAlumno.setFechaNacimiento(fecha);
            this.alumnosServ.editar(editarAlumno);

            Mensaje mensaje = new Mensaje("El alumno se ha editado de forma correcta!!!");
            return new ResponseEntity<Mensaje>(mensaje, HttpStatus.OK);
        } else {
            Mensaje mensaje = new Mensaje("EL USUARIO NO EXISTE!!!");
            return new ResponseEntity<Mensaje>(mensaje, HttpStatus.NOT_FOUND);
        }
    }
}
