package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void Texto1(View view){
        Intent ejemplo=new Intent(this, MainTexto1.class);
        startActivity(ejemplo);
    }
    public void Texto2(View view){
        Intent ejemplo=new Intent(this, MainTexto2.class);
        startActivity(ejemplo);
    }
    public void Texto3(View view){
        Intent ejemplo=new Intent(this, MainTexto3.class);
        startActivity(ejemplo);
    }
    public void Texto4(View view){
        Intent ejemplo=new Intent(this, MainTexto4.class);
        startActivity(ejemplo);
    }
}