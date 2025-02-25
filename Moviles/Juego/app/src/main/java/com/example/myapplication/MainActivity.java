package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //Shooter Espacial
    private int[] playerSprites = {R.drawable.player, R.drawable.player2, R.drawable.player3};
    private int currentIndex = 0;
    private ImageView playerPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerPreview = findViewById(R.id.playerPreview);
        playerPreview.setImageResource(playerSprites[currentIndex]);

        findViewById(R.id.btnNext).setOnClickListener(v -> changeCharacter(1));
        findViewById(R.id.btnPrevious).setOnClickListener(v -> changeCharacter(-1));
        findViewById(R.id.btnStart).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("selectedPlayer", playerSprites[currentIndex]);
            startActivity(intent);
        });
    }

    private void changeCharacter(int direction) {
        currentIndex = (currentIndex + direction + playerSprites.length) % playerSprites.length;
        playerPreview.setImageResource(playerSprites[currentIndex]);
    }
}