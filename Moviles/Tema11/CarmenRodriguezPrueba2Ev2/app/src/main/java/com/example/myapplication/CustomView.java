package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {
    private Paint linePaint;
    private Paint textPaint;
    private float scaleDensity;

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        scaleDensity = getResources().getDisplayMetrics().scaledDensity;

        linePaint = new Paint();
        linePaint.setColor(Color.GREEN);
        linePaint.setStrokeWidth(2);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setPathEffect(new DashPathEffect(new float[]{10, 10}, 0));

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(20 * scaleDensity);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Fondo blanco
        canvas.drawColor(Color.WHITE);

        int width = getWidth();
        int height = getHeight();
        float step = 30 * scaleDensity;
        int rowNumber = 1;

        // Dibujar líneas horizontales discontinuas con número de fila según escala
        for (float y = step; y < height; y += step, rowNumber++) {
            canvas.drawLine(0, y, width, y, linePaint);
            canvas.drawText("Fila " + rowNumber, 10, y - 5, textPaint);
        }

        // Dividir pantalla en 4 secciones horizontales y mostrar texto personalizado
        String[] sections = {"Fila: 544 scale= 3.09375", "Fila: 1088 width= 1080", "Fila: 1632 heigth= 2177", "ratio= 2.0157406"};
        int sectionHeight = height / 4;
        int textOffset = 50;

        for (int i = 0; i < 4; i++) {
            float x = width / 2;
            float y = i * sectionHeight + textOffset;
            canvas.drawText(sections[i], x, y, textPaint);
        }
    }
}
