package com.uni.thanosgym.controller.client;

import com.uni.thanosgym.dao.CRUDContrato;
import com.uni.thanosgym.model.Contrato;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.view.PanelClientPayments;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ControladorClientBoletas {

    public static PanelClientPayments vista;
    public static DefaultTableModel modelo;
    public static String[] titulosTabla = {"ID", "Codigo de transacción", "Fecha de Creación", "Nombre del cliente", "Total"};

    public static void mostrarPagosClient(int clientId) {
        vista = ControladorClientBoletas.getWindowTableBoletas();
        FrameUtils.showWindow(vista, "Lista de Pagos");
        modelo = new DefaultTableModel(null, titulosTabla);
        vista.jtblBoletas.setModel(modelo);
        vista.setSize(750, 590);
        vista.setResizable(false);
        vista.setLocationRelativeTo(vista);
        vista.setVisible(true);
        Response<Contrato> response = CRUDContrato.getInstance().getByCliente(clientId);
        
        vista.jbtnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose(); // Cierra la ventana
            }
        });
        
        if (response.isSuccess()) {
            modelo.setRowCount(0);
            List<Contrato> payments = response.getDataList();
            if (payments != null) {
                llenarTablaPagos(payments);
            } else {
                Messages.show("El cliente no tiene pagos registrados.");
            }
        } else {
            Messages.show("Error al obtener los pagos del cliente: " + response.getMessage());
        }
    }

    private static void llenarTablaPagos(List<Contrato> lista) {
        DefaultTableModel model = (DefaultTableModel) vista.jtblBoletas.getModel();

        for (Contrato payment : lista) {
            model.addRow(new Object[]{
                payment.getId(),
                payment.getTransactionCode(),
                StringUtils.parseSpanishDate(payment.getCreatedAt()),
                payment.getCliente().getFullName(),
                String.format("S/ %.2f", payment.getPlan().getPrice())
            });
        }
    }

    public static PanelClientPayments getWindowTableBoletas() {
        if (vista == null) {
            vista = new PanelClientPayments();
        }
        return vista;
    }

}
