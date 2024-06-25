package com.uni.thanosgym.components;

import java.awt.*;

import javax.swing.JButton;

import com.uni.thanosgym.common.Theme;
import com.uni.thanosgym.utils.FrameUtils;

public class ButtonComponent extends JButton {
    Color background = new Color(0, 123, 255);
    Color foreground = Color.WHITE;
    int fontSize = 16;
    int height = 40;

    public static enum Type {
        PRIMARY, SECONDARY, SUCCESS, DANGER, WARNING, INFO, LIGHT, DARK, SMALL
    }

    public ButtonComponent(String text, int x, int y, int width, Type type) {
        super(text);
        setDataFromType(type);
        setBackground(background);
        setForeground(foreground);
        setFont(Theme.getMainFont(Font.BOLD, fontSize));
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

    public void onClick(Runnable function) {
        FrameUtils.addOnClickEvent(this, function);
    }
}
