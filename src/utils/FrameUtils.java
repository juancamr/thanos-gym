
package utils;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.auth.WindowSession;

public class FrameUtils {

    public static void showWindow(JFrame vista, String title) {
        vista.setTitle(title);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    // for main window
//    public static void showPanel(VentanaPrincipal vista, JPanel panel) {
//        panel.setSize(840, 790);
//        panel.setLocation(0, 0);
//        vista.content.removeAll();
//        vista.content.add(panel, BorderLayout.CENTER);
//        vista.content.revalidate();
//        vista.content.repaint();
//    }

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
