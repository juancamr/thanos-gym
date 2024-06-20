package com.uni.thanosgym.controller.client;

import com.uni.thanosgym.dao.CRUDCliente;
import com.uni.thanosgym.model.Client;
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
    public static String[] titulosTabla = { "ID", "DNI", "Nombre", "Fecha de Creación", "Email", "Telefono",
            "Dirección" };

    public static void showWindow() {
        vista = ControladorWindowClients.getWindowTableClients();
        FrameUtils.showWindow(vista, "Búsqueda de Clientes");
        modelo = new DefaultTableModel(null, titulosTabla);
        vista.jtblClient.setModel(modelo);
        vista.setSize(750, 590);
        vista.setResizable(false);
        vista.setLocationRelativeTo(vista);
        vista.setVisible(true);

        if (!ControladorClient.isFull) {
            Response<Client> response = CRUDCliente.getInstance().getAll();
            if (!response.isSuccess()) {
                Messages.show("Error al obtener todos los productos");
                return;
            }
            ControladorClient.listaClientes = response.getDataList();
            ControladorClient.isFull = true;
        }
        fillTable(ControladorClient.listaClientes);

        if (!vistaRendered) {
            FrameUtils.addHandleChangeEvent(vista.jtxtNameBuscar, ControladorWindowClients::busqueda);
            FrameUtils.addTableRowSelectionEvent(vista.jtblClient, ControladorWindowClients::abrirVentanaBoletas);
            vistaRendered = true;
        }
    }

    public static void busqueda() {
        WindowTableClients vista = ControladorWindowClients.getWindowTableClients();
        String query = vista.jtxtNameBuscar.getText();
        if (query.isEmpty()) {
            modelo.setRowCount(0);
            fillTable(ControladorClient.listaClientes);
        } else {
            List<Client> filteredList = ControladorClient.listaClientes.stream()
                    .filter(c -> c.getFullName().toLowerCase().contains(query.toLowerCase())
                            || String.valueOf(c.getDni()).contains(query))
                    .toList();
            fillTable(filteredList);
        }
    }

    public static void abrirVentanaBoletas() {
        int selectedRow = vista.jtblClient.getSelectedRow();
        if (selectedRow != -1) {
            int clientId = (int) vista.jtblClient.getValueAt(selectedRow, 0);
            ControladorClientBoletas.mostrarPagosClient(clientId);
        }
    }

    public static void fillTable(List<Client> lista) {
        modelo.setRowCount(0);
        for (Client cliente : lista) {
            modelo.addRow(cliente.showAll());
        }
    }

    public static WindowTableClients getWindowTableClients() {
        if (vista == null) {
            vista = new WindowTableClients();
        }
        return vista;
    }
}
