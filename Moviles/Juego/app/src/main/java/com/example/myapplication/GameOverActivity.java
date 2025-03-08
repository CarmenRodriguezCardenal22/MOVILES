package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        TextView levelTextView = findViewById(R.id.levelTextView);

        // Obtener los datos de la pantalla de juego
        int score = getIntent().getIntExtra("score", 0);
        int level = getIntent().getIntExtra("level", 1);

        // Mostrar los datos en la pantalla
        scoreTextView.setText("Puntuación: " + score);
        levelTextView.setText("Nivel alcanzado: " + level);

        // Botón para reiniciar el juego
        Button restartButton = findViewById(R.id.btnRestart);
        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(GameOverActivity.this, GameActivity.class);
            startActivity(intent);
            finish();
        });

        // Botón para volver al inicio
        Button backToMainButton = findViewById(R.id.btnBackToMain);
        backToMainButton.setOnClickListener(v -> {
            Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
