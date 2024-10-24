package com.example.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Visor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TextView miTexto=(TextView) findViewById(R.id.texto);
        Typeface miFuente=Typeface.createFromAsset(getAssets(),"font/colour.ttf");
        miTexto.setTypeface(miFuente);
    }
}