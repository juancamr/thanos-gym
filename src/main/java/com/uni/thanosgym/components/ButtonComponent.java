package com.uni.thanosgym.components;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import com.uni.thanosgym.common.Theme;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.view.VentanaPrincipal;

public class ButtonComponent extends JButton {

    private Color backgroundColor;
    Color foreground = Color.WHITE;
    private int radius = 10;
    int fontSize = 16;
    int fontWeight = Font.BOLD;
    int height = 40;

    public static enum Type {
        PRIMARY, SECONDARY, SUCCESS, DANGER, WARNING, INFO, LIGHT, DARK, SMALL, MENU
    }

    public ButtonComponent(Builder builder) {
        setText(builder.text);
        setDataFromType(builder.type);
        setBackground(backgroundColor);
        setForeground(foreground);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFont(Theme.getMainFont(fontWeight, fontSize));
        setBounds(builder.x, builder.y, builder.width, height);
        setFocusable(false);
        FrameUtils.addOnClickEvent(this, builder.function);
    }

    public void setPosition(int x, int y, int width) {
        setBounds(x, y, width, height);
    }

    public void addOnClickEvent(Runnable function) {
        FrameUtils.addOnClickEvent(this, function);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //  Paint Border
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        //  Border set 2 Pix
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
        super.paintComponent(grphcs);
    }

    public void setDataFromType(Type type) {
        switch (type) {
            case PRIMARY:
                backgroundColor = new Color(229, 190, 1);
                foreground = Color.BLACK;
                break;
            case SECONDARY:
                backgroundColor = new Color(108, 117, 125);
                foreground = Color.WHITE;
                break;
            case DANGER:
                backgroundColor = new Color(220, 53, 69);
                foreground = Color.WHITE;
                break;
            case SMALL:
                backgroundColor = Color.WHITE;
                foreground = Color.BLACK;
                fontWeight = Font.PLAIN;
                fontSize = 12;
                height = 30;
                break;
            case MENU:
                backgroundColor = VentanaPrincipal.sidebarBackground;
                foreground = Color.BLACK;
                this.setHorizontalAlignment(SwingConstants.LEFT);
                fontWeight = Font.PLAIN;
                break;
            default:
                break;
        }
    }


    public static class Builder {
        private String text;
        private int x;
        private int y;
        private int width;
        private Type type;
        private Runnable function;

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder position(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder type(Type type) {
            this.type = type;
            return this;
        }

        public Builder onClick(Runnable function) {
            this.function = function;
            return this;
        }

        public ButtonComponent build() {
            return new ButtonComponent(this);
        }
    }
}
