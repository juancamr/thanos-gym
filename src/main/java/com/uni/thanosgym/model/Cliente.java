package com.uni.thanosgym.model;

import java.util.Date;

public class Cliente extends Persona {

    private String Direccion;
    private Date subscription_since;
    private int dni;
    private Plan plan;

    public Cliente(int id, Plan plan, int dni, Date created_At, Date subscription_since, String fullName, String email,
            String Direccion, int phone) {
        super(id, created_At, fullName, phone, email);
        this.plan = plan;
        this.dni = dni;
        this.subscription_since = subscription_since;
        this.Direccion = Direccion;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Date getSubscription_since() {
        return subscription_since;
    }

    public void setSubscription_since(Date subscription_since) {
        this.subscription_since = subscription_since;
    }


    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

}
