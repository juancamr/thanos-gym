package com.uni.thanosgym.components;

import java.awt.*;

import javax.swing.JButton;

import com.uni.thanosgym.common.Theme;
import com.uni.thanosgym.utils.FrameUtils;

public class ButtonComponent extends JButton {
    Color background = new Color(0, 123, 255);
    Color foreground = Color.WHITE;
    int fontSize = 16;
    public static int height = 40;

    public static enum Type {
        PRIMARY, SECONDARY, SUCCESS, DANGER, WARNING, INFO, LIGHT, DARK, SMALL
    }

    public ButtonComponent(Builder builder) {
        super(builder.text);
        setDataFromType(builder.type);
        setBackground(background);
        setForeground(foreground);
        setFont(Theme.getMainFont(Font.BOLD, fontSize));
        setBounds(builder.x, builder.y, builder.width, height);
        FrameUtils.addOnClickEvent(this, builder.function);
    }

    public void setPosition(int x, int y, int width) {
        setBounds(x, y, width, height);
    }

    public void setDataFromType(Type type) {
        if (type == Type.PRIMARY) {
            background = new Color(0, 123, 255);
            foreground = Color.WHITE;
        } else if (type == Type.SECONDARY) {
            background = new Color(108, 117, 125);
            foreground = Color.WHITE;
        } else if (type == Type.DANGER) {
            background = new Color(220, 53, 69);
            foreground = Color.WHITE;
        } else if (type == Type.SMALL) {
            background = Color.WHITE;
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
