package com.example.intertec.easycook.Activity.pruebas;

/**
 * Created by Juanmi on 07/12/2017.
 */

public class Cancion {

    private String cancion;
    private String disco;

    public Cancion(){

    }

    public Cancion(String cancion, String disco) {
        this.cancion = cancion;
        this.disco = disco;
    }

    public String getCancion() {
        return cancion;
    }

    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    public String getDisco() {
        return disco;
    }

    public void setDisco(String disco) {
        this.disco = disco;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "cancion='" + cancion + '\'' +
                ", disco='" + disco + '\'' +
                '}';
    }
}

