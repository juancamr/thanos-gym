package com.uni.thanosgym.components;

import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.uni.thanosgym.common.Theme;
import com.uni.thanosgym.components.lib.RoundJTextField;
import com.uni.thanosgym.components.lib.RoundedJPassword;

public class InputComponent {

    public static enum Type {
        TEXT,
        PASSWORD
    }

    private JLabel label;
    private JTextField input;
    private JPasswordField password;
    private Type type;

    public InputComponent(String label, int x, int y, int width, Type type) {
        this.type = type;
        this.label = new JLabel(label);
        this.label.setFont(Theme.getMainFont(Font.PLAIN, 16));
        this.label.setBounds(x, y, width, 15);
        if (type == Type.PASSWORD) {
            password = new RoundedJPassword(5);
            password.setBounds(x, y + 25, width, 35);
        } else if (type == Type.TEXT) {
            input = new RoundJTextField(5);
            input.setFont(Theme.getMainFont(Font.PLAIN, 14));
            input.setBounds(x, y + 25, width, 35);
        }
    }

    public Component getInput() {
        if (type == Type.TEXT) {
            return input;
        } else {
            return password;
        }
    }

    public JLabel getLabel() {
        return label;
    }

    public String getInputContent() {
        if (type == Type.TEXT) {
            return input.getText();
        } else if (type == Type.PASSWORD) {
            return String.valueOf(password.getPassword());
        }
        return "";
    }

    public void insertComponent(List<Component> components) {
        components.add(label);
        if (type == Type.PASSWORD) {
            components.add(password);
        } else if (type == Type.TEXT) {
            components.add(input);
        }
    }
}
