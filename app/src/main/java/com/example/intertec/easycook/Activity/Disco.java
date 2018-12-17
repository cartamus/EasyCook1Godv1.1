package com.example.intertec.easycook.Activity;

/**
 * Created by Juanmi on 07/12/2017.
 */

public class Disco {

    private String receta,categoria,autor;
    public Disco(){
    }
    public Disco(String receta, String categoria,String autor) {
        this.receta=receta;
        this.categoria=categoria;
        this.autor=autor;
    }
    public String getReceta() {
        return receta;
    }
    public void setReceta(String receta) {
        this.receta = receta;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) { this.autor = autor; }
    @Override
    public String toString() {
        return "Recetas{" +
                "receta=" + receta + '\'' +
                "categoria=" + categoria + '\'' +
                "autor=" + autor + '\'' +
                "}";
    }
}
