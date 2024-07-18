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

    public static void setCliente(String dni, Map<String, JLabel> map) {
        if (dni.isEmpty()) {
            Messages.show("Debe completar el DNI");
            return;
        }
        if (!StringUtils.isValidDni(dni)) {
            Messages.show("El DNI no es valido");
            return;
        }

        Response<Client> res = CRUDCliente.getInstance().getByDni(dni);
        if (!res.isSuccess()) {
            Messages.show(res.getMessage());
            return;
        }
        Client cliente = res.getData();
        map.get("dni").setText(dni);
        map.get("nombre").setText(cliente.getFullName());
        map.get("direccion").setText(cliente.getDireccion());
    }

    public static boolean crearVenta(Map<String, Object> params) {
        if (params == null) {
            return false;
        }

        return true;
    }

    public static void agregarProducto(String cantidad, Producto producto, JTable table,
            List<DetalleBoleta> detalles) {
        if (cantidad.isEmpty()) {
            Messages.show("Debe ingresar una cantidad");
            return;
        }
        if (!StringUtils.isInteger(cantidad)) {
            Messages.show("La cantidad debe ser un número");
            return;
        }
        int cant = Integer.parseInt(cantidad);
        if (cant <= 0) {
            Messages.show("La cantidad debe ser mayor a 0");
            return;
        }
        if (producto == null) {
            Messages.show("Debe seleccionar un producto");
            return;
        }

        DetalleBoleta detalle = new DetalleBoleta.Builder()
                .setCantidad(Integer.parseInt(cantidad))
                .setProducto(producto)
                .setTotal(producto.getPrecio() * Integer.parseInt(cantidad))
                .build();
        detalles.add(detalle);
        String[] datos = new String[] { cantidad, producto.getNombre(), String.valueOf(producto.getPrecio()),
                String.valueOf(detalle.getTotal()) };
        ((javax.swing.table.DefaultTableModel) table.getModel()).addRow(datos);
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
        boleta.setId(response.getId());

        for (DetalleBoleta detalle : detalles) {
            CRUDDetalleBoleta.getInstance().create(new DetalleBoleta.Builder()
                    .setBoleta(boleta)
                    .setProducto(detalle.getProducto())
                    .setCantidad(detalle.getCantidad())
                    .setTotal(detalle.getTotal())
                    .build());
            CRUDDetalleBoleta.getInstance().create(detalle);
        }
        return true;
    }
}
