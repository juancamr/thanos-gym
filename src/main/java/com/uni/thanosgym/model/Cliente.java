package com.uni.thanosgym.model;

import java.util.Date;

public class Cliente extends Persona {

    private String direccion;
    private String dni;

    // client_id INT NOT NULL AUTO_INCREMENT,
    // dni varchar(20) NOT NULL,
    // created_at DATE NOT NULL,
    // full_name VARCHAR(255) NOT NULL,
    // email VARCHAR(255) NOT NULL,
    // address VARCHAR(255),
    // phone VARCHAR(20),
    // photo_url VARCHAR(255),
    // PRIMARY KEY (client_id)

    public Cliente(String dni, Date created_At, String fullName, String email, String direccion,
            String phone, String photoUrl) {
        super(created_At, fullName, phone, email, photoUrl);
        this.dni = dni;
        this.direccion = direccion;
    }

    public Cliente(int id, String dni, Date created_At, String fullName, String email,
            String direccion, String phone, String photoUrl) {
        super(id, created_At, fullName, phone, email, photoUrl);
        this.dni = dni;
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String Direccion) {
        this.direccion = Direccion;
    }

    public Object[] showAll() {
        Object[] lista = { super.getId(), dni, super.getFullName(), super.getCreated_At(), super.getEmail(), super.getPhone(),
                direccion };
        return lista;
    }
}
