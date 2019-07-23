package com.varvet.barcodereadersample;

public class Clase {
    private int id;
    private String name;
    private String aula;
    private String carrera;
    private String dias;
    private int hourStart;
    private int hourEnd;

    public Clase(){

    }

    public Clase(int id, String name, String aula, String carrera, String dias, int hourStart, int hourEnd) {
        this.id = id;
        this.name = name;
        this.aula = aula;
        this.carrera = carrera;
        this.dias = dias;
        this.hourStart = hourStart;
        this.hourEnd = hourEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHourStart() {
        return hourStart;
    }

    public void setHourStart(int hourStart) {
        this.hourStart = hourStart;
    }

    public int getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(int hourEnd) {
        this.hourStart = hourEnd;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }
}
