/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.uni.thanosgym.view.dialogs;

import com.juancamr.route.DialogPanel;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;


import java.io.File;
import java.util.Map;

/**
 *
 * @author juancamr
 */
public class AgregarUtilidad extends DialogPanel {

    private File imageSelected = null;

    /**
     * Creates new form AgregarUtilidad
     */
    public AgregarUtilidad() {
        initComponents();
        setButtonAction(button);

        setOnAction((Map<String, Object> params) -> {
            params.put("nombre", jtxtNombre.getText());
            params.put("peso", jtxtPeso.getText());
            params.put("cantidad", jtxtPeso.getText());
            params.put("image", imageSelected);
            return params;
        });
    }


    private void chooseImageEvent() {
        Response<File> response = FrameUtils.chooseImage(this);
        if (response.isSuccess()) {
            imageSelected = response.getData();
            jlblFileSelected.setText(imageSelected.getName());
        } else {
            Messages.show(response.getMessage());
            imageSelected = null;
            jlblFileSelected.setText("Imagen");
        }
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
        typography3 = new com.juancamr.components.Typography();
        jtxtPeso = new com.juancamr.components.InputComponent();
        jtxtCantidad = new com.juancamr.components.InputComponent();
        typography4 = new com.juancamr.components.Typography();
        jbtnChooseImage = new com.juancamr.components.ButtonComponent();
        jlblFileSelected = new com.juancamr.components.Typography();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography1.setText("Agregar utilidad");
        typography1.setType(com.juancamr.components.Typography.Type.HEADING2);
        jPanel1.add(typography1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));
        jPanel1.add(jtxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 310, -1));

        typography2.setText("Nombre");
        typography2.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel1.add(typography2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        button.setText("Agregar");
        button.setType(com.juancamr.components.ButtonComponent.Type.PRIMARY);
        jPanel1.add(button, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, 310, 40));

        typography3.setText("Peso");
        typography3.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel1.add(typography3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jtxtPeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtPesoActionPerformed(evt);
            }
        });
        jPanel1.add(jtxtPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 140, -1));
        jPanel1.add(jtxtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 140, -1));

        typography4.setText("Cantidad");
        typography4.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel1.add(typography4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, -1, -1));

        jbtnChooseImage.setText("Seleccionar");
        jbtnChooseImage.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jbtnChooseImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnChooseImageMouseClicked(evt);
            }
        });
        jPanel1.add(jbtnChooseImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 130, -1));

        jlblFileSelected.setText("Imagen");
        jlblFileSelected.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel1.add(jlblFileSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 170, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtPesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtPesoActionPerformed

    private void jbtnChooseImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnChooseImageMouseClicked
        chooseImageEvent();
    }//GEN-LAST:event_jbtnChooseImageMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.juancamr.components.ButtonComponent button;
    private javax.swing.JPanel jPanel1;
    private com.juancamr.components.ButtonComponent jbtnChooseImage;
    private com.juancamr.components.Typography jlblFileSelected;
    private com.juancamr.components.InputComponent jtxtCantidad;
    private com.juancamr.components.InputComponent jtxtNombre;
    private com.juancamr.components.InputComponent jtxtPeso;
    private com.juancamr.components.Typography typography1;
    private com.juancamr.components.Typography typography2;
    private com.juancamr.components.Typography typography3;
    private com.juancamr.components.Typography typography4;
    // End of variables declaration//GEN-END:variables
}