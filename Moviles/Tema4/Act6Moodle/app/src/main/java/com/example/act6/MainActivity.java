package com.example.act6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myString = "Hola, mundo!";
                double myDouble = 123.45;

                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("myStringKey", myString);
                intent.putExtra("myDoubleKey", myDouble);
                startActivity(intent);
            }
        });
    }
}