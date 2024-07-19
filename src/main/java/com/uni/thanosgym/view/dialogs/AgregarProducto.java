/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.uni.thanosgym.view.dialogs;


import java.util.Map;
import com.juancamr.route.DialogPanel;
import com.toedter.calendar.JDateChooser;

/**
 *
 * @author juancamr
 */
public class AgregarProducto extends DialogPanel {

    /**
     * Creates new form AgregarProducto
     */
    public AgregarProducto() {
        initComponents();
        setButtonAction(button);

        setOnAction((Map<String, Object> params) -> {
            params.put("codigo", jtxtNombre1.getText());
            params.put("nombre", jtxtNombre.getText());
            params.put("precio", jtxtPrecio.getText());
            params.put("cantidad", jtxtCantidad.getText());
            params.put("fechaVencimiento", dateChooser.getDate());
            return params;
        });
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
        typography1 = new com.juancamr.components.Typography();
        jtxtNombre = new com.juancamr.components.InputComponent();
        typography2 = new com.juancamr.components.Typography();
        button = new com.juancamr.components.ButtonComponent();
        jtxtPrecio = new com.juancamr.components.InputComponent();
        jtxtCantidad = new com.juancamr.components.InputComponent();
        typography4 = new com.juancamr.components.Typography();
        typography5 = new com.juancamr.components.Typography();
        typography3 = new com.juancamr.components.Typography();
        jtxtNombre1 = new com.juancamr.components.InputComponent();
        dateChooser = new com.toedter.calendar.JDateChooser();
        typography6 = new com.juancamr.components.Typography();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography1.setText("Agregar producto");
        typography1.setType(com.juancamr.components.Typography.Type.HEADING2);
        jPanel1.add(typography1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));
        jPanel1.add(jtxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 310, -1));

        typography2.setText("Nombre");
        typography2.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel1.add(typography2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        button.setText("Agregar");
        button.setType(com.juancamr.components.ButtonComponent.Type.PRIMARY);
        jPanel1.add(button, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 310, 40));

        jtxtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtPrecioActionPerformed(evt);
            }
        });
        jPanel1.add(jtxtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 140, -1));
        jPanel1.add(jtxtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 140, -1));

        typography4.setText("Cantidad");
        typography4.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel1.add(typography4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, -1, -1));

        typography5.setText("Fecha de vencimiento");
        typography5.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel1.add(typography5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        typography3.setText("Código");
        typography3.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel1.add(typography3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));
        jPanel1.add(jtxtNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 310, -1));
        jPanel1.add(dateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 310, -1));

        typography6.setText("Precio");
        typography6.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel1.add(typography6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtPrecioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.juancamr.components.ButtonComponent button;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JPanel jPanel1;
    private com.juancamr.components.InputComponent jtxtCantidad;
    private com.juancamr.components.InputComponent jtxtNombre;
    private com.juancamr.components.InputComponent jtxtNombre1;
    private com.juancamr.components.InputComponent jtxtPrecio;
    private com.juancamr.components.Typography typography1;
    private com.juancamr.components.Typography typography2;
    private com.juancamr.components.Typography typography3;
    private com.juancamr.components.Typography typography4;
    private com.juancamr.components.Typography typography5;
    private com.juancamr.components.Typography typography6;
    // End of variables declaration//GEN-END:variables
}