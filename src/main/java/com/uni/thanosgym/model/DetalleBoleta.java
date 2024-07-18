package com.uni.thanosgym.model;

public class DetalleBoleta {

    private int id;
    private Boleta boleta;
    private Producto producto;
    private int cantidad;
    private double total;

    public static String tableName = "detalle_boleta";
    public static String idField = "detalle_boleta_id";
    public static String boletaIdField = "boleta_id";
    public static String productoIdField = "producto_id";
    public static String cantidadField = "cantidad";
    public static String precioField = "precio";

    // Constructor privado para el Builder
    private DetalleBoleta(Builder builder) {
        this.id = builder.id;
        this.boleta = builder.boleta;
        this.producto = builder.producto;
        this.cantidad = builder.cantidad;
        this.total = builder.precio;
    }

    // Getters
    public int getId() {
        return id;
    }

    public Boleta getBoleta() {
        return boleta;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getTotal() {
        return total;
    }

    // Builder estático
    public static class Builder {
        private int id;
        private Boleta boleta;
        private Producto producto;
        private int cantidad;
        private double precio;

        // Métodos setters para DetalleBoleta
        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setBoleta(Boleta boleta) {
            this.boleta = boleta;
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
                ", boleta=" + boleta +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                ", precio=" + total +
                '}';
    }
}
