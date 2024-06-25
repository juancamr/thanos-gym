package com.uni.thanosgym.view.auth;

import com.uni.thanosgym.components.ButtonComponent;
import com.uni.thanosgym.components.ChooserComponent;
import com.uni.thanosgym.components.Typography;
import com.uni.thanosgym.components.InputComponent;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public class RegisterPanel extends BasePanelForSessionWindow {

    @Override
    protected List<Component> build() {
        List<Component> components = new ArrayList<>();
        int[] pos = {50, 125, 200, 275, 350, 425, 500, 550};

        Typography title = new Typography("Registrate", Typography.Type.HEADING, 0, 0, contentWidth);
        components.add(title);

        InputComponent names = new InputComponent("Nombres completos", 0, pos[0], contentWidth, InputComponent.Type.TEXT);
        names.insertComponent(components);

        InputComponent email = new InputComponent("Correo", 0, pos[1], contentWidth, InputComponent.Type.TEXT);
        email.insertComponent(components);

        InputComponent phone = new InputComponent("Telefono", 0, pos[2], contentWidth, InputComponent.Type.TEXT);
        phone.insertComponent(components);

        InputComponent username = new InputComponent("Username", 0, pos[3], contentWidth, InputComponent.Type.TEXT);
        username.insertComponent(components);

        InputComponent password = new InputComponent("Contraseña", 0, pos[4], contentWidth, InputComponent.Type.PASSWORD);
        password.insertComponent(components);

        InputComponent repeatedPassword = new InputComponent("Repetir la Contrseña", 0, pos[5], contentWidth, InputComponent.Type.PASSWORD);
        repeatedPassword.insertComponent(components);

        ChooserComponent chooser = new ChooserComponent(contentWidth, 0, pos[6]);
        components.add(chooser);

        ButtonComponent button = new ButtonComponent("Registrarme", 0, pos[7], contentWidth, ButtonComponent.Type.PRIMARY);
        button.onClick(() -> {
            new LoginPanel().showPanel();
        }); 
        components.add(button);

        return components;
    }

}
