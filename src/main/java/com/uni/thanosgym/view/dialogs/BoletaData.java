/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.uni.thanosgym.view.dialogs;

import javax.swing.JFrame;

import com.uni.thanosgym.model.Boleta;
import com.uni.thanosgym.model.DetalleBoleta;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.utils.Utils;

/**
 *
 * @author juancamr
 */
public class BoletaData extends javax.swing.JFrame {

    /**
     * Creates new form BoletaData
     */
    public BoletaData(Boleta boleta) {
        initComponents();
        
        jtxtNombreCliente.setText(boleta.getCliente().getFullName());
        jtxtDireccionCiente.setText(boleta.getCliente().getDireccion());
        jlblDniCliente.setText(boleta.getCliente().getDni());
        jlblTotal.setText(String.format("S/ %.2f", boleta.getTotal()));
        jlblNumeroBoleta.setText(StringUtils.parseIdBoleta(boleta.getId()));
        jlblRUC.setText(Utils.RUC);

        System.out.println(boleta.getDetallesBoleta());
        for (DetalleBoleta detalle : boleta.getDetallesBoleta()) {
            String[] row = new String[]{String.valueOf(detalle.getCantidad()), detalle.getProducto().getNombre(), String.valueOf(detalle.getProducto().getPrecio()), String.valueOf(detalle.getTotal())};
            ((javax.swing.table.DefaultTableModel) jtblBoleta.getModel()).addRow(row);
        }
        
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlblFechaActual = new com.juancamr.components.Typography();
        typography3 = new com.juancamr.components.Typography();
        jtxtDireccionCiente = new com.juancamr.components.Typography();
        jtxtNombreCliente = new com.juancamr.components.Typography();
        typography4 = new com.juancamr.components.Typography();
        typography5 = new com.juancamr.components.Typography();
        typography6 = new com.juancamr.components.Typography();
        jlblDniCliente = new com.juancamr.components.Typography();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblBoleta = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jlblNumeroBoleta = new com.juancamr.components.Typography();
        jlblRUC = new com.juancamr.components.Typography();
        typography15 = new com.juancamr.components.Typography();
        jPanel3 = new javax.swing.JPanel();
        typography1 = new com.juancamr.components.Typography();
        jlblTotal = new com.juancamr.components.Typography();
        jbtnSendMail = new com.juancamr.components.ButtonComponent();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblFechaActual.setText("Fecha actual");
        jlblFechaActual.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel1.add(jlblFechaActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 120, -1));

        typography3.setText("Fecha");
        typography3.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel1.add(typography3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, -1, -1));

        jtxtDireccionCiente.setText("Direccion del cliente");
        jtxtDireccionCiente.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel1.add(jtxtDireccionCiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 240, -1));

        jtxtNombreCliente.setText("Nombre del cliente");
        jtxtNombreCliente.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel1.add(jtxtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 240, -1));

        typography4.setText("Cliente");
        typography4.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel1.add(typography4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        typography5.setText("Dirección");
        typography5.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel1.add(typography5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        typography6.setText("DNI");
        typography6.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel1.add(typography6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jlblDniCliente.setText("DNI del cliente");
        jlblDniCliente.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel1.add(jlblDniCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 120, -1));

        jtblBoleta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Descripción", "Precio Unid.", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtblBoleta);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 530, 410));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblNumeroBoleta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblNumeroBoleta.setText("typography13");
        jlblNumeroBoleta.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(jlblNumeroBoleta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 130, -1));

        jlblRUC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlblRUC.setText("typography13");
        jlblRUC.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(jlblRUC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 130, -1));

        typography15.setForeground(new java.awt.Color(255, 255, 255));
        typography15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        typography15.setText("BOLETA DE VENTA");
        typography15.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(typography15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 130, -1));

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 26, 170, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 170, 70));

        typography1.setText("Boleta de venta");
        typography1.setType(com.juancamr.components.Typography.Type.HEADING2);
        jPanel1.add(typography1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jlblTotal.setText("S/ 1200.0");
        jlblTotal.setType(com.juancamr.components.Typography.Type.HEADING2);
        jPanel1.add(jlblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, -1, -1));

        jbtnSendMail.setText("Enviar por correo");
        jbtnSendMail.setType(com.juancamr.components.ButtonComponent.Type.PRIMARY);
        jPanel1.add(jbtnSendMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 610, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private com.juancamr.components.ButtonComponent jbtnSendMail;
    private com.juancamr.components.Typography jlblDniCliente;
    private com.juancamr.components.Typography jlblFechaActual;
    private com.juancamr.components.Typography jlblNumeroBoleta;
    private com.juancamr.components.Typography jlblRUC;
    private com.juancamr.components.Typography jlblTotal;
    private javax.swing.JTable jtblBoleta;
    private com.juancamr.components.Typography jtxtDireccionCiente;
    private com.juancamr.components.Typography jtxtNombreCliente;
    private com.juancamr.components.Typography typography1;
    private com.juancamr.components.Typography typography15;
    private com.juancamr.components.Typography typography3;
    private com.juancamr.components.Typography typography4;
    private com.juancamr.components.Typography typography5;
    private com.juancamr.components.Typography typography6;
    // End of variables declaration//GEN-END:variables
}
