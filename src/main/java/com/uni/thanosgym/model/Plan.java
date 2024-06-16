package com.uni.thanosgym.model;

public class Plan {

    private int id;
    private String name;
    private double price;
    private int durationDays;
    private String indicador;
    public static String idField = "plan_id";
    public static String nameField = "name";
    public static String priceField = "price";
    public static String durationField = "duration_days";

    public Plan() {
    }

    public Plan(String name, double price, int durationDays, String indicador) {
        this.name = name;
        this.price = price;
        this.durationDays = durationDays;
        this.indicador = indicador;
    }

    public Plan(int planId, String name, double price, int durationDays, String indicador) {
        this.id = planId;
        this.name = name;
        this.price = price;
        this.indicador = indicador;
        this.durationDays = durationDays;
    }

    public int getId() {
        return id;
    }

    public void setId(int planId) {
        this.id = planId;
    }

    public String getName() {
        return name;
    }

    public Plan setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Plan setPrice(double price) {
        this.price = price;
        return this;
    }

    public Plan setIndicador(String indicador) {
        this.indicador = indicador;
        return this;
    }

    public String getIndicador() {
        return indicador;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public Plan setDurationDays(int durationDays) {
        this.durationDays = durationDays;
        return this;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "planId=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", durationDays=" + durationDays +
                '}';
    }

}
