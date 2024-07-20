package com.uni.thanosgym.model;

public class DetalleBoleta {

    private int id;
    private int boletaId;
    private int cantidad;
    private double precio;
    private Producto producto;

    public static String tableName = "detalle_boleta";
    public static String idField = "detalle_boleta_id";
    public static String boletaIdField = "boleta_id";
    public static String productoIdField = "producto_id";
    public static String cantidadField = "cantidad";
    public static String precioField = "precio";
    public static String totalField = "total";

    // Constructor privado para el Builder
    private DetalleBoleta(Builder builder) {
        this.id = builder.id;
        this.boletaId = builder.boletaId;
        this.producto = builder.producto;
        this.cantidad = builder.cantidad;
        this.precio = builder.precio;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getIdBoleta() {
        return boletaId;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public int getBoletaId() {
        return boletaId;
    }

    public void setIdBoleta(int boletaId) {
        this.boletaId = boletaId;
    }

    public double getTotal() {
        return this.precio * this.cantidad;
    }

    // Builder estático
    public static class Builder {
        private int id;
        private int boletaId;
        private Producto producto;
        private int cantidad;
        private double precio;

        // Métodos setters para DetalleBoleta
        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setBoletaId(int boleta) {
            this.boletaId = boleta;
            return this;
        }

        public Builder setProducto(Producto producto) {
            this.producto = producto;
            return this;
        }

        public Builder setCantidad(int cantidad) {
            this.cantidad = cantidad;
            return this;
        }

        public Builder setPrecio(double precio) {
            this.precio = precio;
            return this;
        }

        public Builder setTotal(double precio) {
            this.precio = precio;
            return this;
        }

        // Método build que retorna una instancia de DetalleBoleta
        public DetalleBoleta build() {
            return new DetalleBoleta(this);
        }
    }

    @Override
    public String toString() {
        return "DetalleBoleta{" +
                "id=" + id +
                ", boletaid=" + boletaId +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }
}
