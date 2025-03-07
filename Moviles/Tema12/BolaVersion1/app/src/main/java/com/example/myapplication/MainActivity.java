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

public class MainActivity extends AppCompatActivity {
    boolean continuar=true;
    float velocidad=1.5f;
    int dt=10;
    int tiempo=0;
    Thread hilo=null;
    DinamicaView dinamica;
    float s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dinamica=new DinamicaView(this);
        setContentView(dinamica);
        s=getResources().getDisplayMetrics().density;
        hilo=new Thread(dinamica);
        hilo.start();
    }

    //detenemos el hilo si pausa
    @Override
    public void onPause(){
        super.onPause();
        continuar=false;
    }

    //reiniciamos el hilo si resume
    @Override
    public void onResume(){
        super.onResume();
        continuar=true;
        if(!hilo.isAlive()){
            hilo=new Thread(dinamica);
            hilo.start();
        }
    }

    class DinamicaView extends View implements Runnable{
        int x,y,ymax;
        Paint paintFondo,paintParticula,paint;
        public DinamicaView(Context context) {
            super(context);
            //Colores para el dibujo y el tamaño del texto
            paintFondo=new Paint();
            paintParticula=new Paint();
            paint=new Paint();
            paintFondo.setColor(Color.WHITE);
            paintParticula.setColor(Color.RED);
            paint.setColor(Color.BLACK);
        }

        @Override
        public void run() {
            while(continuar){
                tiempo=tiempo+dt;
                //movimiento rectilineo uniforme y=y+v*t
                y=y+(int)(velocidad*dt);
                //si llega abajo invertimos la velocidad:
                if(y>ymax){
                    velocidad=-velocidad;
                }

                //si llega arriba invertimos la velocidad:
                if(y<0){
                    velocidad=-velocidad;
                }
                postInvalidate();
                try{Thread.sleep(dt);}
                catch (InterruptedException e){;}
            }
        }

        //obtiene geometria del canvas
        @Override
        protected void onSizeChanged(int w,int h,int oldw,int oldh){
            x=w/2;
            y=0;
            ymax=h;
        }

        @Override
        public void onDraw(Canvas canvas){
            canvas.drawPaint(paintFondo);
            paint.setTextSize(20*s);
            canvas.drawCircle(x,y,30*s,paintParticula);
            canvas.drawText("y= "+y,10*s,25*s,paint);
            canvas.drawText("tiempo= "+tiempo,10*s,50*s,paint);
        }
    }//fin clase DinamicaView
}