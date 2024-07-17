package com.juancamr.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.uni.thanosgym.config.Theme;
import com.juancamr.components.lib.RoundedPanel;
import com.uni.thanosgym.utils.FrameUtils;

public class InputComponent extends JPanel {

    public static enum Type {
        TEXT,
        PASSWORD
    }

    private JLabel label;
    private JTextField input;
    private JPasswordField password;
    private Type type;

    public InputComponent(Builder builder) {
        super();
        int height = 35;
        this.setLayout(null);
        this.setBounds(builder.x, builder.y, builder.width, 60);
        setBackground(Color.WHITE);

        type = builder.type;
        label = new JLabel(builder.label);
        label.setFont(Theme.getMainFont(Font.PLAIN, 16));
        label.setBounds(0, 0, builder.width, 15);

        add(this.label);

        JPanel inputPanel = new RoundedPanel(10);
        inputPanel.setBounds(0, 25, builder.width, height);
        inputPanel.setLayout(null);
        inputPanel.setBackground(Theme.inputColor);

        if (type == Type.PASSWORD) {
            password = new JPasswordField();
            password.setBounds(10, 0, builder.width - 20, height);
            password.setBackground(Theme.inputColor);
            password.setBorder(BorderFactory.createEmptyBorder());
            inputPanel.add(this.password);
        } else if (type == Type.TEXT) {
            input = new JTextField();
            input.setFont(Theme.getMainFont(Font.PLAIN, 14));
            input.setBounds(10, 0, builder.width - 20, height);
            input.setBackground(Theme.inputColor);
            input.setBorder(BorderFactory.createEmptyBorder());
            inputPanel.add(this.input);
        }
        add(inputPanel);
    }

    public JTextField getInput() {
        return input;
    }

    public JPasswordField getPasswordInput() {
        return password;
    }

    public void setContent(String content) {
        input.setText(content);
    }

    public String getContent() {
        if (type == Type.TEXT) {
            return input.getText();
        } else {
            return String.valueOf(password.getPassword());
        }
    }

    public void addOnEnterToInput(Runnable function) {
        FrameUtils.addEnterEvent(input, function);
    }

    public void addOnEnterToPassword(Runnable function) {
        FrameUtils.addEnterEvent(password, function);
    }

    public static class Builder {
        private String label;
        private int x = 0;
        private int y = 0;
        private int width = 0;
        private Type type;

        public Builder label(String label) {
            this.label = label;
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

        public InputComponent build() {
            return new InputComponent(this);
        }

    }
}
