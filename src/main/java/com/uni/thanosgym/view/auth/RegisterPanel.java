package com.uni.thanosgym.view.auth;

import java.awt.Color;

import javax.swing.JLabel;

public class RegisterPanel extends BasePanelForSessionWindow {

    @Override
    protected void build() {
        int width = 760;
        int height = 690;
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setSize(width / 2, height);
        panel.setLocation(0, 0);

        JLabel label = new JLabel("Register");
        label.setBounds(100, 100, 200, 40);

        panel.add(label);
    }

}
