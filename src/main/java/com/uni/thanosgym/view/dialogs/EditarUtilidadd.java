/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.uni.thanosgym.view.dialogs;

import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.dao.CRUDUtilidad;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.model.Utility;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.StringUtils;

/**
 *
 * @author juancamr
 */
public class EditarUtilidadd extends javax.swing.JFrame {

    private Utility utilidad;

    /**
     * Creates new form EditarUtilidadd
     */
    public EditarUtilidadd(Utility utilidad) {
        this.utilidad = utilidad;
        initComponents();
        jtxtNombre2.setText(utilidad.getNombre());
        jtxtPeso2.setText(String.valueOf(utilidad.getPeso()));
        jtxtCantidad.setText(String.valueOf(utilidad.getCantidad()));
        FrameUtils.addOnClickEvent(button, this::editar);
        FrameUtils.setupWindow(this);
        setAlwaysOnTop(true);
    }

    private void editar() {
        String nombre = jtxtNombre2.getText();
        String peso = jtxtPeso2.getText();
        String cantidad = jtxtCantidad.getText();
        if (nombre.isEmpty() || peso.isEmpty() || cantidad.isEmpty()) {
            Messages.show("Complete todos los campos");
            return;
        }

        if (!StringUtils.isDecimal(peso)) {
            Messages.show("El peso debe ser un número");
            return;
        }

        if (!StringUtils.isInteger(cantidad)) {
            Messages.show("El cantidad debe ser un número");
            return;
        }


        double pesoD = Double.parseDouble(peso);
        utilidad.setNombre(nombre);
        utilidad.setPeso(pesoD);
        utilidad.setCantidad(Integer.parseInt(cantidad));
        Response<Utility> response = CRUDUtilidad.getInstance().update(utilidad);
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return;
        }
        dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        typography7 = new com.juancamr.components.Typography();
        jtxtNombre2 = new com.juancamr.components.InputComponent();
        typography8 = new com.juancamr.components.Typography();
        button = new com.juancamr.components.ButtonComponent();
        jtxtPeso2 = new com.juancamr.components.InputComponent();
        jtxtCantidad = new com.juancamr.components.InputComponent();
        typography10 = new com.juancamr.components.Typography();
        typography11 = new com.juancamr.components.Typography();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography7.setText("Editar utilidad");
        typography7.setType(com.juancamr.components.Typography.Type.HEADING2);
        jPanel3.add(typography7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));
        jPanel3.add(jtxtNombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 310, -1));

        typography8.setText("Nombre");
        typography8.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel3.add(typography8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        button.setText("Editar");
        button.setType(com.juancamr.components.ButtonComponent.Type.PRIMARY);
        jPanel3.add(button, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 310, 40));

        jtxtPeso2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtPeso2ActionPerformed(evt);
            }
        });
        jPanel3.add(jtxtPeso2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 140, -1));
        jPanel3.add(jtxtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 140, -1));

        typography10.setText("Cantidad");
        typography10.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel3.add(typography10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, -1, -1));

        typography11.setText("Peso");
        typography11.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel3.add(typography11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtPeso2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtPeso2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtPeso2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.juancamr.components.ButtonComponent button;
    private javax.swing.JPanel jPanel3;
    private com.juancamr.components.InputComponent jtxtCantidad;
    private com.juancamr.components.InputComponent jtxtNombre2;
    private com.juancamr.components.InputComponent jtxtPeso2;
    private com.juancamr.components.Typography typography10;
    private com.juancamr.components.Typography typography11;
    private com.juancamr.components.Typography typography7;
    private com.juancamr.components.Typography typography8;
    // End of variables declaration//GEN-END:variables
}
