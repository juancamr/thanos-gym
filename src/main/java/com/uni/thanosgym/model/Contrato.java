package com.uni.thanosgym.model;

import java.util.Date;

public class Contrato {

    private int id;
    private Client cliente;
    private Plan plan;
    private Admin admin;
    private String transactionCode;
    private Date createdAt;
    private Date subscriptionUntil;
    private boolean isFrozen;
    private int freezeCount;
    private Date freezeUntil;
    private Date lastFreezeDate;

    public static String tableName = "contrato";
    public static String idField = "contrato_id";
    public static String clientIdField = "client_id";
    public static String planIdField = "plan_id";
    public static String adminIdField = "admin_id";
    public static String transactionCodeField = "transaction_code";
    public static String createdAtField = "created_at";
    public static String subscriptionUntilField = "subscription_until";
    public static String isFrozenField = "is_frozen";
    public static String freezeUntilField = "freeze_until";
    public static String freezeCountField = "freeze_count";
    public static String lastFreezeDateField = "last_freeze_date";

    // Constructor privado para el Builder
    private Contrato(Builder builder) {
        this.id = builder.id;
        this.cliente = builder.cliente;
        this.plan = builder.plan;
        this.admin = builder.admin;
        this.transactionCode = builder.transactionCode;
        this.isFrozen = builder.isFrozen;
        this.freezeCount = builder.freezeCount;
        this.freezeUntil = builder.freezeUntil;
        this.lastFreezeDate = builder.lastFreezeDate;
        this.createdAt = builder.createdAt;
        this.subscriptionUntil = builder.subscriptionUntil;
    }

    // Getters
    public int getId() {
        return id;
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public void increaseFreezeCount() {
        this.freezeCount += 1;
    }

    public Admin getAdmin() {
        return admin;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public boolean isCongelado() {
        return isFrozen;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getSubscriptionUntil() {
        return subscriptionUntil;
    }

    public boolean isFrozen() {
        return isFrozen;
    }

    public int getFreezeCount() {
        return freezeCount;
    }

    public Date getFreezeUntil() {
        return freezeUntil;
    }

    public Date getLastFreezeDate() {
        return lastFreezeDate;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setSubscriptionUntil(Date subscriptionUntil) {
        this.subscriptionUntil = subscriptionUntil;
    }

    public void setLastFreezeDate(Date lastFreezeDate) {
        this.lastFreezeDate = lastFreezeDate;
    }

    public void setIsFrozen(boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

    public String getEstado() {
        return this.isFrozen() ? "Congelado"
                : new Date().before(this.getSubscriptionUntil()) ? "Activo" : "Vencido";
    }

    public static class Builder {
        private int id;
        private Client cliente;
        private Plan plan;
        private Admin admin;
        private String transactionCode;
        private boolean isFrozen;
        private Date freezeUntil;
        private int freezeCount;
        private Date lastFreezeDate;
        private Date createdAt;
        private Date subscriptionUntil;

        // Métodos setters para Contrato
        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCliente(Client cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder setPlan(Plan plan) {
            this.plan = plan;
            return this;
        }

        public Builder setAdmin(Admin admin) {
            this.admin = admin;
            return this;
        }

        public Builder setTransactionCode(String transactionCode) {
            this.transactionCode = transactionCode;
            return this;
        }

        public Builder setCongelado(boolean congelado) {
            this.isFrozen = congelado;
            return this;
        }

        public Builder setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setSubscriptionUntil(Date subscriptionUntil) {
            this.subscriptionUntil = subscriptionUntil;
            return this;
        }

        public Builder setIsFrozen(boolean isFrozen) {
            this.isFrozen = isFrozen;
            return this;
        }

        public Builder setFreezeUntil(Date freezeUntil) {
            this.freezeUntil = freezeUntil;
            return this;
        }

        public Builder setFreezeCount(int freezeCount) {
            this.freezeCount = freezeCount;
            return this;
        }

        public Builder setLastFreezeDate(Date lastFreezeDate) {
            this.lastFreezeDate = lastFreezeDate;
            return this;
        }

        // Método build que retorna una instancia de Contrato
        public Contrato build() {
            return new Contrato(this);
        }
    }

    @Override
    public String toString() {
        return "Contrato{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", plan=" + plan +
                ", admin=" + admin +
                ", transactionCode='" + transactionCode + '\'' +
                ", congelado=" + isFrozen +
                ", createdAt=" + createdAt +
                ", subscriptionUntil=" + subscriptionUntil +
                '}';
    }
}
