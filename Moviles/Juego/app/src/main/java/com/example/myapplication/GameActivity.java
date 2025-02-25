package com.example.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    private MediaPlayer bgMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int selectedPlayer = getIntent().getIntExtra("selectedPlayer", R.drawable.player);
        GameView gameView = new GameView(this, selectedPlayer);
        setContentView(gameView);

        bgMusic = MediaPlayer.create(this, R.raw.background_music);
        bgMusic.setLooping(true);
        bgMusic.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bgMusic.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bgMusic.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bgMusic.release();
    }
}
