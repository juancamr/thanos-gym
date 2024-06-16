package com.uni.thanosgym.controller;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import com.uni.thanosgym.dao.CRUDProducto;
import com.uni.thanosgym.model.Producto;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.view.MainWindow;
import com.uni.thanosgym.view.PanelProducto;
import com.uni.thanosgym.view.VentanaAgregarProducto;

public class ControladorProducto {
    public static PanelProducto panel;
    public static boolean panelRendered = false;
    public static DefaultTableModel modelo;
    public static String[] titulosTabla = { "ID", "Nombre", "Cantidad", "Precio" };

    public static void showPanel() {
        MainWindow vista = ControladorMainWindow.getMainWindow();
        PanelProducto panel = ControladorProducto.getPanel();
        modelo = new DefaultTableModel(null, titulosTabla);
        panel.jtblProducto.setModel(modelo);
        Response<Producto> response = CRUDProducto.getInstance().getAll();
        if (!response.isSuccess()) {
            Messages.show("Error al obtener todos los productos");
            return;
        }
        fillTable(response.getDataList());
        if (!panelRendered) {
            FrameUtils.addHandleChangeEvent(panel.jtxtBusquedaProducto, ControladorProducto::busqueda);
            FrameUtils.addOnClickEvent(panel.jbtnCrear, ControladorProducto::showAgregarWindow);
            panelRendered = true;
        }
        FrameUtils.showPanel(vista, panel);
    }

    public static void showAgregarWindow() {
        VentanaAgregarProducto ventana = new VentanaAgregarProducto();
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FrameUtils.addOnClickEvent(ventana.jbtnCrear, () -> ControladorProducto.crearProducto(ventana));
        FrameUtils.showWindow(ventana, "Agregar Producto");
    }

    private static boolean validaciones(String nombre, String cantidad, String precio) {
        if (nombre.isEmpty() || cantidad.isEmpty() || precio.isEmpty()) {
            Messages.show("Por favor llene todos los campos");
            return false;
        }
        if (!StringUtils.isInteger(cantidad)) {
            Messages.show("La cantidad debe ser un número entero");
            return false;
        }
        if (!StringUtils.isDecimal(precio)) {
            Messages.show("El precio debe ser un número decimal");
            return false;
        }
        return true;
    }

    public static void showEditarWindow(int id) {
        Response<Producto> response = CRUDProducto.getInstance().getById(id);
        if (!response.isSuccess()) {
            Messages.show("Error al obtener el producto");
            return;
        }
        Producto producto = response.getData();
        VentanaAgregarProducto ventana = new VentanaAgregarProducto();
        ventana.jbtnCrear.setText("Editar");
        ventana.jtxtNombreProducto.setText(producto.getNombre());
        ventana.jtxtCantidad.setText(String.valueOf(producto.getCantidad()));
        ventana.jtxtPrecio.setText(String.valueOf(producto.getPrecio()));
        FrameUtils.addOnClickEvent(ventana.jbtnCrear, () -> editarProducto(ventana, id));
        FrameUtils.showWindow(ventana, "Editar producto");
    }

    public static void showOptions(int idProducto) {
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
            ControladorProducto.showEditarWindow(idProducto);
        });

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBounds(100, 0, 100, 50);
        FrameUtils.addOnClickEvent(eliminarButton, () -> {
            ventana.dispose();
            ControladorProducto.eliminarProducto(idProducto);
        });

        panel.add(editarButton);
        panel.add(eliminarButton);
        ventana.add(panel);
        ventana.setVisible(true);
    }

    public static void busqueda() {
        PanelProducto panel = ControladorProducto.getPanel();
        String query = panel.jtxtBusquedaProducto.getText();
        if (query.isEmpty()) {
            modelo.setRowCount(0);
            fillTable(CRUDProducto.getInstance().getAll().getDataList());
        } else {
            Response<Producto> response = CRUDProducto.getInstance().getAllByName(query);
            if (response.isSuccess()) {
                modelo.setRowCount(0);
                fillTable(response.getDataList());
            } else
                Messages.show("Error al obtener todos los productos");
        }
    }

    public static void editarProducto(VentanaAgregarProducto ventana, int id) {
        String nombre = ventana.jtxtNombreProducto.getText();
        String cantidad = ventana.jtxtCantidad.getText();
        String precio = ventana.jtxtPrecio.getText();
        if (!validaciones(nombre, cantidad, precio))
            return;
        Producto producto = new Producto(id, nombre, Integer.parseInt(cantidad), Double.parseDouble(precio));
        Response<Producto> response = CRUDProducto.getInstance().update(producto);
        if (!response.isSuccess()) {
            Messages.show("Error al editar el producto");
            return;
        }
        showPanel();
        Messages.show("Producto editado exitosamente");
        ventana.dispose();
    }

    public static void crearProducto(VentanaAgregarProducto ventana) {
        String nombre = ventana.jtxtNombreProducto.getText();
        String cantidad = ventana.jtxtCantidad.getText();
        String precio = ventana.jtxtPrecio.getText();
        if (!validaciones(nombre, cantidad, precio)) {
            return;
        }
        Producto producto = new Producto(nombre, Integer.parseInt(cantidad), Double.parseDouble(precio));
        Response<Producto> response = CRUDProducto.getInstance().create(producto);
        if (!response.isSuccess()) {
            Messages.show("Error al crear el producto");
            return;
        }
        modelo.addRow(response.getData().showAll());
        Messages.show("Producto creado exitosamente");
        ventana.dispose();
    }

    public static void eliminarProducto(int id) {
        Response<Producto> res = CRUDProducto.getInstance().delete(id);
        if (!res.isSuccess()) {
            Messages.show("Error al eliminar el producto");
            return;
        }
        Messages.show("Producto eliminado exitosamente");
        showPanel();
    }

    public static void fillTable(List<Producto> lista) {
        for (Producto producto : lista) {
            modelo.addRow(producto.showAll());
        }
    }

    public static PanelProducto getPanel() {
        if (panel == null) {
            panel = new PanelProducto();
        }
        return panel;
    }
}
