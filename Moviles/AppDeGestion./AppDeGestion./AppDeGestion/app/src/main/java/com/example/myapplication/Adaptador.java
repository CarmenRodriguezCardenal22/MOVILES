package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class Adaptador extends BaseAdapter {
    Context context;
    private int layoutResourceId;
    private List<?> datos;

    public Adaptador(Context context, int layoutResourceId, List<?> datos) {
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.datos = datos;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }
        onEntrada(getItem(position), convertView);
        return convertView;
    }

    public abstract void onEntrada(Object entrada, View view);
}