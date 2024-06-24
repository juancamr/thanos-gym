package com.uni.thanosgym.view;

import javax.swing.JPanel;
import javax.swing.JFrame;
import com.uni.thanosgym.utils.FrameUtils;

public abstract class BasePanelForMainWindow {
    protected static JPanel panel;

    protected abstract void build();

    public void showPanel() {
        if (panel == null) {
            panel = new JPanel();
            build();
        }
        JFrame window = VentanaPrincipal.getWindow();
        FrameUtils.changePanel(VentanaPrincipal.content, panel);
        window.setVisible(true);
    }
}

