package com.juancamr.components;

import java.awt.Font;
import javax.swing.JLabel;
import com.uni.thanosgym.config.Theme;

public class Typography extends JLabel {
    private Type type = Type.BODY;

    public static enum Type {
        HEADING1,
        HEADING2,
        HEADING3,
        BODY,
        MEDIUM,
        SMALL
    }

    public Typography() {
        super();
        updateTypography();
    }

    public void setType(Type type) {
        this.type = type;
        updateTypography();
        revalidate();
        repaint();
    }

    private void updateTypography() {
        switch (type) {
            case HEADING1:
                setFont(Theme.getMainFont(Font.BOLD, 32));
                break;
            case HEADING2:
                setFont(Theme.getMainFont(Font.BOLD, 20));
                break;
            case HEADING3:
                setFont(Theme.getMainFont(Font.BOLD, 18));
                break;
            case BODY:
                setFont(Theme.getMainFont(Font.PLAIN, 15));
                break;
            case MEDIUM:
                setFont(Theme.getMainFont(Font.PLAIN, 12));
                break;
            case SMALL:
                setFont(Theme.getMainFont(Font.PLAIN, 10));
                break;
            default:
                break;
        }
    }
}
