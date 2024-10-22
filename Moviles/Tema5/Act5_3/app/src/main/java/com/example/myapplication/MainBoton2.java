package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainBoton2 extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_boton2);
        Button Boton1=(Button) findViewById(R.id.boton1);
        Button Boton2=(Button) findViewById(R.id.boton2);
        Boton1.setOnClickListener(this);
        Boton2.setOnClickListener(this);
    }
    @Override
    public void onClick (View view){
        if (view.getId()==R.id.boton1) {
            TextView miTexto1 = new TextView(this);
            miTexto1.setText("BOTÓN 1 PULSADO");
            miTexto1.setTextColor(Color.RED);
            miTexto1.setTextSize(20);
        }
        else if(view.getId()==R.id.boton2) {
            TextView miTexto2=new TextView(this);
            miTexto2.setText("BOTÓN 1 PULSADO");
            miTexto2.setTextColor(Color.GREEN);
            miTexto2.setTextSize(20);
        }

    }
}