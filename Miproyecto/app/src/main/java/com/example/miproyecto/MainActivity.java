package com.example.miproyecto;

import java.util.List;
import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private boolean isImageChanging = false; // Variable para controlar el cambio de imagen
    private int originalImageResId; // ID de la imagen original

    private EditText correoEditText;
    private EditText contrasenaEditText;
    private ImageView imagenMascota; // Cambié el nombre a imagenMascota
    private ProgressBar progressBar;  // ProgressBar para la carga
    private SensorManager sensorManager;
    private Sensor accelerometer;

    // Lista de imágenes de mascotas
    private List<Integer> mascotaFotos = new ArrayList<>();
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Asigna el ID de la imagen original
        originalImageResId = R.drawable.mascota; // Reemplaza con el ID de tu imagen original
        imagenMascota = findViewById(R.id.imagenMascota); // Asegúrate de que el ID sea correcto
        progressBar = findViewById(R.id.progressBar);  // Enlaza el ProgressBar

        // Inicializa la imagen original
        imagenMascota.setImageResource(originalImageResId);

        correoEditText = findViewById(R.id.correo);
        contrasenaEditText = findViewById(R.id.contrasena);

        // Inicializa el SensorManager y el acelerómetro
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Agrega las imágenes de mascotas
        mascotaFotos.add(R.drawable.roxy); // Reemplaza con el nombre de tus imágenes
        mascotaFotos.add(R.drawable.dante);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];

            // Verifica si el movimiento es significativo y si no se está cambiando la imagen
            if (!isImageChanging) {
                if (x > 2) { // Gira a la derecha
                    currentIndex = (currentIndex - 1 + mascotaFotos.size()) % mascotaFotos.size();
                    imagenMascota.setImageResource(mascotaFotos.get(currentIndex));
                    isImageChanging = true; // Activa el estado de cambio de imagen

                    // Espera 1 segundo antes de permitir otro cambio
                    new Handler().postDelayed(() -> isImageChanging = false, 1000);
                } else if (x < -2) { // Gira a la izquierda
                    currentIndex = (currentIndex + 1) % mascotaFotos.size();
                    imagenMascota.setImageResource(mascotaFotos.get(currentIndex));
                    isImageChanging = true; // Activa el estado de cambio de imagen

                    // Espera 1 segundo antes de permitir otro cambio
                    new Handler().postDelayed(() -> isImageChanging = false, 1000);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Este método se puede dejar vacío si no necesitas realizar ninguna acción
    }

    public void onClickVentana(View view) {
        Intent intent = new Intent(this, VentanaActivity.class);
        startActivity(intent);
    }

    public void onClickAcceder(View view) {
        String user = correoEditText.getText().toString().trim();
        String pass = contrasenaEditText.getText().toString().trim();

        if (user.isEmpty()) {
            Toast.makeText(this, "El campo de correo está vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass.isEmpty()) {
            Toast.makeText(this, "El campo de contraseña está vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        if (user.equals("usuario1") && pass.equals("1234")) {
            iniciarCarga();  // Llama al método para iniciar la carga y transición
        } else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }

    private void iniciarCarga() {
        // Mostrar el ProgressBar
        progressBar.setVisibility(View.VISIBLE);

        // Iniciar un nuevo Thread para simular la carga
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Simular la espera de 3 segundos
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Después de la espera, ocultar el ProgressBar y cambiar de actividad
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE); // Ocultar ProgressBar
                        Intent intent = new Intent(MainActivity.this, AccesoActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }).start();
    }
}
