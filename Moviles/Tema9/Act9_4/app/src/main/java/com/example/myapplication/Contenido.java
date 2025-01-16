package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Contenido {
    public static ArrayList<Lista_entrada> ent_lista = new ArrayList<>();
    public static Map<String, Lista_entrada> ent_lista_hashmap = new HashMap<>();

    public static class Lista_entrada {
        public String id;
        public String textoEncima;
        public String textoDebajo;
        public int idImagen;

        public Lista_entrada(String id, int idImagen, String textoEncima, String textoDebajo) {
            this.id = id;
            this.textoEncima = textoEncima;
            this.textoDebajo = textoDebajo;
            this.idImagen = idImagen;
        }
    }

    static {
        ponerEntrada(new Lista_entrada("0", R.drawable.ima1, "DONUTS", "El 15 de septiembre de 2009, fue lanzado el SDK de Android 1.6 Donut, basado en el núcleo Linux 2.6.29."));
        ponerEntrada(new Lista_entrada("1", R.drawable.ima2, "FROYO", "El 20 de mayo de 2010, El SDK de Android 2.2 Froyo (Yogur helado) fue lanzado, basado en el núcleo Linux 2.6.32."));
        ponerEntrada(new Lista_entrada("2", R.drawable.ima3, "GINGERBREAD", "El 6 de diciembre de 2010, el SDK de Android 2.3 Gingerbread (Pan de Jengibre) fue lanzado, basado en el núcleo Linux 2.6.35."));
        ponerEntrada(new Lista_entrada("3", R.drawable.ima4, "HONEYCOMB", "El 22 de febrero de 2011, sale el SDK de Android 3.0 Honeycomb (Panal de Miel)."));
        ponerEntrada(new Lista_entrada("4", R.drawable.ima5, "ICE CREAM", "El SDK para Android 4.0.0 Ice Cream Sandwich fue lanzado públicamente el 12 de octubre de 2011."));
        ponerEntrada(new Lista_entrada("5", R.drawable.ima6, "JELLY BEAN", "Google anunció Android 4.1 Jelly Bean el 30 de junio de 2012."));
        ponerEntrada(new Lista_entrada("6", R.drawable.ima7, "KITKAT", "Su nombre se debe a la chocolatina KitKat. Posibilidad de impresión mediante WiFi."));
        ponerEntrada(new Lista_entrada("7", R.drawable.ima8, "LOLLIPOP", "Incluye Material Design, un diseño intrépido, colorido y sensible."));
    }

    private static void ponerEntrada(Lista_entrada entrada) {
        ent_lista.add(entrada);
        ent_lista_hashmap.put(entrada.id, entrada);
    }
}
