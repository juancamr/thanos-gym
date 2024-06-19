package com.uni.thanosgym.model;

public class Item {

    private int id;
    private String nombre;
    private int cantidad;
    private String photoUrl;

    public Item (String nombre, int cantidad, String photoUrl) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.photoUrl = photoUrl;
    }

    public Item(int id, String nombre, int cantidad, String photoUrl) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
