package com.example.intertec.easycook.Activity.pruebas;

public class Lenguaje {

    String logo, nombre, sistema;
    int version;

    public Lenguaje() {
    }

    public Lenguaje(String logo, String nombre, String sistema, int version) {
        this.logo = logo;
        this.nombre = nombre;
        this.sistema = sistema;
        this.version = version;
    }

    public String getLogo() {
        return logo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSistema() {
        return sistema;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "Lenguaje{" +
                "logo='" + logo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", sistema='" + sistema + '\'' +
                ", version=" + version +
                '}';
    }
}