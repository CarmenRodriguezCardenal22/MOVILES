package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Figura1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(new VistaPropia(this));

    }
    private class VistaPropia extends View{
        public VistaPropia(Context context){
            super(context);
        }
        @Override
        protected void onDraw(Canvas canvas){
            Paint pincel=new Paint();
            pincel.setStrokeWidth(15);
            //dibuja el punto
            float x = getWidth() / 2;
            float y = getHeight() / 2;
            canvas.drawPoint(x,y,pincel);

            pincel.setStyle(Paint.Style.STROKE);
            //dibuja el circulo
            canvas.drawCircle(x,y,100,pincel);

            //dibuja el ovalo
            pincel.setColor(Color.BLUE);
            canvas.drawOval(300,1150,1150,1800,pincel);

            //dibuja el rectangulo
            pincel.setColor(Color.RED);
            canvas.drawRect(300,1150,1150,1800,pincel);
        }
    }
}