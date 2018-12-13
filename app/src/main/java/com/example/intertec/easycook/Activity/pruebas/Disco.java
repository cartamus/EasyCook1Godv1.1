package com.example.intertec.easycook.Activity.pruebas;

/**
 * Created by Juanmi on 07/12/2017.
 */

public class Disco {

    private String titulo;
    private String anyo;

    public Disco(){

    }

    public Disco(String titulo, String anyo) {
        this.titulo = titulo;
        this.anyo = anyo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnyo() {
        return anyo;
    }

    public void setAnyo(String anyo) {
        this.anyo = anyo;
    }

    @Override
    public String toString() {
        return "Disco{" +
                "titulo='" + titulo + '\'' +
                ", anyo='" + anyo + '\'' +
                '}';
    }
}
