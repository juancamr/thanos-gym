package com.uni.thanosgym.model;

import java.util.Date;

public class Admin extends Persona {
    
    public static enum Rol {
        MASTER,
        EMPLEADO
    }

    private String username;
    private String password;
    private Rol rol;
    private Date lastSignin;
    private boolean persistencia = false;

    public static String tableName = "admin";
    public static String idField = "admin_id";
    public static String createdAtField = "created_at";
    public static String fullNameField = "full_name";
    public static String emailField = "email";
    public static String phoneField = "phone";
    public static String usernameField = "username";
    public static String passwordField = "password";
    public static String rolField = "rol";
    public static String photoUrlField = "photo_url";
    public static String lastSigninField = "last_signin";

    private Admin(Builder builder) {
        super(builder.id, builder.createdAt, builder.fullName, builder.phone, builder.email, builder.photoUrl);
        this.username = builder.username;
        this.password = builder.password;
        this.rol = builder.rol;
        this.lastSignin = builder.lastSignin;
        this.persistencia = builder.persistencia;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Date getLastSignin() {
        return lastSignin;
    }

    public void setLastSignin(Date lastSignin) {
        this.lastSignin = lastSignin;
    }

    public boolean isSesionPersistence() {
        return persistencia;
    }

    public void setPersistencia(boolean persistencia) {
        this.persistencia = persistencia;
    }
    
    public static class Builder {
        private int id;
        private Date createdAt;
        private String fullName;
        private String phone;
        private String email;
        private String photoUrl;

        private String username;
        private String password;
        private Rol rol;
        private Date lastSignin;
        private boolean persistencia = false;

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

        // Métodos setters para Administrador
        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setRol(Rol rol) {
            this.rol = rol;
            return this;
        }

        public Builder setLastSignin(Date lastSignin) {
            this.lastSignin = lastSignin;
            return this;
        }

        public Builder setPersistencia(boolean persistencia) {
            this.persistencia = persistencia;
            return this;
        }

        public Admin build() {
            return new Admin(this);
        }
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rol=" + rol +
                ", lastSignin=" + lastSignin +
                ", persistencia=" + persistencia +
                "} " + super.toString();
    }
}

