package com.uni.thanosgym.model;

import java.sql.Date;

public class Administrador extends Persona {

    private String username;
    private String password;
    private Date lastSignin;
    public static String usernameField = "username";
    public static String passwordField = "password";

    private Administrador(Builder builder) {
        super(builder.id, builder.createdAt, builder.fullName, builder.phone, builder.email);
        this.username = builder.username;
        this.password = builder.password;
        this.lastSignin = builder.lastSignin;
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

    public static class Builder {
        private int id;
        private String fullName;
        private String email;
        private int phone;
        private String username;
        private String password;
        private Date lastSignin;
        private Date createdAt;

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
