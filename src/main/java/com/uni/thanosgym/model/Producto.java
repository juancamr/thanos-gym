package com.uni.thanosgym.model;

public class Producto extends Item {

    private double precio;

    public static String tableName = "producto";
    public static String idField = "producto_id";
    public static String nombreField = "nombre";
    public static String cantidadField = "cantidad";
    public static String precioField = "precio";
    public static String photoUrlField = "photo_url";

    private Producto(Builder builder) {
        super(builder.id, builder.nombre, builder.cantidad, builder.photoUrl);
        this.precio = builder.precio;
    }

    // Getter
    public double getPrecio() {
        return precio;
    }

    // Builder estático
    public static class Builder {
        private int id;
        private String nombre;
        private int cantidad;
        private String photoUrl;
        private double precio;

        // Métodos setters para Producto
        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setCantidad(int cantidad) {
            this.cantidad = cantidad;
            return this;
        }

        public Builder setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
            return this;
        }

        public Builder setPrecio(double precio) {
            this.precio = precio;
            return this;
        }

        public Producto build() {
            return new Producto(this);
        }
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", cantidad=" + getCantidad() +
                ", photoUrl='" + getPhotoUrl() + '\'' +
                ", precio=" + precio +
                '}';
    }

    public Object[] showAll() {
        Object[] lista = { super.getId(), super.getNombre(), super.getCantidad(), precio };
        return lista;
    }
}

