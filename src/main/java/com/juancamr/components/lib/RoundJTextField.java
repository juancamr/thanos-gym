package com.juancamr.components.lib;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JTextField;

import com.uni.thanosgym.config.Theme;

public class RoundJTextField extends JTextField {
    private Shape shape;

    public RoundJTextField(int radius) {
        super(radius);
        this.setBackground(Theme.inputColor);
        setOpaque(false);
    }
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
         g.setColor(Theme.inputColor);
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
    }
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
         }
         return shape.contains(x, y);
    }
}
