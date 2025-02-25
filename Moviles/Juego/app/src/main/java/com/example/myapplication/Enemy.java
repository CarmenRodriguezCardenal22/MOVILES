package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Enemy {
    private Bitmap bitmap;
    private int x, y;

    public Enemy(Context context, int x, int y, int screenWidth) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);
        bitmap = Bitmap.createScaledBitmap(bitmap, screenWidth / 9, screenWidth / 9, false);
        this.x = x;
        this.y = y;
    }

    public void update(int level) { y += 5 + level; }
    public void draw(Canvas canvas) { canvas.drawBitmap(bitmap, x, y, null); }
    public int getY() { return y; }
    public Rect getRect() { return new Rect(x, y, x + bitmap.getWidth(), y + bitmap.getHeight()); }
}
