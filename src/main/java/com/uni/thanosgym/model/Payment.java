package com.uni.thanosgym.model;

import java.util.Date;

public class Payment {

    Date createdAt;
    int id;
    int ticketCode;
    int transactionCode;
    Cliente cliente;
    Plan plan;

    /**
     * Constructor for Payment
     * 
     * @param createdAt
     * @param ticketCode
     * @param transactionCode
     * @param cliente
     * @param plan
     * @return Payment
     */
    public Payment(Date createdAt, int ticketCode, int transactionCode, Cliente cliente, Plan plan) {
        this.createdAt = createdAt;
        this.ticketCode = ticketCode;
        this.transactionCode = transactionCode;
        this.cliente = cliente;
        this.plan = plan;
    }

    /**
     * Constructor for Payment
     * 
     * @param id
     * @param createdAt
     * @param ticketCode
     * @param transactionCode
     * @param cliente
     * @param plan
     * @return Payment
     */
    public Payment(int id, Date createdAt, int ticketCode, int transactionCode, Cliente cliente, Plan plan) {
        this.createdAt = createdAt;
        this.id = id;
        this.ticketCode = ticketCode;
        this.transactionCode = transactionCode;
        this.cliente = cliente;
        this.plan = plan;
    }

    // getters and setters

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(int ticketCode) {
        this.ticketCode = ticketCode;
    }

    public int getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(int transactionCode) {
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
