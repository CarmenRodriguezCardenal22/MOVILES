package com.example.act6;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        // Obtener los datos del Intent
        String receivedString = getIntent().getStringExtra("myStringKey");
        double receivedDouble = getIntent().getDoubleExtra("myDoubleKey", 0.0);

        // Mostrar los datos en un Toast
        Toast.makeText(this, "String: " + receivedString + ", Double: " + receivedDouble, Toast.LENGTH_LONG).show();
    }
}
