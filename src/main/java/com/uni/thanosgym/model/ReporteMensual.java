package com.uni.thanosgym.model;

public class ReporteMensual {
    private double total;
    private String mes;

    public ReporteMensual(String mes, double total) {
        this.total = total;
        this.mes = mes;
    }

    public double getTotal() {
        return total;
    }

    public String getMes() {
        return mes;
    }
}
