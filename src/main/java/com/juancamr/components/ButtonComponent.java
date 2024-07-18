package com.juancamr.components;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import com.uni.thanosgym.config.Theme;

public class ButtonComponent extends JButton {

    private Color backgroundColor = Theme.colors.primary;
    private Color foreground = Color.WHITE;
    private int weight = Font.BOLD;
    private int radius = 10;
    private int fontSize = 16;

    public static enum Type {
        PRIMARY, SECONDARY, SUCCESS, DANGER, WARNING, INFO, LIGHT, DARK, SMALL, MENU
    }

    public ButtonComponent() {
        super();
        initializeButton();
    }

    private void initializeButton() {
        setBackground(backgroundColor);
        setForeground(foreground);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFont(Theme.getMainFont(weight, fontSize));
    }

    public void setType(Type type) {
        switch (type) {
            case PRIMARY:
                backgroundColor = Theme.colors.primary;
                foreground = Color.white;
                fontSize = 20;
                break;
            case SMALL:
                backgroundColor = Theme.colors.grayCenizo;
                foreground = Color.black;
                weight = Font.PLAIN;
                fontSize = 12;
                break;
            default:
                break;
        }
        initializeButton();
        revalidate();
        repaint();
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
