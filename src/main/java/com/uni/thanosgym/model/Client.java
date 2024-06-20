package com.uni.thanosgym.model;

import java.util.Date;

public class Client extends Persona {

    private String direccion;
    private String dni;

    public static String tableName = "client";
    public static String idField = "client_id";
    public static String dniField = "dni";
    public static String createdAtField = "created_at";
    public static String fullNameField = "full_name";
    public static String emailField = "email";
    public static String addressField = "address";
    public static String phoneField = "phone";
    public static String photoUrlField = "photo_url";

    // Constructor privado para el Builder
    private Client(Builder builder) {
        super(builder.id, builder.createdAt, builder.fullName, builder.phone, builder.email, builder.photoUrl);
        this.direccion = builder.direccion;
        this.dni = builder.dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    // Builder estático
    public static class Builder {
        private int id;
        private Date createdAt;
        private String fullName;
        private String phone;
        private String email;
        private String photoUrl;
        private String direccion;
        private String dni;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
            return this;
        }

        // Métodos setters para Cliente
        public Builder setDireccion(String direccion) {
            this.direccion = direccion;
            return this;
        }

        public Builder setDni(String dni) {
            this.dni = dni;
            return this;
        }

        // Método build que retorna una instancia de Cliente
        public Client build() {
            return new Client(this);
        }
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "direccion='" + direccion + '\'' +
                ", dni='" + dni + '\'' +
                "} " + super.toString();
    }

    public Object[] showAll() {
        Object[] lista = { super.getId(), dni, super.getFullName(), super.getCreated_At(), super.getEmail(), super.getPhone(),
                direccion };
        return lista;
    }
}

