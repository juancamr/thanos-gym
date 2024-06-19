package com.uni.thanosgym.model;

public class Utility extends Item {

    private Administrador admin;
    private int peso;

    // utility_id INT NOT NULL AUTO_INCREMENT,
    // admin_id INT NOT NULL,
    // nombre VARCHAR(255) NOT NULL,
    // peso DECIMAL(10,2) NOT NULL,
    // cantidad INT NOT NULL,
    // photo_url VARCHAR(255),
    // PRIMARY KEY (utility_id),
    // FOREIGN key (admin_id) REFERENCES admin(admin_id)

    public Utility(Administrador admin, String nombre, int peso, int cantidad, String photoUrl) {
        super(nombre, cantidad, photoUrl);
        this.admin = admin;
        this.peso = peso;
    }

    public Utility(int id, Administrador admin, String nombre, int peso, int cantidad, String photoUrl) {
        super(id, nombre, cantidad, photoUrl);
        this.admin = admin;
        this.peso = peso;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }

    public Object[] showAll() {
        Object[] lista = { super.getId(), super.getNombre(), super.getCantidad(), peso };
        return lista;
    }

}
