package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.ListFragment;

public class Fragmento1 extends ListFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new Adaptador(getActivity(), R.layout.layout_listado, Contenido.ent_lista) {
            @Override
            public void onEntrada(Object entrada, View view) {
                TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textoTitulo);
                texto_superior_entrada.setText(((Contenido.Lista_entrada) entrada).textoEncima);
                ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imagenLista);
                imagen_entrada.setImageResource(((Contenido.Lista_entrada) entrada).idImagen);
            }
        });
    }


}
