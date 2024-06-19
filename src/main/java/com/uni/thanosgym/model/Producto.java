package com.uni.thanosgym.model;

public class Producto extends Item {

    private double precio;

    public Producto(String nombre, int cantidad, double precio, String photoUrl) {
        super(nombre, cantidad, photoUrl);
        this.precio = precio;
    }

    public Producto(int id, String nombre, int cantidad, double precio, String photoUrl) {
        super(id, nombre, cantidad, photoUrl);
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Object[] showAll() {
        Object[] lista = { super.getId(), super.getNombre(), super.getCantidad(), precio };
        return lista;
    }

    @Override
    public String toString() {
        return "Producto [cantidad=" + super.getCantidad() + ", id=" + super.getId() + ", nombre=" + super.getNombre() + ", precio=" + precio + "]";

    }

}
