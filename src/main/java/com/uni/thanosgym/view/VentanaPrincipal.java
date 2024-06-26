package com.uni.thanosgym.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.uni.thanosgym.components.ButtonComponent;
import com.uni.thanosgym.components.Typography;
import com.uni.thanosgym.utils.Auth;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.view.cliente.PanelCliente;

public class VentanaPrincipal {

    public static JFrame window;
    public static JPanel content;
    public static Color sidebarBackground = new Color(250, 250, 250);
    private static Color selectedBackground = new Color(240, 240, 240);

    public static PanelDashboard panelDashboard = new PanelDashboard();
    public static PanelPlan panelPlan = new PanelPlan();
    public static PanelCliente panelCliente = new PanelCliente();
    public static PanelVenta panelVenta = new PanelVenta();

    private void removeSelected(List<ButtonComponent> buttons) {
        for (ButtonComponent button : buttons) {
            button.setBackground(sidebarBackground);
        }
    }

    private void build() {
        FrameUtils.setupWindow(window, 1060, 690);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int sidebarWidth = 1060 - 840;

        content = new JPanel();
        content.setLayout(null);
        content.setBackground(Color.WHITE);
        content.setSize(840, 690);
        content.setLocation(1060 - 840, 0);

        JPanel sidebar = new JPanel();
        sidebar.setBackground(sidebarBackground);
        sidebar.setLayout(null);
        sidebar.setSize(sidebarWidth, 690);
        sidebar.setLocation(0, 0);

        JPanel sidebarContent = new JPanel();
        int margin = 20;
        sidebarContent.setBackground(sidebarBackground);
        sidebarContent.setLayout(null);
        sidebarContent.setLocation(margin, margin);
        sidebarWidth -= margin * 2;
        int sidebarHeight = 690 - margin * 2;
        sidebarContent.setSize(sidebarWidth, 690 - margin * 2);

        Typography title = new Typography.Builder()
                .text("Menu")
                .type(Typography.Type.HEADING)
                .position(margin, margin)
                .width(sidebarWidth)
                .build();
        List<ButtonComponent> buttons = new ArrayList<>();

        buttons.add(new ButtonComponent.Builder()
                .text("Dashboard")
                .type(ButtonComponent.Type.MENU)
                .onClick(() -> {
                    panelDashboard.showPanel();
                })
                .build());

        buttons.add(new ButtonComponent.Builder()
                .text("Planes")
                .type(ButtonComponent.Type.MENU)
                .onClick(() -> {
                    panelPlan.showPanel();
                })
                .build());

        buttons.add(new ButtonComponent.Builder()
                .text("Clientes")
                .type(ButtonComponent.Type.MENU)
                .onClick(() -> {
                    panelCliente.showPanel();
                })
                .build());

        buttons.add(new ButtonComponent.Builder()
                .text("Ventas")
                .type(ButtonComponent.Type.MENU)
                .onClick(() -> {
                    panelVenta.showPanel();
                })
                .build());

        for (int i = 0; i < buttons.size(); ++i) {
            ButtonComponent button = buttons.get(i);
            button.setPosition(0, 60 * (i + 2), sidebarWidth);

            button.addOnClickEvent(() -> {
                removeSelected(buttons);
                button.setBackground(selectedBackground);
            });

            sidebarContent.add(buttons.get(i));
        }
        buttons.get(0).setBackground(selectedBackground);

        ButtonComponent logout = new ButtonComponent.Builder()
                .text("Logout")
                .type(ButtonComponent.Type.MENU)
                .position(0, sidebarHeight - 50)
                .width(sidebarWidth)
                .onClick(() -> {
                    Auth.logOut();
                })
                .build();
        logout.setForeground(Color.RED);

        sidebarContent.add(title);
        sidebarContent.add(logout);

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
};
