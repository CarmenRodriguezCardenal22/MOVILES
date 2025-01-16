package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements Fragmento1.Callbacks {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Configuración de insets para manejar el diseño con System Bars
        View fragmentListado = findViewById(R.id.fragment_listado);
        ViewCompat.setOnApplyWindowInsetsListener(fragmentListado, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Añadir dinámicamente el Fragmento1 al contenedor
        Fragmento1 fragmento1 = new Fragmento1(this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_listado, fragmento1)
                .commit();
    }

    @Override
    public void onEntradaSeleccionada(String id) {
        // Iniciar la actividad para mostrar detalles
        Intent detalleIntent = new Intent(this, Fragmento2.class);
        detalleIntent.putExtra(Fragmento3.ARG_ID_ENTRADA_SELECCIONADA, id);
        startActivity(detalleIntent);
    }
}