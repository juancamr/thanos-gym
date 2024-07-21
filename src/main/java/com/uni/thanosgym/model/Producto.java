package com.uni.thanosgym.model;

public class Producto {

    private int id;
    private String nombre;
    private String codigo;
    private DetalleProducto[] detallesProducto;

    public static String tableName = "producto";
    public static String idField = "producto_id";
    public static String codigoField = "codigo";
    public static String nombreField = "nombre";

    public Producto(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getId() {
        return id;
    }

    public void setDetallesProducto(DetalleProducto[] detallesProducto) {
        this.detallesProducto = detallesProducto;
    }

    public DetalleProducto[] getDetallesProducto() {
        return detallesProducto;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre='" + nombre + '\'' + ", codigo='" + codigo + '\'';
    }
}
