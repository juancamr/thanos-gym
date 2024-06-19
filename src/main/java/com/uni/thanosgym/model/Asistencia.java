package com.uni.thanosgym.model;

import java.util.Date;

public class Asistencia {
    int id;
    Cliente cliente;
    Date ingreso;

    public Asistencia(Cliente cli, Date ingreso) {
        this.cliente = cli;
        this.ingreso = ingreso;
    }

    public Asistencia(int id, Cliente cli, Date ingreso) {
        this.id = id;
        this.cliente = cli;
        this.ingreso = ingreso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getIngreso() {
        return ingreso;
    }

    public void setIngreso(Date ingreso) {
        this.ingreso = ingreso;
    }

}
