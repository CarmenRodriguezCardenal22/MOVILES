package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Player {
    private Bitmap bitmap;
    private int x, y;

    public Player(Context context, int x, int y) {
        this.x = x;
        this.y = y;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
    }

    public void update() {}
    public void draw(Canvas canvas) { canvas.drawBitmap(bitmap, x, y, null); }
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getWidth() { return bitmap.getWidth(); }
    public int getY() { return y; }
}
