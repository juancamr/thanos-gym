package com.uni.thanosgym.model;

import java.util.Date;

public class Administrador extends Persona {

    public static enum Rol {
        MASTER,
        EMPLEADO
    }

    private String username;
    private String password;
    private Rol rol;
    private Date lastSignin;
    private boolean persistencia = false;

    // admin_id INT NOT NULL AUTO_INCREMENT,
    // created_at DATE NOT NULL,
    // full_name VARCHAR(255) NOT NULL,
    // email VARCHAR(255) NOT NULL,
    // phone VARCHAR(20),
    // username VARCHAR(255) NOT NULL,
    // password VARCHAR(255) NOT NULL,
    // rol ENUM('MASTER', 'EMPLEADO'),
    // photo_url VARCHAR(255),
    // last_signin DATE NOT NULL,
    // PRIMARY KEY (admin_id)

    public Administrador() {
        super();
    }

    public Administrador(Date createdAt, String fullName, String phone, String email, String username, String password,
            Rol rol, String photoUrl, Date lastSignin) {
        super(createdAt, fullName, phone, email, photoUrl);
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.lastSignin = lastSignin;
    }

    public Administrador(int id, Date createdAt, String fullName, String phone, String email, String username,
            String password, Rol rol, String photoUrl, Date lastSignin) {
        super(id, createdAt, fullName, phone, email, photoUrl);
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.lastSignin = lastSignin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getLastSignin() {
        return lastSignin;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isSesionPersistente() {
        return persistencia;
    }

    public void setLastSignin(Date last) {
        this.lastSignin = last;
    }

    public void setPersistencia(boolean per) {
        this.persistencia = per;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "admin_id=" + super.getId() +
                ", full_name='" + super.getFullName() + '\'' +
                ", email='" + super.getEmail() + '\'' +
                ", phone=" + super.getPhone() +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
