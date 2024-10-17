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

public class MainTexto4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_texto4);
        LinearLayout layout=findViewById(R.id.texto4);
        TextView miTexto=new TextView(this);
        miTexto.setText("TEXTO ESCRITO FUENTE UMBRELLA");
        miTexto.setTextColor(Color.RED);
        miTexto.setTextSize(20);
        Typeface miFuente=Typeface.createFromAsset(getAssets(),"fonts/Umbrella.ttf");
        miTexto.setTypeface(miFuente);
        layout.addView(miTexto);
    }
}