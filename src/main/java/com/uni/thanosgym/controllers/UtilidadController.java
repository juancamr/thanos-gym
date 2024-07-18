package com.uni.thanosgym.controllers;

import java.util.Map;

import java.io.File;

import com.uni.thanosgym.dao.CRUDUtilidad;
import com.uni.thanosgym.model.Admin;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.model.Utility;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.utils.Uploader;
import com.uni.thanosgym.utils.UserPreferences;

public class UtilidadController {

    public static boolean crearUtilidad(Map<String, Object> params) {
        Admin admin = UserPreferences.getData();

        String nombre = (String) params.get("nombre");
        String peso = (String) params.get("peso");
        String cantidad = (String) params.get("cantidad");
        File image = (File) params.get("image");

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

        Utility utility = new Utility.Builder()
                .setAdmin(admin)
                .setNombre(nombre)
                .setPeso(Double.parseDouble(peso))
                .setCantidad(Integer.parseInt(cantidad))
                .setPhotoUrl("")
                .build();

        if (image != null) {
            Uploader.UploaderResponse resUploader = Uploader.uploadImage(image);
            if (!resUploader.isSuccess()) {
                Messages.show(resUploader.getMessage());
                return false;
            }
            utility.setPhotoUrl(resUploader.getUrl());
        }

        Response<Utility> response = CRUDUtilidad.getInstance().create(utility);
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return false;
        }
        return true;

    }
}
