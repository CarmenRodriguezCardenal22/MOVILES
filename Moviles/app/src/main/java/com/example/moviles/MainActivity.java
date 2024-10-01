package com.example.moviles;

import android.os.Bundle;
import android.util.Log;

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
    protected void onStart() {
        super.onStart();
        Log.i("Ejemplo","Estoy onStart");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Ejemplo","Estoy onRestart");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Ejemplo","Estoy onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Ejemplo","Estoy onStop");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Ejemplo","Estoy onResume");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Ejemplo","Estoy onDestroy");
    }
}