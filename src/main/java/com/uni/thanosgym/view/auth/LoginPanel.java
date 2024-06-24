package com.uni.thanosgym.view.auth;

import java.awt.Color;

import com.uni.thanosgym.components.ButtonComponent;
import com.uni.thanosgym.components.InputComponent;

public class LoginPanel extends BasePanelForSessionWindow {

    @Override
    protected void build() {
        System.out.println("building...");
        int width = 760;
        int height = 690;
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setSize(width / 2, height);
        panel.setLocation(0, 0);

        InputComponent usernameInput = new InputComponent("Username", 20, 100, InputComponent.InputType.TEXT);
        usernameInput.addToPanel(panel);

        InputComponent passwordInput = new InputComponent("Password", 20, 180, InputComponent.InputType.PASSWORD);
        passwordInput.addToPanel(panel);

        ButtonComponent button = new ButtonComponent("Press me", 20, 300, 200, 40);
        button.addOnClickEvent(() -> {
            System.out.println(usernameInput.getInputContent());
        });

        panel.add(button);
    }

}
