package com.example.myapplication;

import android.Manifest;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private SurfaceView surface;
    private Button botonGrabar, botonPausa, botonPlay;
    private MediaRecorder grabador;
    private MediaPlayer reproductor;
    private String ruta;
    private boolean grabando = false;

    private static final int REQUEST_PERMISSIONS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización de vistas
        surface = findViewById(R.id.surface);
        botonGrabar = findViewById(R.id.botonGrabar);
        botonPausa = findViewById(R.id.botonPausa);
        botonPlay = findViewById(R.id.botonPlay);

        // Definir ruta segura para guardar el video
        File videoFile = new File(getExternalFilesDir(Environment.DIRECTORY_MOVIES), "mivideo.mp4");
        ruta = videoFile.getAbsolutePath();

        // Configurar SurfaceView
        surface.getHolder().addCallback(this);

        // Pedir permisos
        pedirPermisos();

        // Evento de grabar
        botonGrabar.setOnClickListener(view -> iniciarGrabacion());

        // Evento de detener grabación o reproducción
        botonPausa.setOnClickListener(v -> detener());

        // Evento de reproducción
        botonPlay.setOnClickListener(v -> reproducir());
    }

    private void pedirPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permisos = new ArrayList<>();

            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                permisos.add(Manifest.permission.CAMERA);
            }
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                permisos.add(Manifest.permission.RECORD_AUDIO);
            }

            // Solo pedir WRITE_EXTERNAL_STORAGE en Android 9 o menor
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q &&
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permisos.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            if (!permisos.isEmpty()) {
                requestPermissions(permisos.toArray(new String[0]), REQUEST_PERMISSIONS);
            }
        }
    }



    // Iniciar grabación de video
    private void iniciarGrabacion() {
        if (grabador != null) {
            grabador.release();
        }

        grabador = new MediaRecorder();
        grabador.setAudioSource(MediaRecorder.AudioSource.MIC);
        grabador.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        grabador.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        grabador.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        grabador.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        grabador.setOutputFile(ruta);
        grabador.setPreviewDisplay(surface.getHolder().getSurface());

        try {
            grabador.prepare();
            grabador.start();
            grabando = true;
            botonGrabar.setEnabled(false);
            botonPausa.setEnabled(true);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al iniciar la grabación", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para detener grabación o reproducción
    private void detener() {
        if (grabando) {
            grabador.stop();
            grabador.release();
            grabador = null;
            grabando = false;
            botonGrabar.setEnabled(true);
            botonPausa.setEnabled(false);
        } else if (reproductor != null && reproductor.isPlaying()) {
            reproductor.stop();
            reproductor.reset();
            botonPlay.setText("PLAY");
        }
    }

    // Reproducir el video grabado
    private void reproducir() {
        if (reproductor == null) {
            reproductor = new MediaPlayer();
            reproductor.setDisplay(surface.getHolder());
        }

        if (!reproductor.isPlaying()) {
            try {
                reproductor.setDataSource(ruta);
                reproductor.prepare();
                reproductor.start();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error al reproducir el video", Toast.LENGTH_SHORT).show();
            }
        } else {
            reproductor.pause();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (reproductor == null) {
            reproductor = new MediaPlayer();
            reproductor.setDisplay(holder);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (grabador != null) {
            grabador.release();
            grabador = null;
        }
        if (reproductor != null) {
            reproductor.release();
            reproductor = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {
            boolean permisosAceptados = true;
            for (int resultado : grantResults) {
                if (resultado != PackageManager.PERMISSION_GRANTED) {
                    permisosAceptados = false;
                    break;
                }
            }
            if (!permisosAceptados) {
                Toast.makeText(this, "Se necesitan permisos para grabar", Toast.LENGTH_LONG).show();
            }
        }
    }
}