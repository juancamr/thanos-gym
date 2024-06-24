package com.uni.thanosgym.components;


import java.awt.Color;

import javax.swing.JButton;

import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.components.lib.RoundedBorder;

public class ButtonComponent extends JButton {

    public ButtonComponent(String text, int x, int y, int width, int height) {
        super(text);
        this.setBorder(new RoundedBorder(10));
        this.setBackground(Color.WHITE);
        this.setBounds(x, y, width, height);
    }

    public void addOnClickEvent(Runnable function) {
        FrameUtils.addOnClickEvent(this, function);
    }
}
