package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.SoundPool;
import android.os.Build;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameView  extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread thread;
    private Player player;
    private List<Bullet> bullets;
    private List<Enemy> enemies;
    private Bitmap bg;
    private int score = 0, lives = 3, level = 1;
    private Paint textPaint;
    private SoundPool soundPool;
    private int shootSound, explosionSound;
    private long lastEnemySpawnTime = 0, lastShotTime = 0;
    private int screenWidth, screenHeight;

    public GameView(Context context, int playerSprite) {
        super(context);
        getHolder().addCallback(this);
        thread = new GameThread(getHolder(), this);
        setFocusable(true);

        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        bg = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bg),
                screenWidth, screenHeight, false);

        player = new Player(context, screenWidth, screenHeight, playerSprite);
        bullets = new ArrayList<>();
        enemies = new ArrayList<>();

        soundPool = new SoundPool.Builder().setMaxStreams(5).build();
        shootSound = soundPool.load(context, R.raw.shoot, 1);
        explosionSound = soundPool.load(context, R.raw.explosion, 1);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(60);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            player.setX((int) event.getX() - player.getWidth() / 2);
        }
        return true;
    }

    public void update() {
        player.update();
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShotTime > 500) { // Disparo automático cada 500ms
            bullets.add(new Bullet(getContext(), player.getX() + player.getWidth() / 2, player.getY(), screenWidth));
            soundPool.play(shootSound, 1, 1, 0, 0, 1);
            lastShotTime = currentTime;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            bullets.removeIf(b -> {
                b.update();
                return b.getY() < 0;
            });
        }

        Iterator<Enemy> enemyIter = enemies.iterator();
        while (enemyIter.hasNext()) {
            Enemy enemy = enemyIter.next();
            enemy.update(level);
            if (enemy.getY() > screenHeight) {
                enemyIter.remove();
                lives--;
                if (lives <= 0) thread.setRunning(false);
            }
            for (Iterator<Bullet> bulletIter = bullets.iterator(); bulletIter.hasNext();) {
                Bullet bullet = bulletIter.next();
                if (Rect.intersects(bullet.getRect(), enemy.getRect())) {
                    bulletIter.remove();
                    enemyIter.remove();
                    score += 10;
                    soundPool.play(explosionSound, 1, 1, 0, 0, 1);
                    if (score % 100 == 0) level++;
                    break;
                }
            }
        }

        if (System.currentTimeMillis() - lastEnemySpawnTime > 1000 / level) {
            spawnEnemy();
            lastEnemySpawnTime = System.currentTimeMillis();
        }
    }

    private void spawnEnemy() {
        int xPos = new Random().nextInt(screenWidth - (screenWidth / 10));
        enemies.add(new Enemy(getContext(), xPos, 0, screenWidth));
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas != null) {
            canvas.drawBitmap(bg, 0, 0, null);
            player.draw(canvas);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                bullets.forEach(b -> b.draw(canvas));
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                enemies.forEach(e -> e.draw(canvas));
            }
            canvas.drawText("Score: " + score, 50, 80, textPaint);
            canvas.drawText("Lives: " + lives, 50, 160, textPaint);
            canvas.drawText("Level: " + level, 50, 240, textPaint);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }


    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}