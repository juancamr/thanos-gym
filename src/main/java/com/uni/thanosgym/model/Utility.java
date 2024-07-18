package com.uni.thanosgym.model;

public class Utility extends Item {

    private Admin admin;
    private Proveedor proveedor;
    private double peso;

    public static String tableName = "utility";
    public static String idField = "utility_id";
    public static String adminIdField = "admin_id";
    public static String proveedorField = "proveedor_id";
    public static String nombreField = "nombre";
    public static String pesoField = "peso";
    public static String cantidadField = "cantidad";
    public static String photoUrlField = "photo_url";

    // Constructor privado para el Builder
    private Utility(Builder builder) {
        super(builder.id, builder.nombre, builder.cantidad, builder.photoUrl);
        this.admin = builder.admin;
        this.peso = builder.peso;
        this.proveedor = builder.proveedor;
    }

    // Getters
    public Admin getAdmin() {
        return admin;
    }

    public double getPeso() {
        return peso;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    // Builder estático
    public static class Builder {
        private int id;
        private String nombre;
        private int cantidad;
        private String photoUrl;
        private Admin admin;
        private double peso;
        private Proveedor proveedor;

        // Métodos setters para Utility
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

        public Builder setAdmin(Admin admin) {
            this.admin = admin;
            return this;
        }

        public Builder setProveedor(Proveedor proveedor) {
            this.proveedor = proveedor;
            return this;
        }

        public Builder setPeso(double peso) {
            this.peso = peso;
            return this;
        }

        public Utility build() {
            return new Utility(this);
        }
    }

    @Override
    public String toString() {
        return "Utility{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", cantidad=" + getCantidad() +
                ", photoUrl='" + getPhotoUrl() + '\'' +
                ", admin=" + admin +
                ", peso=" + peso +
                '}';
    }

    public Object[] showAll() {
        Object[] lista = { super.getId(), super.getNombre(), super.getCantidad(), peso };
        return lista;
    }
}
