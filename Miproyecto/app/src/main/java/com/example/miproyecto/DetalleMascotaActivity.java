package com.example.miproyecto;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.widget.ImageView;
public class DetalleMascotaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_mascota);

        TextView textViewNombre = findViewById(R.id.textViewNombre);
        TextView textViewDescripcion = findViewById(R.id.textViewDescripcion);
        TextView textViewEdad = findViewById(R.id.textViewEdad);
        ImageView imageViewFoto = findViewById(R.id.imageViewFoto);

        // Obtener los datos desde el Intent
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String descripcion = intent.getStringExtra("descripcion");
        String edad = intent.getStringExtra("edad");
        int fotoResId = intent.getIntExtra("foto", R.drawable.mascota);

        // Mostrar los datos en los componentes
        textViewNombre.setText(nombre);
        textViewDescripcion.setText(descripcion);
        textViewEdad.setText(edad);
        imageViewFoto.setImageResource(fotoResId);
    }
}

