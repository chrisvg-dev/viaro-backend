package com.viaro.prueba_tecnica.controller;

import com.viaro.prueba_tecnica.dto.AlumnoDto;
import com.viaro.prueba_tecnica.dto.GradoDto;
import com.viaro.prueba_tecnica.dto.Mensaje;
import com.viaro.prueba_tecnica.models.Alumno;
import com.viaro.prueba_tecnica.models.Grado;
import com.viaro.prueba_tecnica.models.Profesor;
import com.viaro.prueba_tecnica.services.AlumnosServ;
import com.viaro.prueba_tecnica.services.GradoServ;
import com.viaro.prueba_tecnica.services.ProfesorServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/grado")
public class GradoController {

    @Autowired
    private GradoServ gradoServ;
    @Autowired
    private ProfesorServ profesorServ;


    /** Este método permite al usuario visualizar la información de todos
     * los alumnos existentes
     *
     * @return devuelve la lista de alumnos existentes en la abse de datos
     */
    @GetMapping
    public List<Grado> get(){ return this.gradoServ.listar(); }

    @PostMapping
    public ResponseEntity<Mensaje> guardar(@RequestBody GradoDto grado) {

        if (grado.getProfesor() == null || grado.getProfesor() < 1) {
            Mensaje mensaje = new Mensaje("Debes seleccionar un docente...");
            return new ResponseEntity<Mensaje>(mensaje, HttpStatus.NOT_FOUND);
        }

        if (!this.gradoServ.existePorNombre(grado.getNombre())) {
            Grado nuevoGrado = new Grado();
            nuevoGrado.setNombre(grado.getNombre());
            Profesor profesor = profesorServ.buscarPorId(grado.getProfesor());
            nuevoGrado.setProfesor(profesor);
            this.gradoServ.guardar(nuevoGrado);

            Mensaje mensaje = new Mensaje("El grado se ha registrado de forma correcta!!!");
            return new ResponseEntity<Mensaje>(mensaje, HttpStatus.OK);
        } else {
            Mensaje mensaje = new Mensaje("El grado ya existe!!!");
            return new ResponseEntity<Mensaje>(mensaje, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable("id") Integer id) {
        this.gradoServ.eliminar(id);
        Mensaje mensaje = new Mensaje("El grado se ha eliminado de forma correcta!!!");
        return new ResponseEntity<Mensaje>(mensaje, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensaje> editar(@PathVariable("id") Integer id,  @RequestBody GradoDto grado) {

        if (this.gradoServ.existePorId(id)) {
            Grado editarGrado = new Grado();
            editarGrado.setId(id);
            editarGrado.setNombre(grado.getNombre());
            Profesor profesor = profesorServ.buscarPorId(grado.getProfesor());
            editarGrado.setProfesor(profesor);
            this.gradoServ.guardar(editarGrado);

            Mensaje mensaje = new Mensaje("El grado se ha editado de forma correcta!!!");
            return new ResponseEntity<Mensaje>(mensaje, HttpStatus.OK);

        } else {
            Mensaje mensaje = new Mensaje("ERROR EN LA EDICION DEL GRADO!!!");
            return new ResponseEntity<Mensaje>(mensaje, HttpStatus.NOT_FOUND);
        }
    }
}
