package com.uni.thanosgym.components;

import java.awt.Color;

import javax.swing.JCheckBox;

public class CheckBoxComponent extends JCheckBox {
    public static int height = 20;

    public CheckBoxComponent(Builder builder) {
        super(builder.text);
        setBackground(Color.WHITE);
        setBounds(builder.x, builder.y, builder.width, height);
    }

    public static class Builder {
        private int x = 0;
        private int y = 0;
        private int width = 0;
        private String text;

        public Builder position(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public CheckBoxComponent build() {
            return new CheckBoxComponent(this);
        }
    }
}
