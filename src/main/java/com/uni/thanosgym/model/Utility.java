package com.uni.thanosgym.model;

public class Utility {

    private int id;
    private String nombre;
    private int cantidad;
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

    // Constructor privado para el Builder
    private Utility(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.cantidad = builder.cantidad;
        this.admin = builder.admin;
        this.peso = builder.peso;
        this.proveedor = builder.proveedor;
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

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
                ", admin=" + admin +
                ", peso=" + peso +
                '}';
    }

    public Object[] showAll() {
        Object[] lista = { id, nombre, cantidad, peso };
        return lista;
    }
}
