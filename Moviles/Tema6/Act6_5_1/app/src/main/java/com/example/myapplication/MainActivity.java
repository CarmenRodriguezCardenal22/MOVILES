package com.example.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
    @Override
    public boolean onCreateOptionMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionItemSelected(MenuItem item){
        String mensaje="";
        if(item.getItemId()==R.id.lunes) {

        }
        else if(item.getItemId()==R.id.martes){

        }
        else if(item.getItemId()==R.id.miercoles){

        }
        else if(item.getItemId()==R.id.jueves){

        }
        else if(item.getItemId()==R.id.viernes){

        }
        else if(item.getItemId()==R.id.sabado){

        }
        else if(item.getItemId()==R.id.domingo){

        }
    }
}