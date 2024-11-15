package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ArrayList<Encapsulador> datos= new ArrayList<Encapsulador>();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public class Encapsulador{
        private int imagen;
        private String titulo, texto;
        public Encapsulador(int idImagen, String textoTitulo, String textoContenido){
            this.imagen=idImagen;
            this.titulo=textoTitulo;
            this.texto=textoContenido;
        }
        public String getTitulo(){return  titulo;}
        public String getContenido(){return texto;}
        public int getImagen(){return imagen;}
    }

}