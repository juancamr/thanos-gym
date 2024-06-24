package com.uni.thanosgym.view.auth;


import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

public class RegisterPanel extends BasePanelForSessionWindow {

    @Override
    protected List<Component> build() {
        List<Component> components = new ArrayList<>();

        JLabel label = new JLabel("Register");
        label.setBounds(100, 100, 200, 40);
        components.add(label);

        return components;
    }

}
