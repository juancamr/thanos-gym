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
    public static List<Utilidad> listaUtilidades;

    public static List<Utilidad> getListaUtilidades() {
        if (listaUtilidades == null)
            listaUtilidades = CRUDUtilidad.getInstance().getAll().getDataList();
        return listaUtilidades;
    }

    public static void showPanel() {
        MainWindow vista = ControladorMainWindow.getMainWindow();
        PanelUtilidad panel = ControladorUtilidad.getPanel();
        if (!panelRendered) {
            FrameUtils.addHandleChangeEvent(panel.jtxtBusquedaUtilidad, ControladorUtilidad::busqueda);
            FrameUtils.addOnClickEvent(panel.jbtnCrear, ControladorUtilidad::showAgregarWindow);
            panelRendered = true;
            modelo = new DefaultTableModel(null, titulosTabla);
            panel.jtblUtilidad.setModel(modelo);
        }
        fillTable(getListaUtilidades());
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

    public static void showEditarWindow(Utilidad utilidad) {
        VentanaAgregarUtilidad ventana = new VentanaAgregarUtilidad();
        ventana.jbtnCrear.setText("Editar");
        ventana.jtxtNombreUtilidad.setText(utilidad.getNombre());
        ventana.jtxtCantidad.setText(String.valueOf(utilidad.getCantidad()));
        ventana.jtxtPeso.setText(String.valueOf(utilidad.getPeso()));
        FrameUtils.addOnClickEvent(ventana.jbtnCrear, () -> editarUtilidad(ventana, utilidad.getId()));
        FrameUtils.showWindow(ventana, "Editar utilidad");
    }

    private static int getIndex(List<Utilidad> lista, int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public static void showOptions(Utilidad utilidad) {
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
            ControladorUtilidad.showEditarWindow(utilidad);
        });

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBounds(100, 0, 100, 50);
        FrameUtils.addOnClickEvent(eliminarButton, () -> {
            ventana.dispose();
            ControladorUtilidad.eliminarUtilidad(utilidad.getId());
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
            fillTable(getListaUtilidades());
        } else {
            List<Utilidad> filteredList = getListaUtilidades().stream()
                    .filter(utilidad -> utilidad.getNombre().contains(query))
                    .toList();
            fillTable(filteredList);
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
        int position = getIndex(getListaUtilidades(), id);
        Messages.show("Utilidad editado exitosamente");
        getListaUtilidades().set(position, utilidad);
        showPanel();
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
        Utilidad utilidadWithTheSameName = getListaUtilidades().stream().filter(u -> u.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
        if (utilidadWithTheSameName != null) {
            Messages.show("Ya existe una utilidad con el mismo nombre");
            return;
        }
        Response<Utilidad> response = CRUDUtilidad.getInstance().create(utilidad);
        if (!response.isSuccess()) {
            Messages.show("Error al crear el utilidad");
            return;
        }
        utilidad.setId(response.getId());
        getListaUtilidades().add(utilidad);
        Messages.show("Utilidad creado exitosamente");
        showPanel();
        ventana.dispose();
    }

    public static void eliminarUtilidad(int id) {
        Response<Utilidad> res = CRUDUtilidad.getInstance().delete(id);
        if (!res.isSuccess()) {
            Messages.show("Error al eliminar el utilidad");
            return;
        }
        int idx = getIndex(getListaUtilidades(), id);
        getListaUtilidades().remove(idx);
        Messages.show("Utilidad eliminado exitosamente");
        showPanel();
    }

    public static void fillTable(List<Utilidad> lista) {
        modelo.setRowCount(0);
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
