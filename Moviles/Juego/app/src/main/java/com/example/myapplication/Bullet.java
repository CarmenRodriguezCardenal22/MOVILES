package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Bullet {
    private Bitmap bitmap;
    private int x, y;

    public Bullet(Context context, int x, int y, int screenWidth) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet);
        bitmap = Bitmap.createScaledBitmap(bitmap, screenWidth / 30, screenWidth / 15, false);
        this.x = x - bitmap.getWidth() / 2;
        this.y = y;
    }

    public void update() { y -= 20; }
    public void draw(Canvas canvas) { canvas.drawBitmap(bitmap, x, y, null); }
    public int getY() { return y; }
    public Rect getRect() { return new Rect(x, y, x + bitmap.getWidth(), y + bitmap.getHeight()); }
}
