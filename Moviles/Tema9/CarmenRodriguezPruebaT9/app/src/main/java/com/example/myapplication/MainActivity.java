package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements Fragmento1.OnTextSendListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment1, new Fragmento1())
                    .replace(R.id.fragment2, new Fragmento2())
                    .commit();
        }
    }

    @Override
    public void onTextSend(String text) {
        Fragmento2 outputFragment = (Fragmento2) getSupportFragmentManager()
                .findFragmentById(R.id.fragment2);

        if (outputFragment != null) {
            outputFragment.updateText(text);
        }
    }
}