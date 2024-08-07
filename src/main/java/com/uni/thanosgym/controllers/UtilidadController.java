package com.uni.thanosgym.controllers;

import java.util.Map;

import java.io.File;

import com.uni.thanosgym.dao.CRUDProveedor;
import com.uni.thanosgym.dao.CRUDUtilidad;
import com.uni.thanosgym.model.Admin;
import com.uni.thanosgym.model.Proveedor;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.model.Utility;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.utils.Uploader;
import com.uni.thanosgym.utils.UserPreferences;

public class UtilidadController {

    public static int getQuantity() {
        return CRUDProveedor.getInstance().getAll().getDataList().size();
    }

    public static boolean crearUtilidad(Map<String, Object> params) {
        if (params == null) {
            return false;
        }
        Admin admin = UserPreferences.getData();

        String nombre = (String) params.get("nombre");
        String peso = (String) params.get("peso");
        String cantidad = (String) params.get("cantidad");
        File image = (File) params.get("image");
        int proveedorId = (int) params.get("proveedor_id");

        if (nombre.isEmpty() || peso.isEmpty() || cantidad.isEmpty()) {
            Messages.show("Complete todos los campos");
            return false;
        }
        if (!StringUtils.isDecimal(peso)) {
            Messages.show("El peso debe ser un número");
            return false;
        }

        if (!StringUtils.isInteger(cantidad)) {
            Messages.show("La cantidad debe ser un número");
            return false;
        }

        Proveedor proveedor = new Proveedor.Builder().build();
        proveedor.setId(proveedorId);
        Utility utility = new Utility.Builder()
                .setAdmin(admin)
                .setNombre(nombre)
                .setProveedor(proveedor)
                .setPeso(Double.parseDouble(peso))
                .setCantidad(Integer.parseInt(cantidad))
                .setPhotoUrl("")
                .build();


        Response<Utility> response = CRUDUtilidad.getInstance().create(utility);
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return false;
        }
        return true;

    }
}
