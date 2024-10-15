package com.example.act4_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
    public void figura1(View view){
        Intent ejemplo=new Intent(this, Figura1.class);
        startActivity(ejemplo);
    }
    public void figura2(View view){
        Intent ejemplo=new Intent(this, Figura2.class);
        startActivity(ejemplo);
    }
    public void figura3(View view){
        Intent ejemplo=new Intent(this, Figura3.class);
        startActivity(ejemplo);
    }
    public void figura4(View view){
        Intent ejemplo=new Intent(this, Figura4.class);
        startActivity(ejemplo);
    }
}