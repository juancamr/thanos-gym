package com.uni.thanosgym.view.auth;

import javax.swing.JPanel;
import javax.swing.JFrame;
import com.uni.thanosgym.utils.FrameUtils;

public abstract class BasePanelForSessionWindow {
    protected static JPanel panel;

    protected abstract void build();

    public void showPanel() {
        if (panel == null) {
            panel = new JPanel();
            build();
        }
        JFrame window = VentanaSession.getWindow();
        FrameUtils.changePanel(VentanaSession.content, panel);
        window.setVisible(true);
    }


}
