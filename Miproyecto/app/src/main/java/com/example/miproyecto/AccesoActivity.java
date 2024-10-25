package com.example.miproyecto;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.Spinner;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.util.GeoPoint;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.view.MotionEvent;


import androidx.appcompat.app.AppCompatActivity;

public class AccesoActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerViewMascotas;
    private MascotaAdapter mascotaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceso);

        recyclerViewMascotas = findViewById(R.id.recyclerViewMascotas);
        recyclerViewMascotas.setLayoutManager(new LinearLayoutManager(this));

        // Lista de mascotas hardcodeada
        List<Mascota> listaMascotas = new ArrayList<>();
        // Agrega las mascotas a la lista
        listaMascotas.add(new Mascota("Roxy", "No vacunada", "Blanca con manchas negras, pelo largo", "11 años", R.drawable.roxy));
        listaMascotas.add(new Mascota("Dante", "No vacunado", "Color naranjo, pelo corto", "9 meses", R.drawable.dante));


        MascotaAdapter mascotaAdapter = new MascotaAdapter(this, listaMascotas);
        recyclerViewMascotas.setAdapter(mascotaAdapter);

        // se enlaza  el ProgressBar
        progressBar = findViewById(R.id.progressBar);
        Configuration.getInstance().load(getApplicationContext(),PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));

        MapView mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        ScrollView scrollView = findViewById(R.id.scrollAcceso); // Asegúrate de tener un ID para el ScrollView en el XML

        // Configurar el OnTouchListener para MapView
        mapView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Deshabilitar el scroll del ScrollView al tocar el mapa
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        // Permitir el scroll del ScrollView al dejar de tocar el mapa
                        scrollView.requestDisallowInterceptTouchEvent(false);
                        break;
                }
                return false; // Permitir que el MapView maneje el evento de forma predeterminada
            }
        });

        //Para que se pueda agrandar con los dedos
        mapView.setMultiTouchControls(true);
        //Coordenadas de Veterinaria Suecia Cerrillos
        double veterinaSueciaLatitud = -33.4738303;
        double veterinaSueciaLongitud = -70.7220467;

        //Coordenadas de Veterinaria Municipal Estación Central
        double veterinaMunicipalLatitud = -33.4641212;
        double veterinaMunicipalLongitud = -70.7095725;

        //ahora creo uno nuevo para mi casa, busco en googlemaps la latitud y longitud
        double casaRoxyLatitud = -33.4790454;
        double casaRoxyLongitud = -70.7216789;

        double casaDanteLatitud = -33.4570832;
        double casaDanteLongitud = -70.690686;

        //Creo un objeto geopoint para las coordenadas, que es el marcador en el mapa de un punto específico
        GeoPoint veterinariaSueciaPoint = new GeoPoint(veterinaSueciaLatitud,veterinaSueciaLongitud);

        //Creo un objeto geopoint para las coordenadas, que es el marcador en el mapa de un punto específico
        GeoPoint veterinariaMunicipalPoint = new GeoPoint(veterinaMunicipalLatitud,veterinaMunicipalLongitud);

        //Creo un objeto geopoint para las coordenadas, que es el marcador en el mapa de un punto específico
        GeoPoint casaRoxyPoint = new GeoPoint(casaRoxyLatitud,casaRoxyLongitud);

        //Creo un objeto geopoint para las coordenadas, que es el marcador en el mapa de un punto específico
        GeoPoint casaDantePoint = new GeoPoint(casaDanteLatitud,casaDanteLongitud);

        //Configuro la vista inicial del mapa centrado en el punto
        mapView.getController().setZoom(15.0);
        mapView.getController().setCenter(casaRoxyPoint);

        //Creo un marcador para la Veterinaria Suecia
        Marker veterinariaSueciaMarker = new Marker(mapView);
        veterinariaSueciaMarker.setPosition(veterinariaSueciaPoint);
        veterinariaSueciaMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        veterinariaSueciaMarker.setTitle("Veterinaria Suecia Cerrillos");
        veterinariaSueciaMarker.setSnippet("Atención de lunes a sábado, de 10:30 a 18:00 hrs");
        // Agrego el marcador creado
        mapView.getOverlays().add(veterinariaSueciaMarker);

        //Creo un marcador para la Veterinaria Municipal
        Marker veterinariaMunicipalMarker = new Marker(mapView);
        veterinariaMunicipalMarker.setPosition(veterinariaMunicipalPoint);
        veterinariaMunicipalMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        veterinariaMunicipalMarker.setTitle("Veterinaria Municipal Estación Central");
        veterinariaMunicipalMarker.setSnippet("Atención de lunes a sábado, de 10:30 a 18:00 hrs");
        // Agrego el marcador creado
        mapView.getOverlays().add(veterinariaMunicipalMarker);

        //Creo un marcador para mi casa
        Marker casaRoxyMarker = new Marker(mapView);
        casaRoxyMarker.setPosition(casaRoxyPoint);
        casaRoxyMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        casaRoxyMarker.setTitle("Casa Roxy");
        casaRoxyMarker.setSnippet("Muchos gatos");
        // Agrego el marcador creado
        mapView.getOverlays().add(casaRoxyMarker);

        //Creo un marcador para mi casa
        Marker casaDanteMarker = new Marker(mapView);
        casaDanteMarker.setPosition(casaDantePoint);
        casaDanteMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        casaDanteMarker.setTitle("Casa Dante");
        casaDanteMarker.setSnippet("Muchos gatos");
        // Agrego el marcador creado
        mapView.getOverlays().add(casaDanteMarker);

        //Creo una linea que conecte los marcadores
        Polyline polyline = new Polyline();
        polyline.addPoint(casaRoxyPoint);
        polyline.addPoint(veterinariaSueciaPoint);
        polyline.setColor(0xFF0000FF);
        polyline.setWidth(5);
        //Añadimos la linea en el mapa
        mapView.getOverlayManager().add(polyline);

        //Creo una linea que conecte los marcadores
        Polyline polylineDante = new Polyline();
        polyline.addPoint(casaDantePoint);
        polyline.addPoint(veterinariaMunicipalPoint);
        polyline.setColor(0xFF0000FF);
        polyline.setWidth(5);
        //Añadimos la linea en el mapa
        mapView.getOverlayManager().add(polylineDante);

        //Configuro el Spinner para cambiar el tipo Mapa
        Spinner mapTypeSpinner = findViewById(R.id.mapTypeSpinner);
        String[] mapTypes={"Mapa normal","Transporte público","MApa topográfico"};
        //Creo un ArrayAdapter para poblar el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mapTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mapTypeSpinner.setAdapter(adapter);

        //Listener para detectar cambios en la selección del spinner
        mapTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {  switch (position){
                case 0:
                    mapView.setTileSource(TileSourceFactory.MAPNIK);
                    break;
                case 1:
                    mapView.setTileSource(new XYTileSource(
                            "PublicTransport",
                            0,18,256,".png", new String[]{
                                    "https://tile.memomaps.de/tilegen/"}));
                    break;
                case 2:
                    mapView.setTileSource(new XYTileSource(
                            "USGS_Satellite", 0, 18, 256, ".png", new String[]{
                                    "https://a.tile.opentopomap.org/",
                                    "https://b.tile.opentopomap.org/",
                                    "https://c.tile.opentopomap.org/"}));
                    break;
            }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    // nos muestra  la actividad de registro con ProgressBar
    public void openRegistrar(View view) {
        // nos muestra el ProgressBar
        progressBar.setVisibility(View.VISIBLE);

        // carga de 3 segundos antes de abrir la nueva actividad
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Oculta  ProgressBar después de esperar
                progressBar.setVisibility(View.GONE);

                // Abrir la nueva actividad
                Intent intent = new Intent(AccesoActivity.this, RegistrarActivity.class);
                startActivity(intent);
            }
        }, 3000); // Realiza carga de 3 segundos
    }


}
