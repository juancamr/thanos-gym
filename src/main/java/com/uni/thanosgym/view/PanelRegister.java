/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.uni.thanosgym.view;

/**
 *
 * @author jcmro
 */
public class PanelRegister extends javax.swing.JPanel {

    /**
     * Creates new form PanelRegister
     */
    public PanelRegister() {
        initComponents();
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
        jLabel14 = new javax.swing.JLabel();
        jbtnRegistro = new javax.swing.JButton();
        jlblExito = new javax.swing.JLabel();
        jtxtNombreUsuario = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPassword = new javax.swing.JPasswordField();
        jLabel15 = new javax.swing.JLabel();
        jtxtNombresCompletos = new javax.swing.JTextField();
        jbtnInicioSesion = new javax.swing.JButton();
        jtxtCorreo = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jtxtPhone = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jtxtRepeatPassword = new javax.swing.JPasswordField();
        jCheckIsForMaster = new javax.swing.JCheckBox();

        setPreferredSize(new java.awt.Dimension(420, 690));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(105, 105, 118));
        jLabel14.setText("Nombre de usuario *");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, 30));

        jbtnRegistro.setBackground(new java.awt.Color(20, 23, 31));
        jbtnRegistro.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jbtnRegistro.setForeground(new java.awt.Color(241, 240, 243));
        jbtnRegistro.setText("Registrarme");
        jbtnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRegistroActionPerformed(evt);
            }
        });
        jPanel3.add(jbtnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 620, 300, 40));

        jlblExito.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlblExito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(jlblExito, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 670, 310, 40));

        jtxtNombreUsuario.setBackground(new java.awt.Color(241, 241, 241));
        jtxtNombreUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtxtNombreUsuario.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtxtNombreUsuario.setBorder(null);
        jtxtNombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtNombreUsuarioActionPerformed(evt);
            }
        });
        jPanel3.add(jtxtNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 300, 30));

        jLabel23.setBackground(new java.awt.Color(105, 105, 118));
        jLabel23.setFont(new java.awt.Font("Malgun Gothic", 0, 24)); // NOI18N
        jLabel23.setText("Registro");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        jLabel18.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(105, 105, 118));
        jLabel18.setText("Contraseña *");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, -1, 30));

        jPassword.setBackground(new java.awt.Color(241, 241, 241));
        jPassword.setBorder(null);
        jPanel3.add(jPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 300, 30));

        jLabel15.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(105, 105, 118));
        jLabel15.setText("Nombres *");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, 30));

        jtxtNombresCompletos.setBackground(new java.awt.Color(241, 241, 241));
        jtxtNombresCompletos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtxtNombresCompletos.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtxtNombresCompletos.setBorder(null);
        jtxtNombresCompletos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtNombresCompletosActionPerformed(evt);
            }
        });
        jPanel3.add(jtxtNombresCompletos, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 300, 30));

        jbtnInicioSesion.setBackground(new java.awt.Color(255, 255, 254));
        jbtnInicioSesion.setFont(new java.awt.Font("Malgun Gothic", 0, 12)); // NOI18N
        jbtnInicioSesion.setForeground(new java.awt.Color(0, 51, 153));
        jbtnInicioSesion.setText("Inicia sesión");
        jbtnInicioSesion.setBorder(null);
        jbtnInicioSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnInicioSesionActionPerformed(evt);
            }
        });
        jPanel3.add(jbtnInicioSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 560, 100, 40));

        jtxtCorreo.setBackground(new java.awt.Color(241, 241, 241));
        jtxtCorreo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtxtCorreo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtxtCorreo.setBorder(null);
        jtxtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtCorreoActionPerformed(evt);
            }
        });
        jPanel3.add(jtxtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 300, 30));

        jLabel16.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(105, 105, 118));
        jLabel16.setText("Correo *");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, 30));

        jtxtPhone.setBackground(new java.awt.Color(241, 241, 241));
        jtxtPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtxtPhone.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtxtPhone.setBorder(null);
        jtxtPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtPhoneActionPerformed(evt);
            }
        });
        jPanel3.add(jtxtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 300, 30));

        jLabel17.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(105, 105, 118));
        jLabel17.setText("Celular");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, 30));

        jLabel19.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(105, 105, 118));
        jLabel19.setText("Repetir Contraseña *");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, -1, 30));

        jtxtRepeatPassword.setBackground(new java.awt.Color(241, 241, 241));
        jtxtRepeatPassword.setBorder(null);
        jPanel3.add(jtxtRepeatPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 520, 300, 30));

        jCheckIsForMaster.setBackground(new java.awt.Color(255, 255, 255));
        jCheckIsForMaster.setText("Master");
        jPanel3.add(jCheckIsForMaster, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 570, -1, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnRegistroActionPerformed

    private void jtxtNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNombreUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNombreUsuarioActionPerformed

    private void jtxtNombresCompletosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNombresCompletosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNombresCompletosActionPerformed

    private void jbtnInicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnInicioSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnInicioSesionActionPerformed

    private void jtxtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtCorreoActionPerformed

    private void jtxtPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtPhoneActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox jCheckIsForMaster;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JPasswordField jPassword;
    public javax.swing.JButton jbtnInicioSesion;
    public javax.swing.JButton jbtnRegistro;
    public javax.swing.JLabel jlblExito;
    public javax.swing.JTextField jtxtCorreo;
    public javax.swing.JTextField jtxtNombreUsuario;
    public javax.swing.JTextField jtxtNombresCompletos;
    public javax.swing.JTextField jtxtPhone;
    public javax.swing.JPasswordField jtxtRepeatPassword;
    // End of variables declaration//GEN-END:variables
}
