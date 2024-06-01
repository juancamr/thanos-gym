
package model;

public class Plan {

    private int planId;
    private String name;
    private double price;
    private int durationDays;

    public Plan() {
    }

    public Plan(int planId, String name, double price, int durationDays) {
        this.planId = planId;
        this.name = name;
        this.price = price;
        this.durationDays = durationDays;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
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
                "planId=" + planId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", durationDays=" + durationDays +
                '}';
    }
    
}
