package com.example.miproyecto;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrarActivity extends AppCompatActivity {

    private EditText editNombre, editHistorial, editDescripcion;
    private Button btnSubmit;
    private ImageView imagenMascota;
    private Spinner spinner;
    private ProgressBar progressBar;  // variable carga progresBar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_mascota);

        // Enlazamos los elementos de la interfaz
        editNombre = findViewById(R.id.editNombre);
        editHistorial = findViewById(R.id.editHistorial);
        editDescripcion = findViewById(R.id.editDescripcion);
        btnSubmit = findViewById(R.id.btnSubmit);
        imagenMascota = findViewById(R.id.imagenMascota);
        spinner = findViewById(R.id.spinnerAnimal);
        progressBar = findViewById(R.id.progressBar);  // Enlazar ProgressBar

        String[] animal = {"Gato", "Perro"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, animal);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Acciones al presionar el botón "Submit"
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editNombre.getText().toString();
                String historial = editHistorial.getText().toString();
                String descripcion = editDescripcion.getText().toString();
                String animal = spinner.getSelectedItem().toString();

                // Validar si el nombre está vacío
                if (nombre.isEmpty()) {
                    Toast.makeText(RegistrarActivity.this, "Por favor, ingrese el nombre de la mascota", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Nos muestra  el ProgressBar durante la simulación de registro
                progressBar.setVisibility(View.VISIBLE);

                // Tiempo de espera de 3 segundos con Handler
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Ocultar el ProgressBar
                        progressBar.setVisibility(View.GONE);

                        // Mostrar los datos ingresados en un mensaje
                        String mensaje = "Mascota: " + animal + "\n" +
                                nombre + "\n" +
                                "Historial Médico: " + historial + "\n" +
                                "Descripción Física: " + descripcion + "\n";
                        Toast.makeText(RegistrarActivity.this, mensaje, Toast.LENGTH_LONG).show();
                    }
                }, 3000); // Simula 3 segundos de espera
            }
        });
    }

    // Metodo onClick para abrir la UbicarReportar
    public void openUbicarReportar(View view) {
        Intent intent = new Intent(this, UbicarReportarActivity.class);
        startActivity(intent);
    }
}
