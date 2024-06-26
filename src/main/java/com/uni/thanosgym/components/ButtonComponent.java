package com.uni.thanosgym.components;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import com.uni.thanosgym.common.Theme;
import com.uni.thanosgym.utils.FrameUtils;

public class ButtonComponent extends JButton {

    private Color backgroundColor;
    Color foreground = Color.WHITE;
    private int radius = 10;
    int fontSize = 16;
    int height = 40;

    public static enum Type {
        PRIMARY, SECONDARY, SUCCESS, DANGER, WARNING, INFO, LIGHT, DARK, SMALL
    }

    public ButtonComponent(Builder builder) {
        setText(builder.text);
        setDataFromType(builder.type);
        setBackground(backgroundColor);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFont(Theme.getMainFont(Font.BOLD, fontSize));
        setBounds(builder.x, builder.y, builder.width, height);
        FrameUtils.addOnClickEvent(this, builder.function);
    }

    public void setPosition(int x, int y, int width) {
        setBounds(x, y, width, height);
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
        if (type == Type.PRIMARY) {
            backgroundColor = new Color(0, 123, 255);
            foreground = Color.WHITE;
        } else if (type == Type.SECONDARY) {
            backgroundColor = new Color(108, 117, 125);
            foreground = Color.WHITE;
        } else if (type == Type.DANGER) {
            backgroundColor = new Color(220, 53, 69);
            foreground = Color.WHITE;
        } else if (type == Type.SMALL) {
            backgroundColor = Color.WHITE;
            foreground = Color.BLACK;
            fontSize = 12;
            height = 30;
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
