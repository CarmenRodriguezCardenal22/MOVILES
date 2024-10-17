package com.example.myapplication;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainTexto2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_texto2);
        LinearLayout layout=findViewById(R.id.texto2);
        TextView miTexto=new TextView(this);
        miTexto.setText("Texto contruido desde Java Tamaño 20dp, Itálic y color Blue");
        miTexto.setTextColor(Color.BLUE);
        miTexto.setTextSize(20);
        miTexto.setTypeface(null, Typeface.ITALIC);
        layout.addView(miTexto);
    }
}