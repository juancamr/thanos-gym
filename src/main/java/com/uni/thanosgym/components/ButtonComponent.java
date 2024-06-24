package com.uni.thanosgym.components;


import java.awt.*;

import javax.swing.JButton;

import com.uni.thanosgym.utils.FrameUtils;

public class ButtonComponent extends JButton {

    public static enum Type {
        PRIMARY, SECONDARY, SUCCESS, DANGER, WARNING, INFO, LIGHT, DARK
    }

    public ButtonComponent(String text, int x, int y, int width, Type type) {
        super(text);
        Color[] colors = getColors(type);
        setBackground(colors[0]);
        setForeground(colors[1]);
        setBounds(x, y, width, 30);
    }
    
    public static Color[] getColors(Type type) {
        Color background;
        Color foreground;
        if (type == Type.PRIMARY) {
            background = new Color(0, 123, 255);
            foreground = Color.WHITE;
        } else if (type == Type.SECONDARY) {
            background = new Color(108, 117, 125);
            foreground = Color.WHITE;
        } else if (type == Type.SUCCESS) {
            background = new Color(40, 167, 69);
            foreground = Color.WHITE;
        } else if (type == Type.DANGER) {
            background = new Color(220, 53, 69);
            foreground = Color.WHITE;
        } else if (type == Type.WARNING) {
            background = new Color(255, 193, 7);
            foreground = Color.WHITE;
        } else if (type == Type.INFO) {
            background = new Color(23, 162, 184);
            foreground = Color.WHITE;
        } else if (type == Type.LIGHT) {
            background = new Color(248, 249, 250);
            foreground = Color.BLACK;
        } else if (type == Type.DARK) {
            background = new Color(52, 58, 64);
            foreground = Color.WHITE;
        } else {
            background = Color.WHITE;
            foreground = Color.BLACK;
        }
        return new Color[]{background, foreground};
    }

    public void onClick(Runnable function) {
        FrameUtils.addOnClickEvent(this, function);
    }
}
