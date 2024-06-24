package com.uni.thanosgym.view.auth;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.uni.thanosgym.components.lib.RoundedPanel;
import com.uni.thanosgym.utils.FrameUtils;

public class VentanaSession {

    public static JPanel content;
    public static JFrame window;

    private void build() {
        int width = 760;
        int height = 690;
        FrameUtils.setupWindow(window, width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        content = new JPanel();
        content.setLayout(null);
        content.setSize(width / 2, height);
        content.setLocation(0, 0);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setSize(width / 2, height);
        rightPanel.setLocation(width / 2, 0);

        JPanel imagePanel = new RoundedPanel(40);
        imagePanel.setSize(width / 2 - 40, height - 40);
        imagePanel.setLocation(20, 20);
        String imageUrl = "https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3JtNjg1ZGVzaWduLXRuLXJlbWl4LTAwMWMuanBn.jpg";
        FrameUtils.renderImageFromWeb(imageUrl, imagePanel, 10);

        rightPanel.add(imagePanel);

        window.add(content);
        window.add(rightPanel);
    }

    public static JFrame getWindow() {
        if (window == null) {
            window = new JFrame();
            new VentanaSession().build();
        }
        return window;
    }

}
