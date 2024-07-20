package com.uni.thanosgym.controllers;

import java.util.Date;
import java.util.Map;

import com.uni.thanosgym.dao.CRUDProducto;
import com.uni.thanosgym.model.Producto;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;

public class ProductoController {

    public static boolean agregarStock(Map<String, Object> params) {
        if (params == null) {
            return false;
        }
        String precio = (String) params.get("precio");
        Date fechaVencimiento = (Date) params.get("fechaVencimiento");
        String cantidad = (String) params.get("cantidad");

        // si fecha vencimiento es antes de hoy
        if (fechaVencimiento.before(new Date())) {
            Messages.show("La fecha de vencimiento no puede ser anterior a la fecha actual");
            return false;
        }

        if (!StringUtils.isDecimal(precio)) {
            Messages.show("El precio debe ser un número");
            return false;
        }

        if (!StringUtils.isInteger(cantidad)) {
            Messages.show("La cantidad debe ser un número entero");
            return false;
        }
        return true;
    }

    public static boolean crearProducto(Map<String, Object> params) {
        if (params == null) {
            return false;
        }

        String codigo = (String) params.get("codigo");
        String nombre = (String) params.get("nombre");

        if (codigo.isEmpty() || nombre.isEmpty()) {
            Messages.show("Debe completar todos los campos");
            return false;
        }

        Producto producto = new Producto(codigo, nombre);
        Response<Producto> response = CRUDProducto.getInstance().create(producto);

        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return false;
        }

        return true;
    }
}
