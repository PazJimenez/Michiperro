package com.example.miproyecto;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UbicarReportarActivity extends AppCompatActivity {

    private EditText editDispositivo, editUbicacion, editDetalles;
    private CheckBox checkPerdido;
    private Button btnSubmitUbicar;
    private ProgressBar progressBarUbicar;  // variable barra de carga ProgressBar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicar_reportar);

        // Enlazamos los elementos de la interfaz
        editDispositivo = findViewById(R.id.editDispositivo);
        editUbicacion = findViewById(R.id.editUbicacion);
        editDetalles = findViewById(R.id.editDetalles);
        checkPerdido = findViewById(R.id.checkPerdido);
        btnSubmitUbicar = findViewById(R.id.btnSubmitUbicar);
        progressBarUbicar = findViewById(R.id.progressBarUbicar);  // Enlazar ProgressBar

        // Configuramos la acción del botón
        btnSubmitUbicar.setOnClickListener(v -> {
            String dispositivo = editDispositivo.getText().toString();
            String ubicacion = editUbicacion.getText().toString();
            String detalles = editDetalles.getText().toString();
            boolean perdido = checkPerdido.isChecked();

            // Validar si el campo dispositivo está vacío
            if (dispositivo.isEmpty()) {
                Toast.makeText(UbicarReportarActivity.this, "Por favor, ingrese el nombre del dispositivo", Toast.LENGTH_SHORT).show();
                return;
            }

            // mostrar el ProgressBar mientras se realiza la simulación
            progressBarUbicar.setVisibility(android.view.View.VISIBLE);

            // simula un tiempo de espera de 3 segundos con Handler
            new Handler().postDelayed(() -> {
                // Ocultar el ProgressBar
                progressBarUbicar.setVisibility(android.view.View.GONE);

                // nos muestra los datos ingresados en un mensaje
                String mensaje = "Dispositivo: " + dispositivo + "\n" +
                        "Última ubicación: " + ubicacion + "\n" +
                        "Perdido: " + perdido + "\n" +
                        "Detalles: " + detalles;
                Toast.makeText(UbicarReportarActivity.this, mensaje, Toast.LENGTH_LONG).show();
            }, 3000); // Simula 3 segundos de espera
        });
    }
}
