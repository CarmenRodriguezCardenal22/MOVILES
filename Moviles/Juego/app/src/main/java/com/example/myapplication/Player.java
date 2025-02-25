package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Player {
    private Bitmap bitmap;
    private int x, y;

    public Player(Context context, int screenWidth, int screenHeight, int playerSprite) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), playerSprite);
        bitmap = Bitmap.createScaledBitmap(bitmap, screenWidth / 7, screenHeight / 10, false);
        x = screenWidth / 2 - bitmap.getWidth() / 2;
        y = screenHeight - bitmap.getHeight() - 50;
    }

    public void update() {}
    public void draw(Canvas canvas) { canvas.drawBitmap(bitmap, x, y, null); }
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getWidth() { return bitmap.getWidth(); }
    public int getY() { return y; }
}
