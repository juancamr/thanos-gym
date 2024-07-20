package com.uni.thanosgym.controllers;

import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.utils.UserPreferences;
import com.uni.thanosgym.dao.CRUDBoleta;
import com.uni.thanosgym.dao.CRUDCliente;
import com.uni.thanosgym.dao.CRUDDetalleBoleta;
import com.uni.thanosgym.dao.CRUDProducto;
import com.uni.thanosgym.model.Boleta;
import com.uni.thanosgym.model.Client;
import com.uni.thanosgym.model.DetalleBoleta;
import com.uni.thanosgym.model.Producto;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Messages;

public class VentaController {

    public static Client setCliente(String dni) {
        if (dni.isEmpty()) {
            Messages.show("Debe completar el DNI");
            return null;
        }
        if (!StringUtils.isValidDni(dni)) {
            Messages.show("El DNI no es valido");
            return null;
        }

        Response<Client> res = CRUDCliente.getInstance().getByDni(dni);
        if (!res.isSuccess()) {
            Messages.show(res.getMessage());
            return null;
        }
        Client cliente = res.getData();

        return cliente;
    }

    public static boolean crearVenta(Map<String, Object> params) {
        if (params == null) {
            return false;
        }

        return true;
    }

    public static DetalleBoleta agregarProducto(String cantidad, Producto producto) {
        if (cantidad.isEmpty()) {
            Messages.show("Debe ingresar una cantidad");
            return null;
        }
        if (!StringUtils.isInteger(cantidad)) {
            Messages.show("La cantidad debe ser un número");
            return null;
        }
        int cant = Integer.parseInt(cantidad);
        if (cant <= 0) {
            Messages.show("La cantidad debe ser mayor a 0");
            return null;
        }
        if (producto == null) {
            Messages.show("Debe seleccionar un producto");
            return null;
        }

        DetalleBoleta detalle = new DetalleBoleta.Builder()
                .setCantidad(Integer.parseInt(cantidad))
                .setProducto(producto)
                .setTotal(producto.getPrecio() * Integer.parseInt(cantidad))
                .build();
        return detalle;
    }

    public static Producto getProducto(String codigo) {
        if (codigo.isEmpty()) {
            Messages.show("Debe completar el codigo del producto");
            return null;
        }
        Response<Producto> res = CRUDProducto.getInstance().getByCodigo(codigo);
        if (!res.isSuccess()) {
            Messages.show(res.getMessage());
            return null;
        }
        return res.getData();
    }

    public static boolean generarVenta(Client client, List<DetalleBoleta> detalles) {
        if (client == null) {
            Messages.show("Debe establecer un cliente");
            return false;
        }
        if (detalles.isEmpty()) {
            Messages.show("Debe agregar al menos un producto");
            return false;
        }

        double total = detalles.stream().mapToDouble(DetalleBoleta::getTotal).sum();
        Boleta boleta = new Boleta.Builder()
                .setCliente(client)
                .setAdmin(UserPreferences.getData())
                .setTotal(total)
                .build();

        Response<Boleta> response = CRUDBoleta.getInstance().create(boleta);
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return false;
        }
        System.out.println("creado con exito");
        boleta.setId(response.getId());

        for (DetalleBoleta detalle : detalles) {
            detalle.setBoleta(boleta);
            CRUDDetalleBoleta.getInstance().create(detalle);
        }
        System.out.println("detalles creados con exito");
        return true;
    }
}
