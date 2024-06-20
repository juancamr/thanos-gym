package com.uni.thanosgym.model;

import java.util.Date;

class Persona {

    private int id;
    private Date created_At;
    private String fullName;
    private String phone;
    private String email;
    private String photoUrl;

    public Persona() {}

    public Persona(int id, Date created_At, String fullName, String phone, String email, String photoUrl) {
        this.id = id;
        this.created_At = created_At;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.photoUrl = photoUrl;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {        
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
