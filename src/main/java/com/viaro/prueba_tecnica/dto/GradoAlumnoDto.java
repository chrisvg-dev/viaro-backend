package com.viaro.prueba_tecnica.dto;

public class GradoAlumnoDto {
    private Integer id;
    private Integer alumno;
    private Integer grado;
    private String seccion;

    public GradoAlumnoDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlumno() {
        return alumno;
    }

    public void setAlumno(Integer alumno) {
        this.alumno = alumno;
    }

    public Integer getGrado() {
        return grado;
    }

    public void setGrado(Integer grado) {
        this.grado = grado;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
}
