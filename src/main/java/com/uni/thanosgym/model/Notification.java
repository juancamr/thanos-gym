package com.uni.thanosgym.model;

public class Notification {
    private Runnable action;
    private String message;

    public Notification(String message, Runnable action) {
        this.message = message;
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public Runnable getAction() {
        return action;
    }
}
