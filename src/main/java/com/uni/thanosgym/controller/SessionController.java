package com.uni.thanosgym.controller;

import com.uni.thanosgym.utils.Auth;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.utils.Uploader;

import java.awt.Color;

import javax.swing.*;

import com.uni.thanosgym.components.CheckBoxComponent;
import com.uni.thanosgym.components.ChooserComponent;
import com.uni.thanosgym.components.InputComponent;
import com.uni.thanosgym.dao.CRUDAdministrador;
import com.uni.thanosgym.model.Admin;
import com.uni.thanosgym.model.Response;

public class SessionController {

    public static void iniciarSesion(JTextField usernameInput, JPasswordField passwordInput,
            CheckBoxComponent checkbox) {
        String userName = usernameInput.getText();
        String password = String.valueOf(passwordInput.getPassword());
        if (userName.isEmpty() || password.isEmpty()) {
            Messages.show("Complete todos los campos");
            return;
        }
        password = StringUtils.sha256(password);
        Response<Admin> response = CRUDAdministrador.getInstance().verify(userName, password, false);
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return;
        }
        FrameUtils.clearInputs(new JTextField[] { usernameInput, passwordInput });
        Admin administrador = response.getData();
        administrador.setPersistencia(checkbox.isSelected());
        Auth.signIn(administrador);
    }

    public static void registrar(JTextField nombres, JTextField userName, JPasswordField passwordInput,
            JPasswordField repeatedPasswordInput, JTextField emailInput, JTextField phoneInput,
            CheckBoxComponent checkbox,
            ChooserComponent chooser) {
        JTextField[] fields = new JTextField[] { nombres, userName, passwordInput, repeatedPasswordInput, emailInput,
                phoneInput };
        String names = nombres.getText();
        String username = userName.getText();
        String password = String.valueOf(passwordInput.getPassword());
        String repeatedPassword = String.valueOf(passwordInput.getPassword());
        String email = emailInput.getText();
        String phone = phoneInput.getText();

        if (username.isEmpty() || password.isEmpty() || names.isEmpty() || email.isEmpty()) {
            Messages.show("Complete todos los campos");
            return;
        }
        if (!username.matches(StringUtils.usernameRegex)) {
            Messages.show("Ingrese un nombre de usuario valido");
            return;
        }
        if (!password.matches(StringUtils.passwordRegex)) {
            Messages.show("Contraseña invalida, recuerda que debe ser almenos de 8 caracteres");
            return;
        }
        if (!phone.isEmpty() && !StringUtils.isValidPhone(phone)) {
            Messages.show("El telefono debe ser un numero de 9 digitos.");
            return;
        }
        if (!password.equals(repeatedPassword)) {
            Messages.show("Las contraseñas no coinciden");
            return;
        }
        if (!StringUtils.isValidEmail(email)) {
            Messages.show("Ingrese un correo valido");
            return;
        }

        password = StringUtils.sha256(password);
        boolean isForMaster = checkbox.isSelected();

        Admin admin = new Admin.Builder()
                .setFullName(names)
                .setEmail(email)
                .setPhone(phone)
                .setUsername(username)
                .setPassword(password)
                .setPhotoUrl("")
                .setRol(isForMaster ? Admin.Rol.MASTER : Admin.Rol.EMPLEADO)
                .build();
        if (!phone.isEmpty()) {
            admin.setPhone(phone);
        }
        if (CRUDAdministrador.getInstance().getQuantity() == 0) {
            registrar2(admin, new Admin.Builder().build(), chooser, fields);
            return;
        }

        // ask for admin master
        int width = 400;
        int height = 400;
        JFrame frame = new JFrame();
        FrameUtils.setupWindow(frame, width, height);
        JPanel panel = new JPanel();
        panel.setLocation(0, 0);
        panel.setBackground(Color.WHITE);
        panel.setSize(width, height);
        panel.setLayout(null);

        InputComponent usernameInputMaster = new InputComponent.Builder()
                .label("Username")
                .position(20, 20)
                .width(360)
                .type(InputComponent.Type.TEXT)
                .build();

        InputComponent passwordInputMaster = new InputComponent.Builder()
                .label("Password")
                .position(20, 120)
                .width(360)
                .type(InputComponent.Type.PASSWORD)
                .build();
        passwordInputMaster.addOnEnterToPassword(() -> {
            String usernameTextMaster = usernameInputMaster.getInput().getText();
            String passwordTextMaster = passwordInputMaster.getContent();

            if (usernameTextMaster.isEmpty() || passwordTextMaster.isEmpty()) {
                Messages.show("Complete todos los campos");
                return;
            }
            Admin adminMaster = new Admin.Builder()
                    .setUsername(usernameTextMaster)
                    .setPassword(StringUtils.sha256(passwordTextMaster))
                    .build();
            registrar2(admin, adminMaster, chooser, fields);
        });

        panel.add(usernameInputMaster);
        panel.add(passwordInputMaster);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void registrar2(Admin admin, Admin adminMaster, ChooserComponent chooser, JTextField fields[]) {
        if (chooser.getSelected() != null) {
            Uploader.UploaderResponse resUploader = Uploader.uploadImage(chooser.getSelected());
            if (!resUploader.isSuccess()) {
                Messages.show(resUploader.getMessage());
                // imageSelected = null;
                return;
            }
            admin.setPhotoUrl(resUploader.getUrl());
            // imageSelected = null;
        }
        Response<Admin> response = CRUDAdministrador.getInstance()
                .create(admin, adminMaster);
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return;
        }
        Admin adminForSignin = response.getData();
        adminForSignin.setId(response.getId());
        Auth.signIn(adminForSignin);
        FrameUtils.clearInputs(fields);
    }

}
