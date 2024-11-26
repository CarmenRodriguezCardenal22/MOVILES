package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuración de insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicialización de usuarios
        datos.add(new Usuario("Carmen", "carmen"));
        datos.add(new Usuario("Sofia", "sofia"));
        datos.add(new Usuario("Sergio", "sergio"));
        datos.add(new Usuario("Jose Antonio", "joseAntonio"));
        datos.add(new Usuario("Javier", "javier"));

        // Inicialización de ImageView y animación
        imagen = findViewById(R.id.imageView);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.animacion_portada);
        imagen.startAnimation(anim);
    }

    public void boton(View view) {
        EditText identificacion = findViewById(R.id.identificacion); // Campo usuario
        EditText contrasena = findViewById(R.id.identificacion2);         // Campo contraseña

        String usuarioIngresado = identificacion.getText().toString().trim();
        String contrasenaIngresada = contrasena.getText().toString().trim();

        boolean encontrado = false;

        // Validación de usuario y contraseña
        for (Usuario usuario : datos) {
            if (usuario.getUsuario().equals(usuarioIngresado) && usuario.getContraseña().equals(contrasenaIngresada)) {
                encontrado = true;
                break;
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