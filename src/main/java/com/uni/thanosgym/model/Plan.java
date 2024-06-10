
package com.uni.thanosgym.model;

public class Plan {

    private int id;
    private String name;
    private double price;
    private int durationDays;
    public static String idField = "plan_id";
    public static String nameField = "name";
    public static String priceField = "price";
    public static String durationField = "duration_days";

    public Plan() {
    }

    public Plan(String name, double price, int durationDays) {
        this.name = name;
        this.price = price;
        this.durationDays = durationDays;
    }
    
    public Plan(int planId, String name, double price, int durationDays) {
        this.id = planId;
        this.name = name;
        this.price = price;
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
