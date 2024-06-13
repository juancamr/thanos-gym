
package com.uni.thanosgym.model;

public class Utilities {
    

    public Utilities (){
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Utilities(int id, String tipo, String peso, String cantidad) {
        this.id = id;
        this.tipo = tipo;
        this.peso = peso;
        this.cantidad = cantidad;
    }
 
    private int id;
    private String tipo;
    private String peso;
    private String cantidad;
    
    
}
