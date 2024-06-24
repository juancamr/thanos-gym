package com.uni.thanosgym.view.auth;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.uni.thanosgym.utils.FrameUtils;

public class RegisterPanel {

    public static JPanel registerPanel;

    private void build() {
        int width = 760;
        int height = 690;
        registerPanel.setLayout(null);
        registerPanel.setBackground(Color.WHITE);
        registerPanel.setSize(width / 2, height);
        registerPanel.setLocation(0, 0);

        JLabel label = new JLabel("Register");
        label.setBounds(100, 100, 200, 40);

        registerPanel.add(label);
    }

    public static void showRegisterPanel() {
        if (registerPanel == null) {
            registerPanel = new JPanel();
            new RegisterPanel().build();
        }
        JFrame window = VentanaSesion.getWindow();
        FrameUtils.changePanel(VentanaSesion.content, registerPanel);
        window.setVisible(true);
    }

}
