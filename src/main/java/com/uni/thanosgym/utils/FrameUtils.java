package com.uni.thanosgym.utils;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.uni.thanosgym.view.MainWindow;
import com.uni.thanosgym.view.WindowSession;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Utility class for JFrame and JPanel
 *
 * @author juancamr
 */
public class FrameUtils {

    /**
     * Mostrar ventana, centrarlo y establecerle un titulo
     *
     * @param vista Window frame
     * @param title Title of the window
     */
    public static void showWindow(JFrame vista, String title) {
        vista.setTitle(title);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    /**
     * Limpiar el texto de todos los campos de texto proporcionados y establecer
     * el foco en el primer campo.
     *
     * @param inputs An array of text fields to be cleared.
     */
    public static void clearInputs(JTextField[] inputs) {
        for (JTextField input : inputs) {
            input.setText("");
        }
        inputs[0].requestFocus();
    }

    /**
     * Limpiar el texto de un campo de texto y establecer el foco en el campo.
     *
     * @param input A text field to be cleared.
     */
    public static void clearInputs(JTextField input) {
        input.setText("");
        input.requestFocus();
    }

    /**
     * Show panel at the right of the main window
     *
     * @param vista Main window 1060 x 690
     * @param panel Panel to show in the window
     */
    public static void showPanel(MainWindow vista, JPanel panel) {
        panel.setSize(840, 690);
        panel.setLocation(0, 0);
        vista.content.removeAll();
        vista.content.add(panel, BorderLayout.CENTER);
        vista.content.revalidate();
        vista.content.repaint();
    }

    /**
     * Show panel at the right of the session window 760 x 690
     *
     * @param vista Session window
     * @param panel Panel to show in the window
     */
    public static void showPanel(WindowSession vista, JPanel panel) {
        panel.setSize(380, 690);
        panel.setLocation(0, 0);
        vista.content.removeAll();
        vista.content.add(panel, BorderLayout.CENTER);
        vista.content.revalidate();
        vista.content.repaint();
    }

    /**
     * Submit on enter key press
     *
     * @param input Text field
     * @param r Lambda function
     */
    public static void addEnterEvent(JTextField input, Runnable r) {
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                r.run();
            }
        });
    }

    /**
     * Submit on enter key press
     *
     * @param input Password field
     * @param r Lambda function
     */
    public static void addEnterEvent(JPasswordField input, Runnable r) {
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                r.run();
            }
        });
    }

    /**
     * Agregar evento de clic a un boton
     *
     * @param button Button
     * @param handleClick Lambda function
     */
    public static <T> void addOnClickEvent(JComboBox<T> combo, Runnable handleClick) {
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleClick.run();
            }
        });
    }

    /**
     * Agregar evento de clic a un boton
     *
     * @param button Button
     * @param handleClick Lambda function
     */
    public static void addOnClickEvent(JButton button, Runnable handleClick) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleClick.run();
            }
        });
    }

    /**
     * Agregar evento cuando cambia el texto
     *
     * @param input TextField
     * @param function Lambda function
     */
    public static void addHandleChangeEvent(JTextField input, Runnable function) {
        input.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                function.run();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                function.run();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                function.run();
            }
        });

    }

    public static void removeAllEvents(JTextField input) {
        for (ActionListener al : input.getActionListeners()) {
            input.removeActionListener(al);
        }
    }


    public static void removeAllEvents(JButton button) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
    }


    /**
     * Agregar evento de selección de fila a una tabla
     *
     * @param table La tabla a la cual agregar el evento de selección
     * @param onRowSelected La acción a ejecutar cuando se selecciona una fila
     */
    public static void addTableRowSelectionEvent(JTable table, Runnable onRowSelected) {
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) { 
                        onRowSelected.run();
                    }
                }
            }
        });
    }

}
