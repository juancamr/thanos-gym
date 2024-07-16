package com.uni.thanosgym.components;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

import com.uni.thanosgym.config.Theme;

public class ButtonComponent extends JButton {

    private Color backgroundColor = Color.BLACK;
    Color foreground = Color.WHITE;
    private int radius = 10;
    int fontSize = 16;
    int fontWeight = Font.BOLD;
    int height = 40;

    private enum Type {
        PRIMARY, SECONDARY, SUCCESS, DANGER, WARNING, INFO, LIGHT, DARK, SMALL, MENU
    }

    public ButtonComponent() {
        super();
        setBackground(backgroundColor);
        setForeground(foreground);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFont(Theme.getMainFont(fontWeight, fontSize));
    }

    public void setType(Type type) {
        if (type == Type.PRIMARY) {
            backgroundColor = Theme.colors.primary;
            setBackground(backgroundColor);
        }
    }

    public void onClick(Runnable action) {
        addActionListener(e -> action.run());
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Paint Border
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        // Border set 2 Pix
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
        super.paintComponent(grphcs);
    }

}
