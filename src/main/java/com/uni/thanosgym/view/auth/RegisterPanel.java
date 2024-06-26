package com.uni.thanosgym.view.auth;

import com.uni.thanosgym.components.ButtonComponent;
import com.uni.thanosgym.components.CheckBoxComponent;
import com.uni.thanosgym.components.ChooserComponent;
import com.uni.thanosgym.components.Typography;
import com.uni.thanosgym.controller.SessionController;
import com.uni.thanosgym.components.InputComponent;

import java.awt.Component;

public class RegisterPanel extends BasePanelForSessionWindow {
    @Override
    protected Component[] build() {
        int[] pos = { 50, 125, 200, 275, 350, 425, 500, 550, 580 };

        Typography title = new Typography.Builder()
                .text("Registrate")
                .type(Typography.Type.HEADING)
                .position(0, 0)
                .width(contentWidth)
                .build();

        InputComponent names = new InputComponent.Builder()
                .label("Nombres completos")
                .position(0, pos[0])
                .width(contentWidth)
                .type(InputComponent.Type.TEXT)
                .build();

        InputComponent email = new InputComponent.Builder()
                .label("Correo")
                .position(0, pos[1])
                .width(contentWidth)
                .type(InputComponent.Type.TEXT)
                .build();

        InputComponent phone = new InputComponent.Builder()
                .label("Telefono")
                .position(0, pos[2])
                .width(contentWidth)
                .type(InputComponent.Type.TEXT)
                .build();

        InputComponent username = new InputComponent.Builder()
                .label("Nombre de usuario")
                .position(0, pos[3])
                .width(contentWidth)
                .type(InputComponent.Type.TEXT)
                .build();

        InputComponent password = new InputComponent.Builder()
                .label("Contraseña")
                .position(0, pos[4])
                .width(contentWidth)
                .type(InputComponent.Type.PASSWORD)
                .build();

        InputComponent repeatedPassword = new InputComponent.Builder()
                .label("Repetir contraseña")
                .position(0, pos[5])
                .width(contentWidth)
                .type(InputComponent.Type.PASSWORD)
                .build();

        ChooserComponent chooser = new ChooserComponent.Builder()
                .width(contentWidth)
                .position(0, pos[6])
                .build();

        CheckBoxComponent checkbox = new CheckBoxComponent.Builder()
                .text("Para ser administrador master")
                .position(0, pos[7])
                .width(contentWidth)
                .build();

        ButtonComponent button = new ButtonComponent.Builder()
                .text("Registrarme")
                .position(0, pos[8])
                .width(contentWidth)
                .type(ButtonComponent.Type.PRIMARY)
                .onClick(() -> {
                    SessionController.registrar(names.getInput(), username.getInput(), password.getPasswordInput(),
                            repeatedPassword.getPasswordInput(), email.getInput(), phone.getInput(), checkbox,
                            chooser);
                })
                .build();

        return new Component[] {
                title,
                names,
                email,
                phone,
                username, password,
                repeatedPassword,
                checkbox,
                chooser,
                button
        };
    }

}
