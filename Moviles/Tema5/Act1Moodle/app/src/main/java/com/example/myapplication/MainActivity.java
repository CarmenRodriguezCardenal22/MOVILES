package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Encuentra el Spinner en el layout
        Spinner mySpinner = findViewById(R.id.mySpinner);

        // Define una lista de opciones para el Spinner
        String[] options = {"Opción 1", "Opción 2", "Opción 3", "Opción 4"};

        // Crea un ArrayAdapter usando el layout predeterminado de Android
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);

        // Especifica el layout para el menú desplegable del Spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asocia el adaptador al Spinner
        mySpinner.setAdapter(adapter);

        // Configura un Listener para manejar la selección del usuario
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtiene el elemento seleccionado
                String selectedOption = parent.getItemAtPosition(position).toString();

                // Muestra un Toast con la opción seleccionada
                Toast.makeText(MainActivity.this, "Seleccionaste: " + selectedOption, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}