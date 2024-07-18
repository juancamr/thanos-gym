package com.uni.thanosgym.model;

public class Item {

    private int id;
    private String nombre;
    private int cantidad;
    private String photoUrl;

    public Item(int id, String nombre, int cantidad, String photoUrl) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPhotoUrl(String url) {
        this.photoUrl = url;
    }
}
