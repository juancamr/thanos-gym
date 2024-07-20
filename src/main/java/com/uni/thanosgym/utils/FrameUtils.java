package com.uni.thanosgym.utils;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.juancamr.components.RoundedLabel;
import com.uni.thanosgym.model.Response;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;
import javax.swing.JFileChooser;

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
    public static void clearInputs(JTextComponent[] inputs) {
        for (JTextComponent input : inputs) {
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
     * Submit on enter key press
     *
     * @param input Text field
     * @param r     Lambda function
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
     * @param r     Lambda function
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
     * @param button      Button
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
     * @param button      Button
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
     * @param input    TextField
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
     * @param table         La tabla a la cual agregar el evento de selección
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

    public static Font getMainFont(int weight, int size) {
        return new Font("Malgun Gothic", weight, size);

    }

    public static void renderImageFromWeb(String imageUrl, JLabel label) {
        label.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                try {
                    BufferedImage originalImage = ImageIO.read(new URL(imageUrl));
                    Image scaledImage = getScaledImage(originalImage, label.getWidth(), label.getHeight());
                    label.setIcon(new ImageIcon(scaledImage));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public static void renderImageNatively(String imageUrl, JLabel label) {
        try {
            URL url = new URL(imageUrl);
            ImageIcon imageIcon = new ImageIcon(url);
            label.setIcon(imageIcon);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    private static Image getScaledImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        double originalWidth = originalImage.getWidth();
        double originalHeight = originalImage.getHeight();

        double scaleFactor = Math.max(targetWidth / originalWidth, targetHeight / originalHeight);

        int newWidth = (int) (originalWidth * scaleFactor);
        int newHeight = (int) (originalHeight * scaleFactor);

        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
        g2d.dispose();

        return scaledImage.getSubimage((newWidth - targetWidth) / 2, (newHeight - targetHeight) / 2, targetWidth, targetHeight);
    }


    public static Response<File> chooseImage(JPanel ventana) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecciona una imagen");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Only allow image files to be selected
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png")
                            || filename.endsWith(".gif");
                }
            }

            @Override
            public String getDescription() {
                return "Image Files (*.jpg, *.jpeg, *.png, *.gif)";
            }
        });

        int result = fileChooser.showOpenDialog(ventana);
        if (result == JFileChooser.APPROVE_OPTION) {
            return new Response<File>(true, fileChooser.getSelectedFile());
        } else
            return new Response<File>(false, "No se pudo seleccionar la imagen");
    }

    public static void renderImageToLabel(File image, JLabel label) {
        try {
            ImageIcon imageIcon = new ImageIcon(image.getAbsolutePath());
            label.setIcon(imageIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setupWindow(JFrame ventana){ 
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setVisible(true);
    }
}
