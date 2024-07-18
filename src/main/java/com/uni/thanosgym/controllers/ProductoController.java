package com.uni.thanosgym.controllers;

import java.util.Map;

import com.uni.thanosgym.dao.CRUDProducto;
import com.uni.thanosgym.model.Producto;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;

public class ProductoController {

    public static boolean crearProducto(Map<String, Object> params) {
        if (params == null) {
            return false;
        }

        String codigo = (String) params.get("codigo");
        String nombre = (String) params.get("nombre");
        String precio = (String) params.get("precio");
        String cantidad = (String) params.get("cantidad");

        if (codigo.isEmpty()|| nombre.isEmpty() || precio.isEmpty() || cantidad.isEmpty()) {
            Messages.show("Debe completar todos los campos");
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

        Producto producto = new Producto.Builder()
                .setCodigo(codigo)
                .setNombre(nombre)
                .setPrecio(Double.parseDouble(precio))
                .setCantidad(Integer.parseInt(cantidad))
                .build();
        Response<Producto> response = CRUDProducto.getInstance().create(producto);

        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return false;
        }

        return true;
    }
}
