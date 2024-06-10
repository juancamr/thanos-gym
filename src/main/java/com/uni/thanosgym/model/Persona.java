package com.uni.thanosgym.model;

import java.util.Date;

class Persona {

    private int id;
    private Date created_At;
    private String fullName;
    private int phone;
    private String email;
    public static String createdAtField = "created_at";
    public static String fullNameField = "full_name";
    public static String phoneField = "phone";
    public static String emailField = "email";
    
    public Persona() {
    }

    public Persona(int id, Date created_At, String fullName, int phone, String email) {
        this.id = id;
        this.created_At = created_At;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String nombre) {
        this.fullName = nombre;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int telefono) {
        this.phone = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
