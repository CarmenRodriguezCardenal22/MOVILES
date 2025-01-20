package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements FragmentoNaranja.OnTextSendListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cargar los fragmentos al iniciar la actividad
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_entrada, new FragmentoNaranja())
                    .replace(R.id.fragment_container_salida, new FragmentoVerde())
                    .commit();
        }
    }

    @Override
    public void onTextSend(String text) {
        // Obtener el fragmento de salida y enviar el texto
        FragmentoVerde outputFragment = (FragmentoVerde) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container_salida);

        if (outputFragment != null) {
            outputFragment.updateText(text);
        }
    }
}