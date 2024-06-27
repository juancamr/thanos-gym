package com.uni.thanosgym.model;

public class Proveedor {

    private int id;
    private String nombre;
    private String ruc;
    private String phone;
    private String address;
    private boolean isVisible;

    public static String tableName = "proveedor";
    public static String idField = "proveedor_id";
    public static String nombreField = "nombre";
    public static String rucField = "ruc";
    public static String phoneField = "phone";
    public static String addressField = "address";
    public static String createdAtField = "created_at";
    public static String isVisibleField = "is_visible";

    // Constructor privado para el Builder
    private Proveedor(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.ruc = builder.ruc;
        this.phone = builder.phone;
        this.address = builder.address;
        this.isVisible = builder.isVisible;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public boolean getIsVisible() {
        return isVisible;
    }

    // Builder estático
    public static class Builder {
        private int id;
        private String nombre;
        private String ruc;
        private String phone;
        private String address;
        private boolean isVisible;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder ruc(String ruc) {
            this.ruc = ruc;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder isVisible(boolean isVisible) {
            this.isVisible = isVisible;
            return this;
        }

        // Método build que retorna una instancia de Cliente
        public Proveedor build() {
            return new Proveedor(this);
        }
    }
}
