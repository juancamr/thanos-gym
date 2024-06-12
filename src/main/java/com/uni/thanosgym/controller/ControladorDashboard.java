package com.uni.thanosgym.controller;

import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.view.MainWindow;
import com.uni.thanosgym.view.PanelDashboard;

public class ControladorDashboard {

    public static PanelDashboard panel;

    public static void showPanel() {
        MainWindow vista = ControladorMainWindow.getMainWindow();
        PanelDashboard panel = ControladorDashboard.getPanel();
        FrameUtils.showPanel(vista, panel);
    }

    public static PanelDashboard getPanel() {
        if (panel == null) {
            panel = new PanelDashboard();
        }
        return panel;
    }
}

