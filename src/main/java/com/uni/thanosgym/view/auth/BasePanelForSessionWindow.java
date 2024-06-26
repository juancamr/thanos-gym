package com.uni.thanosgym.view.auth;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;

import com.uni.thanosgym.utils.FrameUtils;

public abstract class BasePanelForSessionWindow {
    protected JPanel panel;
    public static int windowWidth = 760;
    public static int windowHeight = 690;
    public static int margin = 40;
    public static int contentWidth = windowWidth / 2 - margin * 2;

    protected abstract Component[] build();

    public void showPanel() {
        if (panel == null) {
            System.out.println("it was null");
            panel = new JPanel();
            panel.setLayout(null);
            panel.setBackground(Color.WHITE);
            panel.setSize(windowWidth / 2 - margin * 2, windowHeight-margin * 2);
            panel.setLocation(margin, margin);
            Component[] components = build();
            for (Component component : components) {
                panel.add(component);
            }
        }
        JFrame window = VentanaSession.getWindow();
        FrameUtils.changePanel(VentanaSession.content, panel);
        window.setVisible(true);
    }

}
