package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ListView listado=(ListView) findViewById(R.id.miLista);
        final String[] datos=new String[]{"España","Francia","Alemania","Inglaterra","Bruselas","Noruega","Finlandia","Rusia","Italia","Ucrania"};
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        listado.setAdapter(adaptador);
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicion, long id){
                String elemento=(String) parent.getItemAtPosition(posicion);
                TextView texto=(TextView) findViewById(R.id.miTexto);
                texto.setText(elemento);
            }
        });
    }
}