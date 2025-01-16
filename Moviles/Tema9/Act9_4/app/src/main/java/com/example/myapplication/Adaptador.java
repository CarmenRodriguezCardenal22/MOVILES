package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public abstract class Adaptador extends BaseAdapter {
    private ArrayList<?> entradas;
    private int layoutId;
    private Context contexto;

    public Adaptador(Context contexto, int layoutId, ArrayList<?> entradas) {
        this.contexto = contexto;
        this.entradas = entradas;
        this.layoutId = layoutId;
    }

    @Override
    public View getView(int posicion, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(contexto);
            convertView = inflater.inflate(layoutId, parent, false);
        }
        onEntrada(entradas.get(posicion), convertView);
        return convertView;
    }

    @Override
    public int getCount() {
        return entradas.size();
    }

    @Override
    public Object getItem(int posicion) {
        return entradas.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }

    public abstract void onEntrada(Object entrada, View view);
}
