package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragmento3 extends Fragment {
    public static final String ARG_ID_ENTRADA_SELECCIONADA = "item_id"; // Nombre constante en mayúsculas
    private Contenido.Lista_entrada miItem;

    public Fragmento3() {
        // Constructor vacío requerido para Fragment
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Recuperar el argumento y buscar el objeto en el mapa
        if (getArguments() != null && getArguments().containsKey(ARG_ID_ENTRADA_SELECCIONADA)) {
            String itemId = getArguments().getString(ARG_ID_ENTRADA_SELECCIONADA);
            miItem = Contenido.ent_lista_hashmap.get(itemId);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar la vista del diseño del detalle
        View rootView = inflater.inflate(R.layout.layout_detalle, container, false);

        // Verificar si el objeto miItem no es nulo antes de acceder a sus valores
        if (miItem != null) {
            TextView textoTitulo = rootView.findViewById(R.id.textoTitulo);
            TextView textoContenido = rootView.findViewById(R.id.textoContenido);
            ImageView imagen = rootView.findViewById(R.id.imagen);

            textoTitulo.setText(miItem.titulo);
            textoContenido.setText(miItem.contenido);
            imagen.setImageResource(miItem.imagen);
        }

        return rootView;
    }
}
