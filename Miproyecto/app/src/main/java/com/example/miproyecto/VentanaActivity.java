package com.example.miproyecto;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VentanaActivity extends AppCompatActivity {

    private EditText editNombreCuidador, editCorreo, editContrasena, editRepContrasena;
    private Button btnSubmit;
    private ProgressBar progressBarCrearCuenta; // variable para nuestro progreesBar, barra de carga

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana);

        // Enlazamos los elementos de la interfaz
        editNombreCuidador = findViewById(R.id.editNombreCuidador);
        editCorreo = findViewById(R.id.editCorreo);
        editContrasena = findViewById(R.id.editContrasena);
        editRepContrasena = findViewById(R.id.editRepContrasena);
        btnSubmit = findViewById(R.id.btnSubmit);
        progressBarCrearCuenta = findViewById(R.id.progressBarCrearCuenta);  // Enlazar ProgressBar

        // Configuramos la acción del botón
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreCuidador = editNombreCuidador.getText().toString();
                String correo = editCorreo.getText().toString();
                String contrasena = editContrasena.getText().toString();
                String repContrasena = editRepContrasena.getText().toString();

                // Validar si los campos están vacíos
                if (nombreCuidador.isEmpty() || correo.isEmpty() || contrasena.isEmpty() || repContrasena.isEmpty()) {
                    Toast.makeText(VentanaActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!contrasena.equals(repContrasena)) {
                    Toast.makeText(VentanaActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Mostrar ProgressBar mientras se simula la creación de la cuenta
                progressBarCrearCuenta.setVisibility(View.VISIBLE);

                // Simular un tiempo de espera de 3 segundos con Handler
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Oculta el ProgressBar
                        progressBarCrearCuenta.setVisibility(View.GONE);

                        //  mensaje de éxito
                        String mensaje = "Cuenta creada para " + nombreCuidador;
                        Toast.makeText(VentanaActivity.this, mensaje, Toast.LENGTH_LONG).show();
                    }
                }, 3000); // Simula 3 segundos de espera
            }
        });
    }
}
