package com.uni.thanosgym.controller;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.uni.thanosgym.dao.CRUDUtilidad;
import com.uni.thanosgym.model.Utilidad;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.view.MainWindow;
import com.uni.thanosgym.view.PanelUtilidad;
import com.uni.thanosgym.view.VentanaAgregarUtilidad;

public class ControladorUtilidad {
    public static PanelUtilidad panel;
    public static boolean panelRendered = false;
    public static DefaultTableModel modelo;
    public static String[] titulosTabla = { "ID", "Nombre", "Cantidad", "peso" };

    public static void showPanel() {
        MainWindow vista = ControladorMainWindow.getMainWindow();
        PanelUtilidad panel = ControladorUtilidad.getPanel();
        modelo = new DefaultTableModel(null, titulosTabla);
        panel.jtblUtilidad.setModel(modelo);
        Response<Utilidad> response = CRUDUtilidad.getInstance().getAll();
        if (!response.isSuccess()) {
            Messages.show("Error al obtener todos los utilidads");
            return;
        }
        fillTable(response.getDataList());
        if (!panelRendered) {
            FrameUtils.addHandleChangeEvent(panel.jtxtBusquedaUtilidad, ControladorUtilidad::busqueda);
            FrameUtils.addOnClickEvent(panel.jbtnCrear, ControladorUtilidad::showAgregarWindow);
            panelRendered = true;
        }
        FrameUtils.showPanel(vista, panel);
    }

    public static void showAgregarWindow() {
        VentanaAgregarUtilidad ventana = new VentanaAgregarUtilidad();
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FrameUtils.addOnClickEvent(ventana.jbtnCrear, () -> ControladorUtilidad.crearUtilidad(ventana));
        FrameUtils.showWindow(ventana, "Agregar Utilidad");
    }

    private static boolean validaciones(String nombre, String cantidad, String peso) {
        if (nombre.isEmpty() || cantidad.isEmpty() || peso.isEmpty()) {
            Messages.show("Por favor llene todos los campos");
            return false;
        }
        if (!StringUtils.isInteger(cantidad)) {
            Messages.show("La cantidad debe ser un número entero");
            return false;
        }
        if (!StringUtils.isInteger(peso)) {
            Messages.show("El peso debe ser un número decimal");
            return false;
        }
        return true;
    }

    public static void showEditarWindow(int id) {
        Response<Utilidad> response = CRUDUtilidad.getInstance().getById(id);
        if (!response.isSuccess()) {
            Messages.show("Error al obtener el utilidad");
            return;
        }
        Utilidad utilidad = response.getData();
        VentanaAgregarUtilidad ventana = new VentanaAgregarUtilidad();
        ventana.jbtnCrear.setText("Editar");
        ventana.jtxtNombreUtilidad.setText(utilidad.getNombre());
        ventana.jtxtCantidad.setText(String.valueOf(utilidad.getCantidad()));
        ventana.jtxtPeso.setText(String.valueOf(utilidad.getPeso()));
        FrameUtils.addOnClickEvent(ventana.jbtnCrear, () -> editarUtilidad(ventana, id));
        FrameUtils.showWindow(ventana, "Editar utilidad");
    }

    public static void showOptions(int idUtilidad) {
        JFrame ventana = new JFrame();
        ventana.setSize(300, 100);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setVisible(true);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));

        JButton editarButton = new JButton("Editar");
        editarButton.setBounds(0, 0, 100, 50);
        FrameUtils.addOnClickEvent(editarButton, () -> {
            ventana.dispose();
            ControladorUtilidad.showEditarWindow(idUtilidad);
        });

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBounds(100, 0, 100, 50);
        FrameUtils.addOnClickEvent(eliminarButton, () -> {
            ventana.dispose();
            ControladorUtilidad.eliminarUtilidad(idUtilidad);
        });

        panel.add(editarButton);
        panel.add(eliminarButton);
        ventana.add(panel);
        ventana.setVisible(true);
    }

    public static void busqueda() {
        PanelUtilidad panel = ControladorUtilidad.getPanel();
        String query = panel.jtxtBusquedaUtilidad.getText();
        if (query.isEmpty()) {
            modelo.setRowCount(0);
            fillTable(CRUDUtilidad.getInstance().getAll().getDataList());
        } else {
            Response<Utilidad> response = CRUDUtilidad.getInstance().getAllByName(query);
            if (response.isSuccess()) {
                modelo.setRowCount(0);
                fillTable(response.getDataList());
            } else
                Messages.show("Error al obtener todos los utilidads");
        }
    }

    public static void editarUtilidad(VentanaAgregarUtilidad ventana, int id) {
        String nombre = ventana.jtxtNombreUtilidad.getText();
        String cantidad = ventana.jtxtCantidad.getText();
        String peso = ventana.jtxtPeso.getText();
        if (!validaciones(nombre, cantidad, peso))
            return;
        Utilidad utilidad = new Utilidad(id, nombre, Integer.parseInt(cantidad), Integer.parseInt(peso));
        Response<Utilidad> response = CRUDUtilidad.getInstance().update(utilidad);
        if (!response.isSuccess()) {
            Messages.show("Error al editar el utilidad");
            return;
        }
        showPanel();
        Messages.show("Utilidad editado exitosamente");
        ventana.dispose();
    }

    public static void crearUtilidad(VentanaAgregarUtilidad ventana) {
        String nombre = ventana.jtxtNombreUtilidad.getText();
        String cantidad = ventana.jtxtCantidad.getText();
        String peso = ventana.jtxtPeso.getText();
        if (!validaciones(nombre, cantidad, peso)) {
            return;
        }
        Utilidad utilidad = new Utilidad(nombre, Integer.parseInt(cantidad), Integer.parseInt(peso));
        Response<Utilidad> response = CRUDUtilidad.getInstance().create(utilidad);
        if (!response.isSuccess()) {
            Messages.show("Error al crear el utilidad");
            return;
        }
        modelo.addRow(response.getData().showAll());
        Messages.show("Utilidad creado exitosamente");
        ventana.dispose();
    }

    public static void eliminarUtilidad(int id) {
        Response<Utilidad> res = CRUDUtilidad.getInstance().delete(id);
        if (!res.isSuccess()) {
            Messages.show("Error al eliminar el utilidad");
            return;
        }
        Messages.show("Utilidad eliminado exitosamente");
        showPanel();
    }

    public static void fillTable(List<Utilidad> lista) {
        for (Utilidad utilidad : lista) {
            modelo.addRow(utilidad.showAll());
        }
    }

    public static PanelUtilidad getPanel() {
        if (panel == null) {
            panel = new PanelUtilidad();
        }
        return panel;
    }
}
