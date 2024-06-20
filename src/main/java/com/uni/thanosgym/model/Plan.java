package com.uni.thanosgym.model;

public class Plan {

    private int id;
    private String name;
    private double price;
    private int durationDays;
    private boolean isVisible;
    
    public static String tableName = "plan";
    public static String idField = "plan_id";
    public static String nameField = "name";
    public static String priceField = "price";
    public static String durationDaysField = "duration_days";
    public static String isVisibleField = "is_visible";

    // Constructor privado para el Builder
    private Plan(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.durationDays = builder.durationDays;
        this.isVisible = builder.isVisible;
    }

    // Getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getDurationDays() {
        return durationDays;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    // Builder estático
    public static class Builder {
        private int id;
        private String name;
        private double price;
        private int durationDays;
        private boolean isVisible;

        // Métodos setters para Plan
        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setDurationDays(int durationDays) {
            this.durationDays = durationDays;
            return this;
        }

        public Builder setIsVisible(boolean isVisible) {
            this.isVisible = isVisible;
            return this;
        }

        // Método build que retorna una instancia de Plan
        public Plan build() {
            return new Plan(this);
        }
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", durationDays=" + durationDays +
                ", isVisible=" + isVisible +
                '}';
    }
}
