package com.uni.thanosgym.view;

import javax.swing.JPanel;
import javax.swing.JFrame;
import com.uni.thanosgym.utils.FrameUtils;

public class BasePanelForMainWindow {
    public static JPanel panel;

    protected void build() {}

    public static void showPanel() {
        if (panel == null) {
            panel = new JPanel();
            new BasePanelForMainWindow().build();
        }
        JFrame window = VentanaPrincipal.getWindow();
        FrameUtils.changePanel(VentanaPrincipal.content, panel);
        window.setVisible(true);
    }
}

