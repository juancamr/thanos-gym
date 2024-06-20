package com.uni.thanosgym.model;

import java.util.Date;

public class Asistencia {
    private int id;
    private Client cliente;
    private Date ingreso;

    public static String tableName = "asistencia";
    public static String idField = "asistencia_id";
    public static String clientIdField = "client_id";
    public static String ingresoField = "ingreso";

    // Constructor privado para el Builder
    private Asistencia(Builder builder) {
        this.id = builder.id;
        this.cliente = builder.cliente;
        this.ingreso = builder.ingreso;
    }

    // Getters
    public int getId() {
        return id;
    }

    public Client getCliente() {
        return cliente;
    }

    public Date getIngreso() {
        return ingreso;
    }

    // Builder estático
    public static class Builder {
        private int id;
        private Client cliente;
        private Date ingreso;

        // Métodos setters para Asistencia
        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCliente(Client cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder setIngreso(Date ingreso) {
            this.ingreso = ingreso;
            return this;
        }

        // Método build que retorna una instancia de Asistencia
        public Asistencia build() {
            return new Asistencia(this);
        }
    }

    @Override
    public String toString() {
        return "Asistencia{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", ingreso=" + ingreso +
                '}';
    }
}

