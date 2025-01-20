package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

public class Fragmento1 extends ListFragment {
    private Callbacks mCallbacks;

    public Fragmento1() {
        // Constructor sin argumentos necesario para instanciación del fragmento
        this.mCallbacks = Callbacks.CallbacksVacios;
    }

    public Fragmento1(Callbacks mCallbacks) {
        this.mCallbacks = mCallbacks;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new Adaptador(getActivity(), R.layout.layout_listado, Contenido.ent_lista) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada instanceof Contenido.Lista_entrada) {
                    Contenido.Lista_entrada listaEntrada = (Contenido.Lista_entrada) entrada;

                    TextView textoSuperior = view.findViewById(R.id.textoTitulo);
                    textoSuperior.setText(listaEntrada.titulo);

                    ImageView imagenEntrada = view.findViewById(R.id.imagenLista);
                    imagenEntrada.setImageResource(listaEntrada.imagen);
                }
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof Callbacks) {
            mCallbacks = (Callbacks) activity;
        } else {
            throw new ClassCastException(activity.toString() + " debe implementar Fragmento1.Callbacks");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = Callbacks.CallbacksVacios;
    }

    public interface Callbacks {
        // Implementación vacía por defecto para evitar NPE
        static Callbacks CallbacksVacios = id -> {};

        void onEntradaSeleccionada(String id);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (mCallbacks != null) {
            mCallbacks.onEntradaSeleccionada(Contenido.ent_lista.get(position).id);
        }
    }
}
