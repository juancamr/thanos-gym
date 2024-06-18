package com.uni.thanosgym.model;

public class Utilidad {

    private int id;
    private String nombre;
    private int peso;
    private int cantidad;

    public Utilidad(String tipo, int peso, int cantidad) {
        this.nombre = tipo;
        this.peso = peso;
        this.cantidad = cantidad;
    }

    public Utilidad(int id, String tipo, int peso, int cantidad) {
        this.id = id;
        this.nombre = tipo;
        this.peso = peso;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String tipo) {
        this.nombre = tipo;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Object[] showAll() {
        Object[] lista = { id, nombre, cantidad, peso };
        return lista;
    }

}