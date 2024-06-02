package model;

import java.time.LocalDate;

public class Cliente {

    private int ClienteId;
    private int planId;
    private int dni;
    private LocalDate created_At;
    private LocalDate subscription_since;
    private String nombre;
    private int telefono;
    private String Direccion;
    private String email;

    public Cliente(int ClienteId, int planId, int dni, LocalDate created_At, LocalDate subscription_since, String nombre, String email, String Direccion, int telefono) {
        this.ClienteId = ClienteId;
        this.planId = planId;
        this.dni = dni;
        this.created_At = created_At;
        this.subscription_since = subscription_since;
        this.nombre = nombre;
        this.telefono = telefono;
        this.Direccion = Direccion;
        this.email = email;
    }

    public Cliente() {
        }

    public int getClienteId() {
        return ClienteId;
    }

    public void setClienteId(int ClienteId) {
        this.ClienteId = ClienteId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }
    
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public LocalDate getCreated_At() {
        return created_At;
    }

    public void setCreated_At(LocalDate created_At) {
        this.created_At = created_At;
    }

    public LocalDate getSubscription_since() {
        return subscription_since;
    }

    public void setSubscription_since(LocalDate subscription_since) {
        this.subscription_since = subscription_since;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
