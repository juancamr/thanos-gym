package com.juancamr.components;

import java.awt.Font;
import javax.swing.JLabel;
import com.uni.thanosgym.config.Theme;

public class Typography extends JLabel {
    public static int headerHeight = 28;
    public static int subheaderHeight = 26;
    public static int bodyHeight = 21;
    public static int smallHeight = 18;

    public static enum Type {
        HEADING,
        SUBHEADING,
        BODY,
        SMALL
    }

    public Typography(String text, Type type, int x, int y, int width) {
        super(text);
        int height = 0;
        if (type == Type.HEADING) {
            setFont(Theme.getMainFont(Font.BOLD, 24));
            height = headerHeight;
        } else if (type == Type.SUBHEADING) {
            setFont(Theme.getMainFont(Font.BOLD, 18));
            height = subheaderHeight;
        } else if (type == Type.BODY) {
            setFont(Theme.getMainFont(Font.PLAIN, 16));
            height = bodyHeight;
        } else if (type == Type.SMALL) {
            setFont(Theme.getMainFont(Font.PLAIN, 12));
            height = 18;
        }
        setBounds(x, y, width, height);
    }

    public static class Builder {
        private String text;
        private Type type;
        private int x;
        private int y;
        private int width;

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder type(Type type) {
            this.type = type;
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

        public Typography build() {
            if (text == null || type == null) {
                throw new IllegalArgumentException("Text and type must be set");
            }
            return new Typography(text, type, x, y, width);
        }
    }
}

