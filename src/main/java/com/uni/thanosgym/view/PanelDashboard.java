package com.uni.thanosgym.view;

import java.awt.Color;
import java.awt.Component;

import com.uni.thanosgym.components.lib.RoundedPanel;
import com.uni.thanosgym.utils.FrameUtils;

import javax.swing.JPanel;
import com.uni.thanosgym.components.Typography;

public class PanelDashboard extends BasePanelForMainWindow {

    @Override
    protected Component[] build() {

        Typography title = new Typography.Builder()
                .text("Dashboard")
                .type(Typography.Type.HEADING)
                .position(0, 0)
                .width(contentWidth)
                .build();

        JPanel imagePanel = new RoundedPanel(40);
        imagePanel.setBackground(Color.BLACK);
        imagePanel.setSize(contentWidth, 100);
        imagePanel.setLocation(0, 40);
        String imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdZ5gyy-J7yWrlQ4YKKogzhccLA2KQg-Gatg&s";
        FrameUtils.renderImageFromWeb(imageUrl, imagePanel, 10);

        return new Component[] {
            title,
            imagePanel
        };
    }

}
