package com.uni.thanosgym.controller;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.uni.thanosgym.dao.CRUDProducto;
import com.uni.thanosgym.model.Producto;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.view.MainWindow;
import com.uni.thanosgym.view.PanelProducto;

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
        FrameUtils.showPanel(vista, panel);
        Response<Producto> response = CRUDProducto.getInstance().getAll();
        if (response.isSuccess()) {
            fillTable(response.getDataList());
            if (!panelRendered) {
                FrameUtils.addHandleChangeEvent(panel.jtxtBusquedaProducto, ControladorProducto::busqueda);
                panelRendered = true;
            }
        } else
            Messages.show("Error al obtener todos los productos");
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
