package com.varvet.barcodereadersample;

import android.os.Parcelable;

import java.io.Serializable;

public class Usuario implements Serializable{

    int id;
    String contrasena;
    String nombre;
    String telefono;
    String correo;

    public Usuario() {
    }

    public Usuario(int id, String contrasena, String nombre, String telefono, String correo) {
        this.id = id;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
