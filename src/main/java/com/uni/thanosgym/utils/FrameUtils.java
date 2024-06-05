
package com.uni.thanosgym.utils;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.uni.thanosgym.view.MainWindow;
import com.uni.thanosgym.view.WindowSession;

public class FrameUtils {

    public static void showWindow(JFrame vista, String title) {
        vista.setTitle(title);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
    
    public static void clearInputs(List<TextField> inputs) {
        for (TextField input : inputs) {
            input.setText("");
        }
    }

    public static void clearInputs(TextField input) {
        input.setText("");
    }

    // for main window
    public static void showPanel(MainWindow vista, JPanel panel) {
        panel.setSize(840, 790);
        panel.setLocation(0, 0);
        vista.content.removeAll();
        vista.content.add(panel, BorderLayout.CENTER);
        vista.content.revalidate();
        vista.content.repaint();
    }

    // for session window
    public static void showPanel(WindowSession vista, JPanel panel) {
        panel.setSize(380, 770);
        panel.setLocation(0, 0);
        vista.content.removeAll();
        vista.content.add(panel, BorderLayout.CENTER);
        vista.content.revalidate();
        vista.content.repaint();
    }
}
