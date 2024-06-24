package com.uni.thanosgym.components;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.uni.thanosgym.components.lib.RoundJTextField;
import com.uni.thanosgym.components.lib.RoundedJPassword;

public class InputComponent {

    public static enum InputType {
        TEXT,
        PASSWORD
    }

    private JLabel label;
    private JTextField input;
    private JPasswordField password;
    private InputType type;

    public InputComponent(String label, int x, int y, InputType type) {
        this.type = type;
        this.label = new JLabel(label);
        this.label.setBounds(x, y, 200, 30);
        if (type == InputType.PASSWORD) {
            this.password = new RoundedJPassword();
            this.password.setBounds(x, y + 30, 200, 30);
        } else if (type == InputType.TEXT) {
            this.input = new RoundJTextField();
            this.input.setBounds(x, y + 30, 200, 30);
        }
    }

    public String getInputContent() {
        if (type == InputType.TEXT) {
            return input.getText();
        } else if (type == InputType.PASSWORD) {
            return String.valueOf(password.getPassword());
        }
        return "";
    }

    public void addToPanel(JPanel panel) {
        panel.add(label);
        if (type == InputType.PASSWORD) {
            panel.add(password);
        } else if (type == InputType.TEXT) {
            panel.add(input);
        }
    }
}
