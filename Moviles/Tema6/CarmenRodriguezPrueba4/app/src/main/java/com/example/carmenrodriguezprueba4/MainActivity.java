package com.example.carmenrodriguezprueba4;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<Encapsulador> datos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Encapsulador> datos=new ArrayList<Encapsulador>();
        datos.add(new Encapsulador(R.drawable.caravaggio, "CARVAGGIO", "Pintor italiano entre los años de 1593 y 1610. Es considerado como el primer gran exponente de la pintura del Barroco.", "http://es.wikipedia.org/wiki/Caravaggio"));
        datos.add(new Encapsulador(R.drawable.rafael, "RAFAEL SANZIO", "Pintor y arquitecto italiano del Renacimiento, realizó importantes aportes en la arquitectura y, como inspector de antigüedades.", "http://es.wikipedia.org/wiki/Rafael_Sanzio"));
        datos.add(new Encapsulador(R.drawable.velazquez, "VELAZQUEZ", "Pintor Barroco nacido en Sevilla en 1599, es considerado uno de los máximos exponentes de la pintura española y maestro de la pintura universal.", "http://es.wikipedia.org/wiki/Diego_Vel%C3%A1zquez"));
        datos.add(new Encapsulador(R.drawable.miguelangel, "MIGUEL ANGEL", "Arquitecto, escultor y pintor italiano renacentista, considerado uno de los más grandes artistas de la historia.", "http://es.wikipedia.org/wiki/Miguel_%C3%81ngel"));
        datos.add(new Encapsulador(R.drawable.rembrant, "REMBRANDT", "Pintor y grabador holandés. La historia del arte le considera uno de los mayores maestros barrocos de la pintura y el grabado.", "http://es.wikipedia.org/wiki/Rembrandt"));
        datos.add(new Encapsulador(R.drawable.boticelli, "BOTICELLI", "Apodado Sandro Botticelli, fue un pintor cuatrocentista italiano.su obra se ha considerado representativa de la pintura del primer Renacimiento.", "http://es.wikipedia.org/wiki/Sandro_Botticelli"));
        datos.add(new Encapsulador(R.drawable.leonardo, "LEONARDO DA VINCI", "Notable polímata del Renacimiento italiano (a la vez anatomista, arquitecto, artista, botánico, científico, escritor, escultor, filósofo,ingeniero...)", "http://es.wikipedia.org/wiki/Leonardo_da_Vinci"));
        datos.add(new Encapsulador(R.drawable.renoir, "RENOIR", "Pintor francés impresionista, interesado por la pintura de cuerpos femeninos en paisajes, inspirados a menudo en pinturas clásicas renacentistas y barrocas.", "http://es.wikipedia.org/wiki/Pierre-Auguste_Renoir"));

        Spinner listado = findViewById(R.id.spinner);
        WebView wikipedia = findViewById(R.id.webview);

        wikipedia.setWebViewClient(new WebViewClient());
        wikipedia.getSettings().setJavaScriptEnabled(true);

        ArrayList<String> titulos = new ArrayList<>();
        for (Encapsulador e : datos) {
            titulos.add(e.getTitulo());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, titulos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listado.setAdapter(adapter);

        listado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String url = datos.get(position).getContenido();
                wikipedia.loadUrl(url);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        listado.setAdapter(new Adaptador(this, R.layout.activity_main2, datos){
            @Override
            public void onEntrada(Object entrada, View view){
                TextView texto_superior_entrada = (TextView) view.findViewById(R.id.texto_titulo);
                TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.texto_datos);
                ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imagen);
                texto_superior_entrada.setText(((Encapsulador) entrada).getTitulo());
                texto_inferior_entrada.setText(((Encapsulador) entrada).getContenido());
                imagen_entrada.setImageResource(((Encapsulador) entrada).getImagen());
            }
        });
    }
    public class Encapsulador{
        private int imagen;
        private String titulo, texto, url;
        public Encapsulador(int idImagen, String textoTitulo, String textoContenido, String url) {
            this.imagen = idImagen;
            this.titulo = textoTitulo;
            this.texto = textoContenido;
            this.url=url;
        }
        public String getTitulo(){return  titulo;}
        public String getContenido(){return texto;}
        public int getImagen(){return imagen;}
        public String getUrl(){return url;}
    }
}

