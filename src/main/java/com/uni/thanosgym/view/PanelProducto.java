/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.uni.thanosgym.view;

import com.uni.thanosgym.controller.ControladorProducto;
import com.uni.thanosgym.model.Producto;

/**
 *
 * @author juancamr
 */
public class PanelProducto extends javax.swing.JPanel {

    /**
     * Creates new form PanelProducto
     */
    public PanelProducto() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jtxtBusquedaProducto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblProducto = new javax.swing.JTable();
        jbtnCrear = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtxtBusquedaProducto.setBackground(new java.awt.Color(250, 250, 250));
        jtxtBusquedaProducto.setBorder(null);
        jPanel1.add(jtxtBusquedaProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 330, 40));

        jLabel2.setText("Busqueda");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, -1));

        jtblProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtblProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblProducto);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, 630, 450));

        jbtnCrear.setBackground(new java.awt.Color(254, 254, 254));
        jbtnCrear.setText("Crear");
        jPanel1.add(jbtnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 140, 40));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 690));
    }// </editor-fold>//GEN-END:initComponents

    private void jtblProductoMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jtblProductoMouseClicked
        int fila = jtblProducto.getSelectedRow();
        if (fila != -1) {
            int idProducto = Integer.parseInt(String.valueOf(jtblProducto.getValueAt(fila, 0)));
            String nombre = String.valueOf(jtblProducto.getValueAt(fila, 1));
            int cantidad = Integer.parseInt(String.valueOf(jtblProducto.getValueAt(fila, 2)));
            double precio = Double.parseDouble(String.valueOf(jtblProducto.getValueAt(fila, 3)));
            Producto producto = new Producto.Builder()
                    .setId(idProducto)
                    .setNombre(nombre)
                    .setCantidad(cantidad)
                    .setPrecio(precio)
                    .build();
            ControladorProducto.showOptions(producto);
        }

    }// GEN-LAST:event_jtblProductoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JButton jbtnCrear;
    public javax.swing.JTable jtblProducto;
    public javax.swing.JTextField jtxtBusquedaProducto;
    // End of variables declaration//GEN-END:variables
}
