package com.uni.thanosgym.view.auth;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import com.uni.thanosgym.components.ButtonComponent;
import com.uni.thanosgym.components.Typography;
import com.uni.thanosgym.components.InputComponent;

public class LoginPanel extends BasePanelForSessionWindow {

    @Override
    protected List<Component> build() {
        List<Component> components = new ArrayList<>();

        Typography title = new Typography("ThanosGym", Typography.Type.HEADING, 0, 0, contentWidth);
        components.add(title);

        InputComponent username = new InputComponent("Username", 0, 200, contentWidth, InputComponent.Type.TEXT);
        username.insertComponent(components);

        InputComponent password = new InputComponent("Password", 0, 300, contentWidth, InputComponent.Type.PASSWORD);
        password.insertComponent(components);

        ButtonComponent button = new ButtonComponent("Login", 0, 400, contentWidth, ButtonComponent.Type.PRIMARY);
        button.onClick(() -> {
            System.out.println("hola mundo");
        });
        components.add(button);

        return components;
    }

}
