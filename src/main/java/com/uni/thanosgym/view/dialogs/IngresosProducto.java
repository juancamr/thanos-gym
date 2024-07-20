/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.uni.thanosgym.view.dialogs;

import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.dao.CRUDDetalleProducto;
import com.uni.thanosgym.model.DetalleProducto;
import com.uni.thanosgym.model.Producto;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;

/**
 *
 * @author juancamr
 */
public class IngresosProducto extends javax.swing.JFrame {

    /**
     * Creates new form IngresosProductov
     */
    public IngresosProducto(Producto producto) {
        initComponents();
        jlblProductName.setText(producto.getNombre());
        Response<DetalleProducto> response = CRUDDetalleProducto.getInstance().getAllByProductoId(producto.getId());
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return;
        }

        for (DetalleProducto detalle : response.getDataList()) {
            String[] datos = new String[] {
                StringUtils.parseSpanishDate(detalle.getCreatedAt()), String.valueOf(detalle.getStock()), String.valueOf(detalle.getPrecio()), StringUtils.parseSpanishDate(detalle.getFechaVencimiento())
            };
            ((javax.swing.table.DefaultTableModel) jtblBoletas.getModel()).addRow(datos);
        }
        FrameUtils.setupWindow(this);
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
        e = new javax.swing.JScrollPane();
        jtblBoletas = new javax.swing.JTable();
        jlblProductName = new com.juancamr.components.Typography();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtblBoletas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ingresado", "Stock", "Precio", "Fecha vencimiento"
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
        jtblBoletas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblBoletasMouseClicked(evt);
            }
        });
        e.setViewportView(jtblBoletas);

        jPanel1.add(e, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 700, 480));

        jlblProductName.setText("typography1");
        jlblProductName.setType(com.juancamr.components.Typography.Type.HEADING1);
        jPanel1.add(jlblProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtblBoletasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblBoletasMouseClicked
       
    }//GEN-LAST:event_jtblBoletasMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane e;
    private javax.swing.JPanel jPanel1;
    private com.juancamr.components.Typography jlblProductName;
    private javax.swing.JTable jtblBoletas;
    // End of variables declaration//GEN-END:variables
}
