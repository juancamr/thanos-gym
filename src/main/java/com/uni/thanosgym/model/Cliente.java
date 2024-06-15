package com.uni.thanosgym.model;

import java.util.Date;

public class Cliente extends Persona {

    private String direccion;
    private Date subscription_until;
    private int dni;
    public static String addressField = "address";
    public static String subscriptionUntilField = "subscription_until";
    public static String dniField = "dni";
    public static String idField = "client_id";

    public Cliente() {
        super();
    }

    public Cliente(int dni, Date created_At, Date subscription_until, String fullName, String email,
            String Direccion, int phone) {
        super(created_At, fullName, phone, email);
        this.dni = dni;
        this.subscription_until = subscription_until;
        this.direccion = Direccion;
    }

    public Cliente(int id, int dni, Date created_At, Date subscription_until, String fullName, String email,
            String Direccion, int phone) {
        super(id, created_At, fullName, phone, email);
        this.dni = dni;
        this.subscription_until = subscription_until;
        this.direccion = Direccion;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Date getSubscription_until() {
        return subscription_until;
    }

    public void setSubscription_until(Date subscription_until) {
        this.subscription_until = subscription_until;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String Direccion) {
        this.direccion = Direccion;
    }

    public Object[] showAll() {
        Object[] lista = {  idField, dni, fullNameField, createdAtField, emailField, phoneField, direccion };
        return lista;
    }
    
}
