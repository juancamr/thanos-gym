package com.uni.thanosgym.model;

import java.util.Date;

public class Contrato {

    int id;
    Cliente cliente;
    Plan plan;
    Administrador admin;
    String transactionCode;
    boolean congelado;
    Date createdAt;
    Date subscriptionUntil;

    // contrato_id INT NOT NULL AUTO_INCREMENT,
    // client_id INT NOT NULL,
    // plan_id INT NOT NULL,
    // admin_id INT NOT NULL,
    // transaction_code INT NOT NULL,
    // congelado
    // created_at DATETIME NOT NULL,
    // subscription_until DATE NOT NULL,
    // PRIMARY KEY (contrato_id),
    // FOREIGN KEY (client_id) REFERENCES client(client_id),
    // FOREIGN KEY (plan_id) REFERENCES plan(plan_id),
    // FOREIGN KEY (admin_id) REFERENCES admin(admin_id)

    public Contrato(Cliente cliente, Plan plan, Administrador admin, String transactionCode, boolean congelado,
            Date createdAt, Date subscriptionUntil) {
        this.cliente = cliente;
        this.plan = plan;
        this.admin = admin;
        this.transactionCode = transactionCode;
        this.congelado = congelado;
        this.createdAt = createdAt;
        this.subscriptionUntil = subscriptionUntil;
    }

    public Contrato(int id, Cliente cliente, Plan plan, Administrador admin, String transactionCode, boolean congelado,
            Date createdAt, Date subscriptionUntil) {
        this.id = id;
        this.cliente = cliente;
        this.plan = plan;
        this.admin = admin;
        this.transactionCode = transactionCode;
        this.congelado = congelado;
        this.createdAt = createdAt;
        this.subscriptionUntil = subscriptionUntil;
    }

    // getters and setters

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
