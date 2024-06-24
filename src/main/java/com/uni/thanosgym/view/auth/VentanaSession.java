package com.uni.thanosgym.view.auth;

import javax.swing.JFrame;
import javax.swing.JPanel;
import com.uni.thanosgym.utils.FrameUtils;

public class VentanaSession {

    public static JPanel content;
    public static JFrame window;

    private void build() {
        int width = 760;
        int height = 690;
        FrameUtils.setupWindow(window, width, height);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel imagePanel = new JPanel();
        imagePanel.setSize(width/2, height + 20);
        imagePanel.setLocation(width/2, -10);

        content = new JPanel();
        content.setLayout(null);
        content.setSize(width/2, height);
        content.setLocation(0, 0);

        FrameUtils.renderImageFromWeb(
                "https://images.rawpixel.com/image_800/cHJpdmF0ZS9sci9pbWFnZXMvd2Vic2l0ZS8yMDIzLTA4L3JtNjg1ZGVzaWduLXRuLXJlbWl4LTAwMWMuanBn.jpg",
                imagePanel);

        window.add(content);
        window.add(imagePanel);
    }

    public static JFrame getWindow() {
        if (window == null) {
            window = new JFrame();
            new VentanaSession().build();
        }
        return window;
    }

}
