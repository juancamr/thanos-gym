package com.uni.thanosgym.model;

import java.util.Date;

public class Administrador extends Persona {

    public static enum Rol {
        MASTER,
        EMPLEADO
    }

    private String username;
    private String password;
    private Date lastSignin;
    private Rol rol;
    private boolean persistencia = false;

    private Administrador(Builder builder) {
        super(builder.id, builder.createdAt, builder.fullName, builder.phone, builder.email);
        this.username = builder.username;
        this.password = builder.password;
        this.lastSignin = builder.lastSignin;
        this.rol = builder.rol;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getLastSignin() {
        return lastSignin;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    public boolean isSesionPersistente() {
        return persistencia;
    }

    public void setLastSignin(Date last) {
        this.lastSignin = last;
    }

    public void setPersistencia(boolean per) {
        this.persistencia = per;
    }

    public static class Builder {
        private int id;
        private String fullName;
        private String email;
        private int phone;
        private String username;
        private String password;
        private Date lastSignin;
        private Date createdAt;
        private Rol rol;

        public Builder() {
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhone(int phone) {
            this.phone = phone;
            return this;
        }

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

        public Administrador build() {
            return new Administrador(this);
        }
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "admin_id=" + super.getId() +
                ", full_name='" + super.getFullName() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", phone=" + super.getPhone() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
