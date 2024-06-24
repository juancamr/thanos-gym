package com.uni.thanosgym.view.auth;

import java.awt.Color;

import javax.swing.JPanel;

import com.uni.thanosgym.components.ButtonComponent;
import com.uni.thanosgym.components.InputComponent;

public class LoginPanel extends BasePanelForSessionWindow {

    public static JPanel loginPanel;

    @Override
    protected void build() {
        int width = 760;
        int height = 690;
        loginPanel.setLayout(null);
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setSize(width/2, height);
        loginPanel.setLocation(0, 0);

        InputComponent usernameInput = new InputComponent("Username", 20, 100, InputComponent.InputType.TEXT);
        usernameInput.addToPanel(loginPanel);

        InputComponent passwordInput = new InputComponent("Password", 20, 180, InputComponent.InputType.PASSWORD);
        passwordInput.addToPanel(loginPanel);

        ButtonComponent button = new ButtonComponent("Press me", 20, 300, 200, 40);
        button.addOnClickEvent(() -> {
            System.out.println(usernameInput.getInputContent());
        });

        loginPanel.add(button);
    }

}
