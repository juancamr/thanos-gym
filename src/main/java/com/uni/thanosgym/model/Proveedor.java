package com.uni.thanosgym.model;

import java.util.Date;

public class Proveedor {

    private int id;
    private String nombre;
    private String ruc;
    private String phone;
    private String address;
    private boolean isVisible = true;
    private Date createdAt;

    public static String tableName = "proveedor";
    public static String idField = "proveedor_id";
    public static String nombreField = "nombre";
    public static String rucField = "ruc";
    public static String phoneField = "phone";
    public static String addressField = "address";
    public static String isVisibleField = "is_visible";
    public static String createdAtField = "created_at";

    public Proveedor(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.ruc = builder.ruc;
        this.phone = builder.phone;
        this.address = builder.address;
        this.isVisible = builder.isVisible;
        this.createdAt = builder.createdAt;
    }

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

    public boolean isVisible() {
        return isVisible;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static class Builder {

        private int id;
        private String nombre;
        private String ruc;
        private String phone;
        private String address;
        private boolean isVisible;
        private Date createdAt;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setRuc(String ruc) {
            this.ruc = ruc;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setIsVisible(boolean isVisible) {
            this.isVisible = isVisible;
            return this;
        }

        public Builder setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Proveedor build() {
            return new Proveedor(this);
        }
    }

    @Override
    public String toString() {
        return "Proveedor [address=" + address + ", createdAt=" + createdAt + ", id=" + id + ", isVisible=" + isVisible
                + ", nombre=" + nombre + ", phone=" + phone + ", ruc=" + ruc + "]";
    }
}
