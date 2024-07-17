package com.juancamr.components;

import java.awt.Font;

import javax.swing.JTextField;

import com.uni.thanosgym.config.Theme;

public class InputComponent extends JTextField {

    public InputComponent() {
        super();
        setFont(Theme.getMainFont(Font.PLAIN, 15));
        setText("");
    }

}
