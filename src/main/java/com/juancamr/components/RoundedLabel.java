package com.juancamr.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedLabel extends JLabel {

    private int borderRadius = 10;

    public RoundedLabel() {
        super();
    }

    public void setIcon(Icon icon) {
        super.setIcon(icon);
    }

    public void setBorderRadius(int radius) {
        this.borderRadius = radius;
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int arc = borderRadius * 2;

        // Create a rounded rectangle clip area
        Shape clip = new RoundRectangle2D.Float(0, 0, width, height, arc, arc);
        g2.setClip(clip);

        super.paintComponent(g2);
        g2.dispose();
    }
}
