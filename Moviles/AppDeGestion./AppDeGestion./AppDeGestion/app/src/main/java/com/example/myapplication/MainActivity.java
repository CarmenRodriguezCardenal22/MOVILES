package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Usuario> datos = new ArrayList<>();
    private ImageView imagen;
    private EditText identificacion, contrasena;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        identificacion = findViewById(R.id.identificacion);
        contrasena = findViewById(R.id.identificacion2);

        // Configuración de insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //dbHelper.insertarUsuario("eva", "eva");
        // Inicialización de ImageView y animación
        imagen = findViewById(R.id.imageView);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.animacion_portada);
        imagen.startAnimation(anim);

        SharedPreferences prefe=getSharedPreferences("datos",Context.MODE_PRIVATE);
        identificacion.setText(prefe.getString("identificacion",""));
        // Reproducir audio en bucle
        mediaPlayer = MediaPlayer.create(this, R.raw.audio);
        mediaPlayer.setLooping(true); // Para que el audio se repita en bucle
        mediaPlayer.start();
    }

    public void boton(View view) {
        String usuarioIngresado = identificacion.getText().toString();
        String contrasenaIngresada = contrasena.getText().toString();

        boolean encontrado = dbHelper.validarUsuario(usuarioIngresado, contrasenaIngresada);
        SharedPreferences prefe=getSharedPreferences("datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefe.edit();

        // Validación de usuario y contraseña
        for (Usuario usuario : datos) {
            if (usuario.getUsuario().equals(usuarioIngresado) && usuario.getContraseña().equals(contrasenaIngresada)) {
                encontrado = true;
            }
        }

        // Acciones según resultado
        if (encontrado) {
            Intent ejemplo = new Intent(this, Boton.class);
            startActivity(ejemplo);
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
    BaseDeDatos dbHelper = new BaseDeDatos(this);

    public void registrarUsuario(String usuario, String contrasena) {
        long resultado = dbHelper.insertarUsuario(usuario, contrasena);
        if (resultado != -1) {
            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
        }
    }
    public void validarUsuario(String usuario, String contrasena) {
        boolean valido = dbHelper.validarUsuario(usuario, contrasena);
        if (valido) {
            Intent intent = new Intent(this, Boton.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // Clase Usuario
    public static class Usuario {
        private String usuario;
        private String contraseña;

        public Usuario(String usuario, String contraseña) {
            this.usuario = usuario;
            this.contraseña = contraseña;
        }

        public String getUsuario() {
            return usuario;
        }

        public String getContraseña() {
            return contraseña;
        }
    }
}