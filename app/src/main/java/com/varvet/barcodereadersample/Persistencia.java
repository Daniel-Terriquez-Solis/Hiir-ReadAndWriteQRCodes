package com.varvet.barcodereadersample;

import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    private Usuario usuario;
    private List<Clase> clases;

    public Persistencia() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    public Usuario buscarAlumnoPorId(int id){
        return AlumnoPersistence.obtenerPorId(id);
    }

    public Usuario loginAlumno(String telefono, String contrasena){
        return AlumnoPersistence.login(telefono, contrasena);
    }

    public Usuario buscarMaestroPorId(int id){
        return MaestroPersistence.obtenerPorId(id);
    }

    public Usuario loginMaestro(String telefono, String contrasena){
        return MaestroPersistence.login(telefono, contrasena);
    }

    public boolean registrarAsistencia(int id_alumno, int id_clase){
        return AlumnoPersistence.comprobarAsistencia(id_alumno, id_clase);
    }

    public int registrarAsistenciaMaestro(int id_maestro, int id_clase){
        return MaestroPersistence.generarLista(id_maestro, id_clase);
    }

    public List<Clase> obtenerClasesDeMaestro(int id_maestro){
        return MaestroPersistence.obtenerClases(id_maestro);
    }
}
