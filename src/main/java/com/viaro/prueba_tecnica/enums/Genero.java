package com.viaro.prueba_tecnica.enums;

public enum Genero {
    HOMBRE("HOMBRE"), MUJER("MUJER");

    public final String text;

    Genero(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
