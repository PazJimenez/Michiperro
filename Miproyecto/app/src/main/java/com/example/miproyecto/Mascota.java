package com.example.miproyecto;

public class Mascota {
    private String nombre;
    private String vacunas;
    private String descripcion;
    private String edad;
    private int imagenResId;

    public Mascota(String nombre, String vacunas, String descripcion, String edad, int imagenResId) {
        this.nombre = nombre;
        this.vacunas = vacunas;
        this.descripcion = descripcion;
        this.edad = edad;
        this.imagenResId = imagenResId;
    }

    public String getNombre() {
        return nombre;
    }
    public String getVacunas() {
        return vacunas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEdad() {
        return edad;
    }

    public int getImagenResId() {
        return imagenResId;
    }
}

