package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Figura2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(new Figura2.VistaPropia(this));

    }
    private class VistaPropia extends View {
        public VistaPropia(Context context){
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas){
            Paint pincel=new Paint();
            pincel.setStrokeWidth(15);

            pincel.setColor(Color.RED);

            //dibuja el borde del rectangulo
            pincel.setStyle(Paint.Style.STROKE);
            canvas.drawRect(100,1000,400,1300,pincel);

            //dibuja el rectangulo relleno
            pincel.setStyle(Paint.Style.FILL);
            canvas.drawRect(500,1000,800,1300,pincel);

            //dibuja el rectangulo relleno con el borde
            pincel.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawRect(900,1000,1200,1300,pincel);
        }
    }
}