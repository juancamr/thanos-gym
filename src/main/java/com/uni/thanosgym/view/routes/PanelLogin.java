/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.uni.thanosgym.view.routes;

import javax.swing.JTextField;

import com.juancamr.route.Route;
import com.juancamr.route.Router;
import com.uni.thanosgym.controllers.AuthController;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Utils;

/**
 *
 * @author jcmro
 */
@Route("auth:auth/login")
public class PanelLogin extends javax.swing.JPanel {

    /**
     * Creates new form PanelLogin
     */
    public PanelLogin() {
        initComponents();
        FrameUtils.addOnClickEvent(jbtnIniciar, this::loginEvent);
        FrameUtils.addEnterEvent(jPassword, this::loginEvent);
        FrameUtils.addOnClickEvent(jbtnRegistro, () -> {
            Router.getInstance().go("auth/register");
        });
    }

    private void loginEvent() {
        jbtnIniciar.setEnabled(false);
        Utils.mostrarPantallaDeCarga(null, () -> {
            String username = jtxtNombreUsuario.getText();
            String password = new String(jPassword.getPassword());
            boolean persistencia = jCheckSesion.isSelected();
            if (AuthController.login(username, password, persistencia)) {
                FrameUtils.clearInputs(new JTextField[] { jtxtNombreUsuario, jPassword });
            }
            jbtnIniciar.setEnabled(true);
        });
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

        jPanel3 = new javax.swing.JPanel();
        jlblExito = new javax.swing.JLabel();
        jCheckSesion = new javax.swing.JCheckBox();
        typography1 = new com.juancamr.components.Typography();
        jbtnIniciar = new com.juancamr.components.ButtonComponent();
        typography2 = new com.juancamr.components.Typography();
        jtxtNombreUsuario = new com.juancamr.components.InputComponent();
        typography3 = new com.juancamr.components.Typography();
        jPassword = new javax.swing.JPasswordField();
        jbtnRegistro = new com.juancamr.components.ButtonComponent();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblExito.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlblExito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(jlblExito, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 650, 310, 40));

        jCheckSesion.setBackground(new java.awt.Color(255, 255, 255));
        jCheckSesion.setText("Mantener mi sesion iniciada");
        jPanel3.add(jCheckSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        typography1.setText("Inicio de sesión");
        typography1.setType(com.juancamr.components.Typography.Type.HEADING1);
        jPanel3.add(typography1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jbtnIniciar.setText("INICIAR");
        jbtnIniciar.setType(com.juancamr.components.ButtonComponent.Type.PRIMARY);
        jPanel3.add(jbtnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 330, 50));

        typography2.setText("Nombre de usuario");
        typography2.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel3.add(typography2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));
        jPanel3.add(jtxtNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 330, -1));

        typography3.setText("Contraseña");
        typography3.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel3.add(typography3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, -1, -1));
        jPanel3.add(jPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 330, -1));

        jbtnRegistro.setText("Registrate");
        jbtnRegistro.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel3.add(jbtnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, -1, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 690));
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jtxtNombreUsuarioActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jtxtNombreUsuarioActionPerformed

    private void jbtnIniciarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtnIniciarActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnIniciarActionPerformed

    private void jbtnRegistroActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtnRegistroActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnRegistroActionPerformed

    private void jPasswordKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_jPasswordKeyReleased

    }// GEN-LAST:event_jPasswordKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox jCheckSesion;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPassword;
    private com.juancamr.components.ButtonComponent jbtnIniciar;
    private com.juancamr.components.ButtonComponent jbtnRegistro;
    public javax.swing.JLabel jlblExito;
    private com.juancamr.components.InputComponent jtxtNombreUsuario;
    private com.juancamr.components.Typography typography1;
    private com.juancamr.components.Typography typography2;
    private com.juancamr.components.Typography typography3;
    // End of variables declaration//GEN-END:variables
}
