package com.viaro.prueba_tecnica.dto;

public class GradoDto {
    private Integer id;
    private String nombre;
    private Integer profesor;

    public GradoDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getProfesor() {
        return profesor;
    }

    public void setProfesor(Integer profesor) {
        this.profesor = profesor;
    }
}
