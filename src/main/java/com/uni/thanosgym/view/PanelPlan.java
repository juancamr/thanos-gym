package com.uni.thanosgym.view;

import java.awt.Color;
import com.uni.thanosgym.components.InputComponent;
import com.uni.thanosgym.components.ButtonComponent;

public class PanelPlan extends BasePanelForMainWindow {

    @Override
	protected void build() {
        int width = 840;
        int height = 690;
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setSize(width, height);
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
