package com.example.myapplication;

import android.os.Bundle;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainPantalla2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pantalla2);

        ImageView imageView = findViewById(R.id.imagen);

        TranslateAnimation animation = new TranslateAnimation(this, R.anim.anim_pantalla2);
        animation.setRepeatCount(TranslateAnimation.INFINITE);
        animation.setRepeatMode(TranslateAnimation.RESTART);

        imageView.startAnimation(animation);
    }
}