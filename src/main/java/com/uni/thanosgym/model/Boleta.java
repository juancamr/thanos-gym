package com.uni.thanosgym.model;

import java.util.Date;

public class Boleta {

    private int id;
    private Client cliente;
    private Admin admin;
    private Date createdAt;
    private double total;
    private DetalleBoleta[] detallesBoleta;

    public static String tableName = "boleta";
    public static String idField = "boleta_id";
    public static String clientIdField = "client_id";
    public static String adminIdField = "admin_id";
    public static String createdAtField = "created_at";
    public static String totalField = "total_boleta";

    // Constructor privado para el Builder
    private Boleta(Builder builder) {
        this.id = builder.id;
        this.cliente = builder.cliente;
        this.admin = builder.admin;
        this.createdAt = builder.createdAt;
        this.total = builder.total;
        this.detallesBoleta = builder.detallesBoleta;
    }

    // Getters
    public int getId() {
        return id;
    }

    public Client getCliente() {
        return cliente;
    }

    public Admin getAdmin() {
        return admin;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public double getTotal() {
        return total;
    }

    public DetalleBoleta[] getDetallesBoleta() {
        return detallesBoleta;
    }

    // Builder estático
    public static class Builder {
        private int id;
        private Client cliente;
        private Admin admin;
        private Date createdAt;
        private double total;
        private DetalleBoleta[] detallesBoleta;

        // Métodos setters para Boleta
        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCliente(Client cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder setAdmin(Admin admin) {
            this.admin = admin;
            return this;
        }

        public Builder setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setTotal(double total) {
            this.total = total;
            return this;
        }

        public Builder setDetallesBoleta(DetalleBoleta[] detalleBoletas) {
            this.detallesBoleta = detalleBoletas;
            return this;
        }

        // Método build que retorna una instancia de Boleta
        public Boleta build() {
            return new Boleta(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Boleta{")
          .append("id=").append(id)
          .append(", cliente=").append(cliente)
          .append(", admin=").append(admin)
          .append(", createdAt=").append(createdAt)
          .append(", total=").append(total)
          .append(", detallesBoleta=[");
        if (detallesBoleta != null) {
            for (DetalleBoleta detalleBoleta : detallesBoleta) {
                sb.append(detalleBoleta).append(", ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}

