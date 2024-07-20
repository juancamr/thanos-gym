package com.uni.thanosgym.model;

import java.util.Date;

public class DetalleProducto {
    private int id;
    private Producto producto;
    private int stock;
    private Date fechaVencimiento;
    private Date createdAt;
    private double precio;

    public static String tableName = "detalle_producto";
    public static String idField = "detalle_producto_id";
    public static String productoIdField = "producto_id";
    public static String stockField = "stock";
    public static String fechaVencimientoField = "fecha_vencimiento";
    public static String createdAtField = "created_at";
    public static String precioField = "precio";

    private DetalleProducto(Builder builder) {
        this.id = builder.id;
        this.stock = builder.stock;
        this.fechaVencimiento = builder.fechaVencimiento;
        this.createdAt = builder.createdAt;
        this.producto = builder.producto;
        this.precio = builder.precio;
    }

    public int getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public double getPrecio() {
        return precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public static class Builder {
        private int id;
        private Producto producto;
        private int stock;
        private Date fechaVencimiento;
        private Date createdAt;
        private double precio;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setProducto(Producto producto) {
            this.producto = producto;
            return this;
        }

        public Builder setStock(int stock) {
            this.stock = stock;
            return this;
        }

        public Builder setFechaVencimiento(Date fechaVencimiento) {
            this.fechaVencimiento = fechaVencimiento;
            return this;
        }

        public Builder setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setPrecio(double precio) {
            this.precio = precio;
            return this;
        }

        public DetalleProducto build() {
            return new DetalleProducto(this);
        }
    }

    @Override
    public String toString() {
        return "DetalleProducto{" +
                "id=" + id +
                ", producto=" + producto +
                ", stock=" + stock +
                ", fechaVencimiento=" + fechaVencimiento +
                ", createdAt=" + createdAt +
                ", precio=" + precio +
                '}';
    }
}
