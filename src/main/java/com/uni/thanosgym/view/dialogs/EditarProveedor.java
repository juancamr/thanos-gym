/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.uni.thanosgym.view.dialogs;

import com.juancamr.route.DialogPanel;

/**
 *
 * @author juancamr
 */
public class EditarProveedor extends DialogPanel {

    /**
     * Creates new form EditarProveedor
     */
    public EditarProveedor() {
        initComponents();
        setButtonAction(button);
        setOnAction(params -> {
            params.put("nombre", jtxtNombre.getText());
            params.put("ruc", jtxtRUC.getText());
            params.put("direccion", jtxtDireccion.getText());
            params.put("telefono", jtxtTelefono.getText());
            return params;
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        typography7 = new com.juancamr.components.Typography();
        jtxtRUC = new com.juancamr.components.InputComponent();
        typography8 = new com.juancamr.components.Typography();
        button = new com.juancamr.components.ButtonComponent();
        jtxtNombre = new com.juancamr.components.InputComponent();
        typography11 = new com.juancamr.components.Typography();
        jtxtDireccion = new com.juancamr.components.InputComponent();
        typography12 = new com.juancamr.components.Typography();
        typography13 = new com.juancamr.components.Typography();
        jtxtTelefono = new com.juancamr.components.InputComponent();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography7.setText("Editar proveedor");
        typography7.setType(com.juancamr.components.Typography.Type.HEADING2);
        jPanel3.add(typography7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));
        jPanel3.add(jtxtRUC, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 310, -1));

        typography8.setText("RUC");
        typography8.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel3.add(typography8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        button.setText("Editar");
        button.setType(com.juancamr.components.ButtonComponent.Type.PRIMARY);
        jPanel3.add(button, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 470, 310, 40));

        jtxtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtNombreActionPerformed(evt);
            }
        });
        jPanel3.add(jtxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 310, -1));

        typography11.setText("Nombre");
        typography11.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel3.add(typography11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jtxtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtDireccionActionPerformed(evt);
            }
        });
        jPanel3.add(jtxtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 310, -1));

        typography12.setText("Dirección");
        typography12.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel3.add(typography12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        typography13.setText("Teléfono");
        typography13.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel3.add(typography13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        jtxtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtTelefonoActionPerformed(evt);
            }
        });
        jPanel3.add(jtxtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 310, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 412,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 560,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)));
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtNombreActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jtxtNombreActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jtxtNombreActionPerformed

    private void jtxtDireccionActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jtxtDireccionActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jtxtDireccionActionPerformed

    private void jtxtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jtxtTelefonoActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jtxtTelefonoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.juancamr.components.ButtonComponent button;
    private javax.swing.JPanel jPanel3;
    private com.juancamr.components.InputComponent jtxtDireccion;
    private com.juancamr.components.InputComponent jtxtNombre;
    private com.juancamr.components.InputComponent jtxtRUC;
    private com.juancamr.components.InputComponent jtxtTelefono;
    private com.juancamr.components.Typography typography11;
    private com.juancamr.components.Typography typography12;
    private com.juancamr.components.Typography typography13;
    private com.juancamr.components.Typography typography7;
    private com.juancamr.components.Typography typography8;
    // End of variables declaration//GEN-END:variables
}
