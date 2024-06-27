package com.uni.thanosgym.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;

import com.uni.thanosgym.components.ButtonComponent;
import com.uni.thanosgym.components.Typography;
import com.uni.thanosgym.controller.PlanController;

public class PanelPlan extends BasePanelForMainWindow {
    public static JPanel panelPlanes;

    @Override
    protected Component[] build() {
        Typography title = new Typography.Builder()
                .text("Planes")
                .type(Typography.Type.HEADING)
                .position(0, 0)
                .width(contentWidth)
                .build();

        panelPlanes = new JPanel();
        panelPlanes.setBackground(Color.WHITE);
        panelPlanes.setBounds(0, 80, 300, 800);

        ButtonComponent button = new ButtonComponent.Builder()
                .text("Agregar plan")
                .position(contentWidth / 2, 0)
                .width(contentWidth)
                .type(ButtonComponent.Type.PRIMARY)
                .onClick(() -> {
                    PlanController.showAddPlan();
                })
                .build();

        updateUIPlan();

        return new Component[] {
                title,
                panelPlanes,
                button
        };
    }

    public static void updateUIPlan() {
        panelPlanes.removeAll();
        panelPlanes.revalidate();
        panelPlanes.repaint();
        PlanController.createPanelList(PlanController.getListaPlanes(), panelPlanes);
    }
}
