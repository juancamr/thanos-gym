package com.uni.thanosgym.view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;
import com.uni.thanosgym.utils.FrameUtils;

public abstract class BasePanelForMainWindow {
    protected JPanel panel;
    public static int windowWidth = 1060;
    public static int windowHeight = 690;
    public static int contentWidth = 840;

    protected abstract Component[] build();

    public void showPanel() {
        int margin = 40;
        if (panel == null) {
            panel = new JPanel();
            panel.setLayout(null);
            panel.setBackground(Color.WHITE);
            panel.setSize(contentWidth - margin * 2, windowHeight - margin * 2);
            panel.setLocation(margin, margin);
            Component[] components = build();
            for (Component component : components) {
                panel.add(component);
            }
        }
        JFrame window = VentanaPrincipal.getWindow();
        FrameUtils.changePanel(VentanaPrincipal.content, panel);
        window.setVisible(true);
    }
}
