/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.uni.thanosgym.view.routes;

import com.uni.thanosgym.utils.Messages;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.juancamr.route.Route;
import com.uni.thanosgym.dao.CRUDBoleta;
import com.uni.thanosgym.model.Reporte;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.view.dialogs.AllBoletas;

/**
 *
 * @author juancamr
 */
@Route("main:reporte")
public class PanelReportes extends javax.swing.JPanel {

    /**
     * Creates new form PanelReportes
     */
    public PanelReportes() {
        initComponents();
        FrameUtils.addOnClickEvent(jbtnBuscar, this::buscarEntreFechas);
    }

    private void buscarEntreFechas() {
        CRUDBoleta crudBoleta = CRUDBoleta.getInstance();
        Response<Reporte> resReporte = crudBoleta.getReportBetweenDates(desde.getDate(), hasta.getDate());
        if (!resReporte.isSuccess()) {
            Messages.show(resReporte.getMessage());
            return;
        }
        if (resReporte.getDataList().isEmpty()) {
            Messages.show("No hay reportes para mostrar");
            return;
        }
        List<String[]> datos = resReporte.getDataList().stream().map(reporte -> {
            return new String[] { StringUtils.parseSpanishDate(reporte.getFecha()),
                    String.valueOf(reporte.getMonto()) };
        }).collect(Collectors.toList());

        fillTable(datos);
    }

    private void fillTable(List<String[]> datos) {
        ((javax.swing.table.DefaultTableModel) jtblReportes.getModel()).setRowCount(0);
        for (String[] dato : datos) {
            ((javax.swing.table.DefaultTableModel) jtblReportes.getModel()).addRow(dato);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        desde = new com.toedter.calendar.JDateChooser();
        hasta = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblReportes = new javax.swing.JTable();
        jbtnBuscar = new com.juancamr.components.ButtonComponent();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(desde, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 200, -1));
        jPanel1.add(hasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 160, -1));

        jtblReportes.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Fecha", "Monto"
                }) {
            Class[] types = new Class[] {
                    java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jtblReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblReportesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblReportes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 620, -1));

        jbtnBuscar.setText("BUSCAR");
        jbtnBuscar.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel1.add(jbtnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 70, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    private void jtblReportesMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jtblReportesMouseClicked
        int fila = jtblReportes.getSelectedRow();
        if (fila != -1) {
            Date fecha = StringUtils.spanishDateToDate(jtblReportes.getValueAt(fila, 0).toString());
            new AllBoletas(fecha);
        }
    }// GEN-LAST:event_jtblReportesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser desde;
    private com.toedter.calendar.JDateChooser hasta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.juancamr.components.ButtonComponent jbtnBuscar;
    private javax.swing.JTable jtblReportes;
    // End of variables declaration//GEN-END:variables
}
