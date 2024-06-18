package com.uni.thanosgym.controller;

import com.uni.thanosgym.dao.CRUDCliente;
import com.uni.thanosgym.model.Cliente;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.view.WindowTableClients;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ControladorWindowClients {

    public static WindowTableClients vista;
    public static boolean vistaRendered = false;
    public static DefaultTableModel modelo;
    public static String[] titulosTabla = {"ID", "DNI", "Nombre", "Fecha de Creación", "Email", "Telefono", "Dirección"};

    public static void showWindow() {
        vista = ControladorWindowClients.getWindowTableClients();
        FrameUtils.showWindow(vista, "Búsqueda de Clientes");
        modelo = new DefaultTableModel(null, titulosTabla);
        vista.jtblClient.setModel(modelo);
        vista.setSize(1060, 690);
        vista.setResizable(false);
        vista.setLocationRelativeTo(vista);
        vista.setVisible(true);

        Response<Cliente> response = CRUDCliente.getInstance().readAll();
        if (response.isSuccess()) {
            fillTable(response.getDataList());
            if (!vistaRendered) {
                FrameUtils.addHandleChangeEvent(vista.jtxtNameBuscar, ControladorWindowClients::busqueda);
                vistaRendered = true;
            }
        } else {
            Messages.show("Error al obtener todos los productos");
        }
    }

    public static void busqueda() {
        WindowTableClients vista = ControladorWindowClients.getWindowTableClients();
        String query = vista.jtxtNameBuscar.getText();
        if (query.isEmpty()) {
            modelo.setRowCount(0);
            fillTable(CRUDCliente.getInstance().getAll().getDataList());
        } else {
            Response<Cliente> response = CRUDCliente.getInstance().getAllByName(query);
            if (response.isSuccess()) {
                modelo.setRowCount(0);
                fillTable(response.getDataList());
            } else {
                Messages.show("Error al obtener todos los clientes");
            }
        }
    }

    public static void fillTable(List<Cliente> lista) {
        for (Cliente cliente : lista) {
            modelo.addRow(new Object[]{
                cliente.getId(),
                cliente.getDni(),
                cliente.getFullName(),
                cliente.getCreated_At(),
                cliente.getEmail(),
                cliente.getPhone(),
                cliente.getDireccion()
            });
        }
    }

    public static WindowTableClients getWindowTableClients() {
        if (vista == null) {
            vista = new WindowTableClients();
        }
        return vista;
    }
}
