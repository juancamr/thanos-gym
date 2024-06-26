package com.uni.thanosgym.utils;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.uni.thanosgym.components.lib.RoundedLabel;
import com.uni.thanosgym.model.Response;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import javax.swing.JFileChooser;
import javax.imageio.ImageIO;

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
    // public static void showPanel(MainWindow vista, JPanel panel) {
    // panel.setSize(840, 690);
    // panel.setLocation(0, 0);
    // vista.content.removeAll();
    // vista.content.add(panel, BorderLayout.CENTER);
    // vista.content.revalidate();
    // vista.content.repaint();
    // }

    public static void setupWindow(JFrame window, int width, int height) {
        window.setResizable(false);
        window.setLayout(null);
        window.setSize(width, height);
        window.setLocationRelativeTo(null);
    }

    public static void changePanel(JPanel mainPanel, JPanel contentPanel) {
        mainPanel.removeAll();
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    /**
     * Show panel at the right of the session window 760 x 690
     *
     * @param vista Session window
     * @param panel Panel to show in the window
     */
    // public static void showPanel(WindowSession vista, JPanel panel) {
    // panel.setSize(380, 690);
    // panel.setLocation(0, 0);
    // vista.content.removeAll();
    // vista.content.add(panel, BorderLayout.CENTER);
    // vista.content.revalidate();
    // vista.content.repaint();
    // }

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

    public static void renderImageFromWeb(String imageUrl, JPanel panel, int borderRadius) {
        try {
            URL url = new URL(imageUrl);
            BufferedImage originalImage = ImageIO.read(url);

            // Obtener las dimensiones del panel
            int panelWidth = panel.getWidth();
            int panelHeight = panel.getHeight();

            // Calcular las proporciones de escalado
            float panelAspect = (float) panelWidth / panelHeight;
            float imageAspect = (float) originalImage.getWidth() / originalImage.getHeight();

            int newWidth, newHeight;
            if (panelAspect > imageAspect) {
                // El panel es más ancho en relación a su altura que la imagen
                newWidth = panelWidth;
                newHeight = (int) (panelWidth / imageAspect);
            } else {
                // El panel es más alto en relación a su ancho que la imagen
                newWidth = (int) (panelHeight * imageAspect);
                newHeight = panelHeight;
            }

            // Escalar la imagen manteniendo la proporción
            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            // Convertir la imagen escalada a BufferedImage
            BufferedImage bufferedScaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = bufferedScaledImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(scaledImage, 0, 0, null);
            g2d.dispose();

            // Recortar la imagen para que coincida con las dimensiones del panel
            int x = (newWidth - panelWidth) / 2;
            int y = (newHeight - panelHeight) / 2;
            BufferedImage croppedImage = bufferedScaledImage.getSubimage(x, y, panelWidth, panelHeight);

            // Crear un ImageIcon desde la imagen recortada
            ImageIcon imageIcon = new ImageIcon(croppedImage);

            // Crear un JLabel personalizado
            RoundedLabel imageLabel = new RoundedLabel(imageIcon, borderRadius);

            // Añadir el JLabel al panel
            panel.setLayout(new BorderLayout());
            panel.add(imageLabel, BorderLayout.CENTER);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Response<File> chooseImage(JPanel panel) {
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

        int result = fileChooser.showOpenDialog(panel);
        if (result == JFileChooser.APPROVE_OPTION) {
            return new Response<File>(true, fileChooser.getSelectedFile());
        } else
            return new Response<File>(false, "No se pudo seleccionar la imagen");
    }
}
