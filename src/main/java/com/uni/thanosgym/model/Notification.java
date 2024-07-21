package com.uni.thanosgym.model;

public class Notification {
    private Runnable action;
    private String descripcion;

    public Notification(String message, Runnable action) {
        this.descripcion = message;
        this.action = action;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Runnable getAction() {
        return action;
    }
}
