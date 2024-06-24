package com.uni.thanosgym.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.uni.thanosgym.utils.FrameUtils;

public class VentanaPrincipal {

    public static JFrame window;
    public static JPanel content;

    private void build() {
        FrameUtils.setupWindow(window, 1060, 690);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        content = new JPanel();
        content.setLayout(null);
        content.setSize(840, 690);
        content.setLocation(1060-840, 0);

        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(250, 250, 250));
        sidebar.setLayout(null);
        sidebar.setSize(1060-840, 690);
        sidebar.setLocation(0, 0);

        window.add(sidebar);
        window.add(content);
    }

    public static JFrame getWindow() {
        if (window == null) {
            window = new JFrame();
            new VentanaPrincipal().build();
        }
        return window;
    }
}
