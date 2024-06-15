package com.uni.thanosgym.controller;

import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.view.MainWindow;
import com.uni.thanosgym.view.PanelUtility;

public class ControladorUtilidad {
    public static PanelUtility panel;
    public static boolean panelRendered;

    public static void showPanel() {
        MainWindow vista = ControladorMainWindow.getMainWindow();
        PanelUtility panel = ControladorUtilidad.getPanel();
        FrameUtils.showPanel(vista, panel);
    }

    public static PanelUtility getPanel() {
        if (panel == null) {
            panel = new PanelUtility();
        }
        return panel;
    }
}
