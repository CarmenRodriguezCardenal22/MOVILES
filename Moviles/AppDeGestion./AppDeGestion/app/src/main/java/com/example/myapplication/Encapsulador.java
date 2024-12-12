package com.example.myapplication;

import java.io.Serializable;

public class Encapsulador implements Serializable {
    private int imagen;
    private String titulo, contenido, fecha;
    private boolean favorito;

    public Encapsulador(int imagen, String titulo, String contenido, String fecha, boolean favorito) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.favorito = favorito;
    }

    public int getImagen() { return imagen; }

    public String getTitulo() { return titulo; }

    public String getContenido() { return contenido; }

    public String getFecha() { return fecha; }

    public boolean isFavorito() { return favorito; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public void setContenido(String contenido) { this.contenido = contenido; }
}
