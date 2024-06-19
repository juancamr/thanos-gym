package com.uni.thanosgym.model;

import java.util.Date;

public class Boleta {

    private int id;
    private Cliente cliente;
    private Administrador admin;
    private Date createdAt;
    private double total;
    private Producto[] productos;

    public Boleta(Cliente cliente, Administrador admin, Date createdAt, double total) {
        this.cliente = cliente;
        this.admin = admin;
        this.createdAt = createdAt;
        this.total = total;
    }

    public Boleta(int id, Cliente cliente, Administrador admin, Date createdAt, double total, Producto[] productos) {
        this.id = id;
        this.cliente = cliente;
        this.admin = admin;
        this.createdAt = createdAt;
        this.total = total;
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Administrador getAdmin() {
        return admin;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public double getTotal() {
        return total;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }

    public Object[] showAll() {
        Object[] lista = { id, cliente.getFullName(), admin.getFullName(), createdAt, total };
        return lista;
    }

}
