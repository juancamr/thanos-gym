package com.uni.thanosgym.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.uni.thanosgym.components.ButtonComponent;
import com.uni.thanosgym.components.Typography;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.UserPreferences;

public class VentanaPrincipal {

    public static JFrame window;
    public static JPanel content;

    private void build() {
        FrameUtils.setupWindow(window, 1060, 690);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int dashboardWidth = 1060 - 840;

        content = new JPanel();
        content.setLayout(null);
        content.setBackground(Color.WHITE);
        content.setSize(840, 690);
        content.setLocation(1060 - 840, 0);

        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(250, 250, 250));
        sidebar.setLayout(null);
        sidebar.setSize(dashboardWidth, 690);
        sidebar.setLocation(0, 0);

        JPanel sidebarContent = new JPanel();
        int margin = 40;
        sidebarContent.setBackground(new Color(250, 250, 250));
        sidebarContent.setLayout(null);
        sidebarContent.setLocation(margin, margin);
        dashboardWidth -= margin * 2;
        sidebarContent.setSize(dashboardWidth, 690 - margin * 2);

        Typography title = new Typography.Builder()
                .text("Menu")
                .type(Typography.Type.HEADING)
                .position(0, 0)
                .width(dashboardWidth)
                .build();
        List<ButtonComponent> buttons = new ArrayList<>();

        buttons.add(new ButtonComponent.Builder()
                .text("Primero")
                .type(ButtonComponent.Type.PRIMARY)
                .onClick(() -> {
                    new PanelPlan().showPanel();
                })
                .build());

        buttons.add(new ButtonComponent.Builder()
                .text("Segundo")
                .type(ButtonComponent.Type.PRIMARY)
                .onClick(() -> {
                })
                .build());

        buttons.add(new ButtonComponent.Builder()
                .text("Tercero")
                .type(ButtonComponent.Type.PRIMARY)
                .onClick(() -> {
                    System.out.println("pressing tercero");
                })
                .build());

        buttons.add(new ButtonComponent.Builder()
                .text("Cuarto")
                .type(ButtonComponent.Type.PRIMARY)
                .onClick(() -> {
                    System.out.println("pressing cuarto");
                })
                .build());

        for (int i = 0; i < buttons.size(); ++i) {
            buttons.get(i).setPosition(0, 60 * (i + 2), dashboardWidth);
            sidebarContent.add(buttons.get(i));
        }

        Typography adminName = new Typography.Builder()
                .text(UserPreferences.getData().getFullName())
                .type(Typography.Type.SMALL)
                .position(0, 690 - margin * 2 - 20)
                .width(dashboardWidth)
                .build();

        ButtonComponent myButton = new ButtonComponent.Builder()
                .text("ThanosGym")
                .type(ButtonComponent.Type.PRIMARY)
                .position(0, 0)
                .width(dashboardWidth)
                .build();
        myButton.setBorderPainted(false);
        myButton.setText("Logout");
        myButton.setBounds(0, 690 - margin * 3 - 40, dashboardWidth, 40);
        sidebarContent.add(myButton);

        sidebarContent.add(title);
        sidebarContent.add(adminName);

        sidebar.add(sidebarContent);
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
