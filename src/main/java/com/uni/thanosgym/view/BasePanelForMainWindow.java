package com.uni.thanosgym.view;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JFrame;
import com.uni.thanosgym.utils.FrameUtils;

public abstract class BasePanelForMainWindow {
    protected static JPanel panel;
    public static int windowWidth = 1060;
    public static int windowHeight = 690;
    public static int contentWidth = 840;

    protected abstract void build();

    public void showPanel() {
        if (panel == null) {
            panel = new JPanel();
            panel.setLayout(null);
            panel.setBackground(Color.WHITE);
            panel.setSize(contentWidth, windowHeight);
            panel.setLocation(0, 0);
            build();
        }
        JFrame window = VentanaPrincipal.getWindow();
        FrameUtils.changePanel(VentanaPrincipal.content, panel);
        window.setVisible(true);
    }
}
