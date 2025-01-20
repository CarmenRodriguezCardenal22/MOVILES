package com.example.myapplication;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Contenido {
    public static ArrayList<Lista_entrada> ent_lista = new ArrayList<>();
    public static Map<String, Lista_entrada> ent_lista_hashmap = new HashMap<>();

    public static class Lista_entrada {
        int imagen;
        String titulo, contenido, fecha, id;

        public Lista_entrada(String id, int imagen, String titulo, String contenido, String fecha) {
            this.id = id;
            this.imagen = imagen;
            this.titulo = titulo;
            this.contenido = contenido;
            this.fecha = fecha;
        }
    }

    static {
        ponerEntrada(new Lista_entrada("0", R.drawable.camiseta, "CAMISETA", "Tallas: XS, S, M, L, XL\\nColores: Negro, Blanco, Rojo, Beige", obtenerFechaActual()));
        ponerEntrada(new Lista_entrada("1", R.drawable.pantalon, "PANTALON", "Tallas: 36, 38, 40, 42, 44, 46, 48\\nColores: Azul, Gris, Negro, Blanco", obtenerFechaActual()));
        ponerEntrada(new Lista_entrada("2", R.drawable.botas, "BOTAS", "Tallas: 36, 37, 38, 39, 40, 41, 42\\nColores: Negro, Blanco, Marrón", obtenerFechaActual()));
        ponerEntrada(new Lista_entrada("3", R.drawable.chaqueta, "CHAQUETA", "Tallas: XS, S, M, L, XL\\nColores: Negro, Blanco, Rojo", obtenerFechaActual()));
    }


    private static void ponerEntrada(Lista_entrada entrada) {
        ent_lista.add(entrada);
        ent_lista_hashmap.put(entrada.id, entrada);
    }
    static private String obtenerFechaActual() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        return day + "/" + month + "/" + year;
    }
}
