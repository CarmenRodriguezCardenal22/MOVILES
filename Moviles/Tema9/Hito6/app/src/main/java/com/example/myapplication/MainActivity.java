package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements Fragmento1.Callbacks {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar el fragmento listado con soporte para System Bars
        View fragmentListado = findViewById(R.id.fragment_listado);
        ViewCompat.setOnApplyWindowInsetsListener(fragmentListado, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar el Fragmento1 dinámicamente
        Fragmento1 fragmento1 = new Fragmento1();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_listado, fragmento1)
                .commit();
    }

    @Override
    public void onEntradaSeleccionada(String id) {
        Intent detalleIntent = new Intent(this, Fragmento2.class);
        detalleIntent.putExtra(Fragmento3.ARG_ID_ENTRADA_SELECCIONADA, id);
        startActivity(detalleIntent);
    }
}