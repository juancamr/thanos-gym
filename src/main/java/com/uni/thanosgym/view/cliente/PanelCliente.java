package com.uni.thanosgym.view;

import javax.swing.JPanel;
import javax.swing.JFrame;
import com.uni.thanosgym.utils.FrameUtils;
import java.awt.Color;
import com.uni.thanosgym.components.InputComponent;
import com.uni.thanosgym.components.ButtonComponent;

public class PanelCliente {
    public static JPanel panel;

    private void build() {
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

    public static void showPanelCliente() {
        if (panel == null) {
            panel = new JPanel();
            new PanelCliente().build();
        }
        JFrame window = VentanaPrincipal.getWindow();
        FrameUtils.changePanel(VentanaPrincipal.content, panel);
        window.setVisible(true);
    }
}
