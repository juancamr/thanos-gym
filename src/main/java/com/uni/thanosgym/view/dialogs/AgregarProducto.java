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
        typography3 = new com.juancamr.components.Typography();
        jtxtNombre1 = new com.juancamr.components.InputComponent();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography1.setText("Crear producto");
        typography1.setType(com.juancamr.components.Typography.Type.HEADING2);
        jPanel1.add(typography1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));
        jPanel1.add(jtxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 310, -1));

        typography2.setText("Nombre");
        typography2.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel1.add(typography2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        button.setText("Agregar");
        button.setType(com.juancamr.components.ButtonComponent.Type.PRIMARY);
        jPanel1.add(button, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 310, 40));

        typography3.setText("Código");
        typography3.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel1.add(typography3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));
        jPanel1.add(jtxtNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 310, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.juancamr.components.ButtonComponent button;
    private javax.swing.JPanel jPanel1;
    private com.juancamr.components.InputComponent jtxtNombre;
    private com.juancamr.components.InputComponent jtxtNombre1;
    private com.juancamr.components.Typography typography1;
    private com.juancamr.components.Typography typography2;
    private com.juancamr.components.Typography typography3;
    // End of variables declaration//GEN-END:variables
}
