package com.uni.thanosgym.view.auth;

import javax.swing.JPanel;
import javax.swing.JFrame;
import com.uni.thanosgym.utils.FrameUtils;

public class BasePanelForSessionWindow {
    public static JPanel panel;

    protected void build() {}

    public static void showPanel() {
        if (panel == null) {
            panel = new JPanel();
            new BasePanelForSessionWindow().build();
        }
        JFrame window = VentanaSession.getWindow();
        FrameUtils.changePanel(VentanaSession.content, panel);
        window.setVisible(true);
    }


}
