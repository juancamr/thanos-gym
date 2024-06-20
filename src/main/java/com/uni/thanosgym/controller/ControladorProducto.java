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
import com.uni.thanosgym.utils.TablaUtils;
import com.uni.thanosgym.view.MainWindow;
import com.uni.thanosgym.view.PanelProducto;
import com.uni.thanosgym.view.VentanaAgregarProducto;

public class ControladorProducto {
    public static PanelProducto panel;
    public static boolean panelRendered = false;
    public static DefaultTableModel modelo;
    public static String[] titulosTabla = { "ID", "Nombre", "Cantidad", "Precio" };
    public static List<Producto> listaProductos;

    public static List<Producto> getListaProductos() {
        if (listaProductos == null) {
            listaProductos = CRUDProducto.getInstance().getAll().getDataList();
        }
        return listaProductos;
    }

    public static void showPanel() {
        MainWindow vista = ControladorMainWindow.getMainWindow();
        PanelProducto panel = ControladorProducto.getPanel();
        if (!panelRendered) {
            modelo = new DefaultTableModel(null, titulosTabla);
            TablaUtils.formatoTablaProductoOUtilildad(panel.jtblProducto);
            panel.jtblProducto.setModel(modelo);
            FrameUtils.addHandleChangeEvent(panel.jtxtBusquedaProducto, ControladorProducto::busqueda);
            FrameUtils.addOnClickEvent(panel.jbtnCrear, ControladorProducto::showAgregarWindow);
            panelRendered = true;
        }
        fillTable(getListaProductos());
        FrameUtils.showPanel(vista, panel);
    }

    public static void showAgregarWindow() {
        VentanaAgregarProducto ventana = new VentanaAgregarProducto();
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FrameUtils.addEnterEvent(ventana.jtxtCantidad, () -> ControladorProducto.crearProducto(ventana));
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

    public static void showOptions(Producto producto) {
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
            ControladorProducto.showEditarWindow(producto);
        });

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.setBounds(100, 0, 100, 50);
        FrameUtils.addOnClickEvent(eliminarButton, () -> {
            ventana.dispose();
            ControladorProducto.eliminarProducto(producto.getId());
        });

        panel.add(editarButton);
        panel.add(eliminarButton);
        ventana.add(panel);
        ventana.setVisible(true);
    }

    public static void showEditarWindow(Producto producto) {
        VentanaAgregarProducto ventana = new VentanaAgregarProducto();
        ventana.jbtnCrear.setText("Editar");
        ventana.jtxtNombreProducto.setText(producto.getNombre());
        ventana.jtxtCantidad.setText(String.valueOf(producto.getCantidad()));
        ventana.jtxtPrecio.setText(String.valueOf(producto.getPrecio()));
        FrameUtils.addOnClickEvent(ventana.jbtnCrear, () -> editarProducto(ventana, producto.getId()));
        FrameUtils.showWindow(ventana, "Editar producto");
    }

    private static int getIndex(List<Producto> lista, int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public static void busqueda() {
        PanelProducto panel = ControladorProducto.getPanel();
        String query = panel.jtxtBusquedaProducto.getText();
        if (query.isEmpty()) {
            modelo.setRowCount(0);
            fillTable(getListaProductos());
        } else {
            List<Producto> filteredList = getListaProductos().stream()
                    .filter(producto -> producto.getNombre().toLowerCase().contains(query.toLowerCase()))
                    .toList();
            fillTable(filteredList);
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
        int position = getIndex(getListaProductos(), id);
        ControladorProducto.getListaProductos().set(position, producto);
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
        Producto productoWithTheSameName = getListaProductos().stream().filter(p -> p.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
        if (productoWithTheSameName != null) {
            Messages.show("Ya existe un producto con el mismo nombre");
            return;
        }
        Response<Producto> response = CRUDProducto.getInstance().create(producto);
        if (!response.isSuccess()) {
            Messages.show("Error al crear el producto");
            return;
        }
        producto.setId(response.getId());
        getListaProductos().add(producto);
        Messages.show("Producto creado exitosamente");
        showPanel();
        ventana.dispose();
    }

    public static void eliminarProducto(int id) {
        Response<Producto> res = CRUDProducto.getInstance().delete(id);
        if (!res.isSuccess()) {
            Messages.show("Error al eliminar el producto");
            return;
        }
        int idx = getIndex(getListaProductos(), id);
        getListaProductos().remove(idx);
        Messages.show("Producto eliminado exitosamente");
        showPanel();
    }

    public static void fillTable(List<Producto> lista) {
        modelo.setRowCount(0);
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
