package com.uni.thanosgym.view.auth;

import java.awt.Color;
import java.awt.Component;

import com.uni.thanosgym.components.ButtonComponent;
import com.uni.thanosgym.components.CheckBoxComponent;
import com.uni.thanosgym.components.Typography;
import com.uni.thanosgym.components.InputComponent;
import com.uni.thanosgym.controller.SessionController;

public class LoginPanel extends BasePanelForSessionWindow {

    @Override
    protected Component[] build() {

        Typography title = new Typography.Builder()
                .text("ThanosGym")
                .type(Typography.Type.HEADING)
                .position(0, 0)
                .width(contentWidth)
                .build();

        InputComponent username = new InputComponent.Builder()
                .label("Nombre de usuario")
                .position(0, 200)
                .width(contentWidth)
                .type(InputComponent.Type.TEXT)
                .build();

        InputComponent password = new InputComponent.Builder()
                .label("Contraseña")
                .position(0, 275)
                .width(contentWidth)
                .type(InputComponent.Type.PASSWORD)
                .build();

        CheckBoxComponent checkbox = new CheckBoxComponent.Builder()
                .text("Mantener sesión iniciada")
                .position(0, 350)
                .width(contentWidth)
                .build();

        ButtonComponent button = new ButtonComponent.Builder()
                .text("Ingresar")
                .position(0, 380)
                .width(contentWidth)
                .type(ButtonComponent.Type.PRIMARY)
                .onClick(() -> {
                    SessionController.iniciarSesion(username.getInput(), password.getPasswordInput(), checkbox);
                })
                .build();

        ButtonComponent buttonRegistrar = new ButtonComponent.Builder()
                .text("No tengo una cuenta")
                .position(0, 440)
                .width(contentWidth)
                .type(ButtonComponent.Type.SMALL)
                .onClick(() -> {
                    VentanaSession.registerPanel.showPanel();
                })
                .build();
        buttonRegistrar.setForeground(new Color(1, 71, 171));

        password.addOnEnterToPassword(() -> {
            SessionController.iniciarSesion(username.getInput(), password.getPasswordInput(), checkbox);
        });

        return new Component[] {
                title,
                username,
                password,
                checkbox,
                button,
                buttonRegistrar
        };
    }

}
