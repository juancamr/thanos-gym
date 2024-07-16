package com.uni.thanosgym.controllers;

import java.util.Map;

import java.io.File;

import com.juancamr.route.RoutingUtils;
import com.uni.thanosgym.dao.CRUDAdministrador;
import com.uni.thanosgym.model.Admin;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Auth;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.utils.Uploader;
import com.uni.thanosgym.view.dialogs.VerificacionMaster;

public class AuthController {

    public static boolean login(String username, String password, boolean persistencia) {
        if (username.isEmpty() || password.isEmpty()) {
            Messages.show("Complete todos los campos");
            return false;
        }
        password = StringUtils.sha256(password);
        Response<Admin> response = CRUDAdministrador.getInstance().verify(username, password, false);
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return false;
        }
        Admin administrador = response.getData();
        administrador.setPersistencia(persistencia);
        Auth.signIn(administrador);
        return true;
    }

    public static boolean register(Map<String, Object> params) {
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        String repeatedPassword = (String) params.get("repeatPassword");
        String nombres = (String) params.get("fullname");
        String email = (String) params.get("email");
        String phone = (String) params.get("phone");
        boolean isForMaster = (boolean) params.get("isForMaster");
        File imageSelected = (File) params.get("image");

        if (username.isEmpty() || password.isEmpty() || nombres.isEmpty() || email.isEmpty()) {
            Messages.show("Complete todos los campos");
            return false;
        }
        if (!username.matches(StringUtils.usernameRegex)) {
            Messages.show("Ingrese un nombre de usuario valido");
            return false;
        }
        if (!password.matches(StringUtils.passwordRegex)) {
            Messages.show("Contraseña invalida, recuerda que debe ser almenos de 8 caracteres");
            return false;
        }
        if (!phone.isEmpty() && !StringUtils.isValidPhone(phone)) {
            Messages.show("El telefono debe ser un numero de 9 digitos.");
            return false;
        }
        if (!password.equals(repeatedPassword)) {
            Messages.show("Las contraseñas no coinciden");
            return false;
        }
        if (!StringUtils.isValidEmail(email)) {
            Messages.show("Ingrese un correo valido");
            return false;
        }

        Admin admin = new Admin.Builder()
                .setFullName(nombres)
                .setEmail(email)
                .setPhone(phone)
                .setUsername(username)
                .setPassword(StringUtils.sha256(password))
                .setRol(isForMaster ? Admin.Rol.MASTER : Admin.Rol.EMPLEADO)
                .build();
        if (!phone.isEmpty()) {
            admin.setPhone(phone);
        }
        if (imageSelected != null) {
            Uploader.UploaderResponse resUploader = Uploader.uploadImage(imageSelected);
            if (!resUploader.isSuccess()) {
                Messages.show(resUploader.getMessage());
                imageSelected = null;
                return false;
            }
            admin.setPhotoUrl(resUploader.getUrl());
            imageSelected = null;
        }
        Admin adminMaster = null;
        if (CRUDAdministrador.getInstance().getQuantity() != 0) {
            Map<String, Object> respuesta = RoutingUtils.openDialog(new VerificacionMaster());
            if (respuesta == null) {
                Messages.show("Es necesario que ingrese el usuario y contraseña del administrador master");
                return false;
            }
            String usernameMaster = (String) respuesta.get("username");
            String passwordMaster = (String) respuesta.get("password");
            if (usernameMaster.isEmpty() || passwordMaster.isEmpty()) {
                Messages.show("Es necesario que ingrese el usuario y contraseña del administrador master");
                return false;
            }
            adminMaster = new Admin.Builder()
                    .setUsername(usernameMaster)
                    .setPassword(StringUtils.sha256(passwordMaster))
                    .build();
        }
        Response<Admin> response = CRUDAdministrador.getInstance()
                .create(admin, adminMaster);
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return false;
        }

        Admin adminForSignin = response.getData();
        adminForSignin.setId(response.getId());
        Auth.signIn(adminForSignin);
        return true;
    }
}
