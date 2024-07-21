package com.uni.thanosgym.controllers;

import com.uni.thanosgym.utils.Messages;
import java.util.Map;

import com.uni.thanosgym.dao.CRUDProveedor;
import com.uni.thanosgym.model.Proveedor;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.StringUtils;

public class ProveedorController {

    public static boolean registrar(Map<String, Object> params) {
        String nombre = (String) params.get("nombre");
        String ruc = (String) params.get("ruc");
        String phone = (String) params.get("phone");
        String address = (String) params.get("address");

        if (nombre.isEmpty() || ruc.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            Messages.show("Complete todos los campos");
            return false;
        }

        if (!StringUtils.isValidPhone(phone)) {
            Messages.show("El telefono debe ser un numero de 9 digitos.");
            return false;
        }

        if (!StringUtils.isValidRuc(ruc)) {
            Messages.show("El RUC no es valido");
            return false;
        }

        if (!StringUtils.isInteger(ruc)) {
            Messages.show("El RUC debe ser un número");
            return false;
        }

        Proveedor proveedor = new Proveedor.Builder()
                .setNombre(nombre)
                .setRuc(ruc)
                .setPhone(phone)
                .setAddress(address)
                .build();

        Response<Proveedor> response = CRUDProveedor.getInstance().create(proveedor);
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return false;
        }

        return true;
    }
}
