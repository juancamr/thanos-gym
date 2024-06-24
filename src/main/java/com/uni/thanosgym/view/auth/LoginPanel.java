package com.uni.thanosgym.view.auth;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import com.uni.thanosgym.components.Typography;
import com.uni.thanosgym.components.InputComponent;

public class LoginPanel extends BasePanelForSessionWindow {

    @Override
    protected List<Component> build() {
        List<Component> components = new ArrayList<>();

        components.add(new Typography.Builder()
                .setText("Inicia sesion")
                .setType(Typography.Type.HEADING)
                .setPosition(0, 0)
                .setWidth(contentWidth)
                .build());

        InputComponent username = new InputComponent("Username", 0, 200, contentWidth, InputComponent.Type.TEXT);
        username.insert(components);

        InputComponent password = new InputComponent("Password", 0, 300, contentWidth, InputComponent.Type.PASSWORD);
        password.insert(components);

        return components;
    }

}
