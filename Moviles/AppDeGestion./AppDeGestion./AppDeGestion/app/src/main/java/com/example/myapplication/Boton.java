package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class Boton extends AppCompatActivity {
    private ListView lista;
    private ArrayList<Encapsulador> datos = new ArrayList<>();
    private BaseAdapter adaptador;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imagenUriSeleccionada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boton);

        lista = findViewById(R.id.lista);

        // Datos iniciales
        datos.add(new Encapsulador("a","CAMISETA", "Tallas: XS, S, M, L, XL\nColores: Negro, Blanco, Rojo, Beige", obtenerFechaActual(), 5));
        datos.add(new Encapsulador("a","PANTALÓN", "Tallas: 36, 38, 40, 42, 44, 46, 48\nColores: Azul, Gris, Negro, Blanco", obtenerFechaActual(), 3));
        datos.add(new Encapsulador("a","BOTAS", "Tallas: 36, 37, 38, 39, 40, 41, 42\nColores: Negro, Blanco, Marrón", obtenerFechaActual(), 1));
        datos.add(new Encapsulador("a","CHAQUETA", "Tallas: XS, S, M, L, XL\nColores: Negro, Blanco, Rojo", obtenerFechaActual(), 4));

        // Configuración del adaptador
        adaptador = new Adaptador(this, R.layout.activity_boton2, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                TextView textoTitulo = view.findViewById(R.id.texto_titulo);
                TextView textoDatos = view.findViewById(R.id.texto_datos);
                ImageView imagen = view.findViewById(R.id.imagen);
                RatingBar ratingBar = view.findViewById(R.id.ratingBar);

                Encapsulador item = (Encapsulador) entrada;
                textoTitulo.setText(item.getTitulo());
                textoDatos.setText(item.getContenido() + "\nFecha: " + item.getFecha());
                ratingBar.setRating(item.isFavorito());
                ratingBar.setIsIndicator(true);

                if (item.getImagenUri() != null && !item.getImagenUri().isEmpty()) {
                    imagen.setImageURI(Uri.parse(item.getImagenUri()));
                } else {
                    imagen.setImageResource(R.drawable.etiqueta);
                }
            }
        };
        lista.setAdapter(adaptador);

        // Aplicar animación al ListView
        /*Animation anim = AnimationUtils.loadAnimation(this, R.anim.animacion_ropa);
        lista.startAnimation(anim);*/

        // Registrar menú contextual para el ListView
        registerForContextMenu(lista);
    }

    // Crear el menú principal
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    // Opciones del menú principal
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.ordenar_titulo) {
            ordenarPorTitulo();
            return true;
        } else if (itemId == R.id.ordenar_favorito) {
            ordenarPorFavorito();
            return true;
        } else if(itemId == R.id.insertar) {
            mostrarDialogoInsertar();
            return true;
        } else if(itemId == R.id.mostrar_info){
            mostrarInfo();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void ordenarPorTitulo() {
        Collections.sort(datos, (o1, o2) -> o1.getTitulo().compareToIgnoreCase(o2.getTitulo()));
        adaptador.notifyDataSetChanged();
        Toast.makeText(this, "Lista ordenada por título", Toast.LENGTH_SHORT).show();
    }

    private void ordenarPorFavorito() {
        Collections.sort(datos, (o1, o2) -> Integer.compare(o2.isFavorito(), o1.isFavorito()));
        adaptador.notifyDataSetChanged();
        Toast.makeText(this, "Lista ordenada por favoritos", Toast.LENGTH_SHORT).show();
    }

    // Crear el menú contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    // Opciones del menú contextual
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        if (item.getItemId() == R.id.editar) {
            mostrarDialogoModificar(position);
            return true;
        } else if (item.getItemId() == R.id.eliminar) {
            datos.remove(position);
            adaptador.notifyDataSetChanged();
            Toast.makeText(this, "Elemento eliminado", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imagenUriSeleccionada = data.getData();
        }
    }

    private void abrirGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Mostrar diálogo personalizado para insertar
    private void mostrarDialogoInsertar() {
        View dialogView = getLayoutInflater().inflate(R.layout.activity_insertar, null);
        EditText nombre = dialogView.findViewById(R.id.editNombre);
        EditText descripcion = dialogView.findViewById(R.id.editDescripcion);
        RatingBar estrellas = dialogView.findViewById(R.id.ratingBar);
        DatePicker fecha = dialogView.findViewById(R.id.datePicker);
        ImageView imagenSeleccionada = dialogView.findViewById(R.id.imagenSeleccionada);
        Button btnSeleccionarImagen = dialogView.findViewById(R.id.btnSeleccionarImagen);

        btnSeleccionarImagen.setOnClickListener(v -> abrirGaleria());

        new AlertDialog.Builder(this)
                .setTitle("Insertar Elemento")
                .setView(dialogView)
                .setPositiveButton("Guardar", (dialog, which) -> {
                    String titulo = nombre.getText().toString();
                    String contenido = descripcion.getText().toString();
                    int year = fecha.getYear();
                    int month = fecha.getMonth() + 1;
                    int day = fecha.getDayOfMonth();
                    String fechaFinal = day + "/" + month + "/" + year;
                    Integer rating = (int) estrellas.getRating();

                    if (imagenUriSeleccionada != null) {
                        datos.add(new Encapsulador(imagenUriSeleccionada.toString(), titulo, contenido, fechaFinal, rating));
                        adaptador.notifyDataSetChanged();
                        Toast.makeText(this, "Elemento insertado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Selecciona una imagen", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .create()
                .show();
    }


    // Mostrar diálogo personalizado para modificar
    private void mostrarDialogoModificar(int position) {
        Encapsulador item = datos.get(position);
        View dialogView = getLayoutInflater().inflate(R.layout.activity_modificar, null);
        EditText nombre = dialogView.findViewById(R.id.editNombre);
        EditText descripcion = dialogView.findViewById(R.id.editDescripcion);
        RatingBar estrellas = dialogView.findViewById(R.id.ratingBar);

        // Establecer los valores actuales
        nombre.setText(item.getTitulo());
        descripcion.setText(item.getContenido());
        estrellas.setRating(item.isFavorito()); // Establecer el valor del RatingBar

        new AlertDialog.Builder(this)
                .setTitle("Modificar Elemento")
                .setView(dialogView)
                .setPositiveButton("Guardar", (dialog, which) -> {
                    item.setTitulo(nombre.getText().toString());
                    item.setContenido(descripcion.getText().toString());

                    // Obtener el valor del RatingBar y actualizar el favorito
                    int nuevoFavorito = (int) estrellas.getRating();
                    item.setFavorito(nuevoFavorito);

                    adaptador.notifyDataSetChanged();
                    Toast.makeText(this, "Elemento modificado", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", null)
                .create()
                .show();
    }
    private void mostrarInfo() {
        // Inflar la vista personalizada del diálogo
        View dialogView = getLayoutInflater().inflate(R.layout.activity_info, null);
        TextView texto = dialogView.findViewById(R.id.texto); // TextView en el diseño del diálogo

        try (InputStream fichraw = getResources().openRawResource(R.raw.info);
             BufferedReader miFichero = new BufferedReader(new InputStreamReader(fichraw))) {

            // Leer el contenido del archivo
            StringBuilder contenido = new StringBuilder();
            String linea;
            while ((linea = miFichero.readLine()) != null) {
                contenido.append(linea).append("\n");
            }

            // Configurar el texto leído en el TextView
            texto.setText(contenido.toString());

        } catch (Exception e) {
            // Manejar errores e informar al usuario
            e.printStackTrace();
            Toast.makeText(this, "Error al leer el archivo: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        // Crear y mostrar el diálogo
        new AlertDialog.Builder(this)
                .setTitle("Info App")
                .setView(dialogView)
                .setPositiveButton("Guardar", (dialog, which) -> {
                    Toast.makeText(this, "Información guardada.", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", null)
                .create()
                .show();
    }



    // Obtener la fecha actual
    private String obtenerFechaActual() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        return day + "/" + month + "/" + year;
    }

    // Clase POJO Encapsulador
    public static class Encapsulador {
        private String imagenUri;
        private String titulo, contenido, fecha;
        private Integer favorito;

        public Encapsulador(String imagenUri, String titulo, String contenido, String fecha, Integer favorito) {
            this.imagenUri = imagenUri;
            this.titulo = titulo;
            this.contenido = contenido;
            this.fecha = fecha;
            this.favorito = favorito;
        }

        public String getImagenUri() { return imagenUri; }
        public String getTitulo() { return titulo; }
        public String getContenido() { return contenido; }
        public String getFecha() { return fecha; }
        public Integer isFavorito() { return favorito; }

        public void setTitulo(String titulo) { this.titulo = titulo; }
        public void setContenido(String contenido) { this.contenido = contenido; }
        public void setFavorito(Integer favorito) { this.favorito = favorito; }
    }
}

