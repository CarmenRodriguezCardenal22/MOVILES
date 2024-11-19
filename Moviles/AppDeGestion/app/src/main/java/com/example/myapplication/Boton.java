package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Boton extends AppCompatActivity {

    private ListView lista;
    private TextView texto;
    private RadioButton radio_button_pulsado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_boton);
        lista=(ListView) findViewById(R.id.lista);
        ArrayList<Encapsulador> datos=new ArrayList<Encapsulador>();
        datos.add(new Encapsulador(R.drawable.camiseta, "CAMISETA", "Tallas: XS, S, M, L, XL\nColores: Negro, Blanco, Rojo, Beige", true));
        datos.add(new Encapsulador(R.drawable.pantalon, "PANTALON", "Tallas: 36, 38, 40, 42, 44, 46, 48\nColores: Azul, Gris, Negro, Blanco", false));
        datos.add(new Encapsulador(R.drawable.botas, "BOTAS", "Tallas: 36, 37, 38, 39, 40, 41, 42\nColores: Negro, Blanco, Marrón", false));
        datos.add(new Encapsulador(R.drawable.chaqueta, "CHAQUETA", "Tallas: XS, S, M, L, XL\nColores: Negro, Blanco, Rojo", false));
        lista.setAdapter(new Adaptador(this, R.layout.activity_boton2, datos){
            @Override
            public void onEntrada(Object entrada, View view){
                TextView texto_superior_entrada = (TextView) view.findViewById(R.id.texto_titulo);
                TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.texto_datos);
                ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imagen);
                RatingBar ratingBar=(RatingBar) view.findViewById(R.id.ratingBar);
                texto_superior_entrada.setText(((Encapsulador) entrada).getTitulo());
                texto_inferior_entrada.setText(((Encapsulador) entrada).getContenido());
                imagen_entrada.setImageResource(((Encapsulador) entrada).getImagen());
                Encapsulador encapsulador = (Encapsulador) entrada;
            }
        });
    }

    public class Encapsulador{
        private int imagen;
        private String titulo, texto;
        boolean dato1;
        public Encapsulador(int idImagen, String textoTitulo, String textoContenido, boolean favorito){
            this.imagen=idImagen;
            this.titulo=textoTitulo;
            this.texto=textoContenido;
            this.dato1=favorito;
        }
        public String getTitulo(){return  titulo;}
        public String getContenido(){return texto;}
        public int getImagen(){return imagen;}
    }
}