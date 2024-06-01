/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.auth;

import java.awt.event.KeyEvent;

/**
 *
 * @author jcmro
 */
public class PanelLogin extends javax.swing.JPanel {

    /**
     * Creates new form PanelLogin
     */
    public PanelLogin() {
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
        jbtnIniciar = new javax.swing.JButton();
        jlblExito = new javax.swing.JLabel();
        jtxtNombreUsuario = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPassword = new javax.swing.JPasswordField();
        jbtnRegistro = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(105, 105, 118));
        jLabel14.setText("Nombre de usuario");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, 30));

        jbtnIniciar.setBackground(new java.awt.Color(20, 23, 31));
        jbtnIniciar.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jbtnIniciar.setForeground(new java.awt.Color(241, 240, 243));
        jbtnIniciar.setText("Continuar");
        jbtnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnIniciarActionPerformed(evt);
            }
        });
        jPanel3.add(jbtnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, 300, 40));

        jlblExito.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jlblExito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel3.add(jlblExito, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 650, 310, 40));

        jtxtNombreUsuario.setBackground(new java.awt.Color(241, 241, 241));
        jtxtNombreUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtxtNombreUsuario.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtxtNombreUsuario.setBorder(null);
        jtxtNombreUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtNombreUsuarioActionPerformed(evt);
            }
        });
        jPanel3.add(jtxtNombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 300, 30));

        jSeparator13.setForeground(new java.awt.Color(0, 102, 255));
        jPanel3.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, 180, 30));

        jLabel23.setBackground(new java.awt.Color(105, 105, 118));
        jLabel23.setFont(new java.awt.Font("Malgun Gothic", 0, 24)); // NOI18N
        jLabel23.setText("Inicio de sesión");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));

        jLabel18.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(105, 105, 118));
        jLabel18.setText("Contraseña");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, 30));

        jPassword.setBackground(new java.awt.Color(241, 241, 241));
        jPassword.setBorder(null);
        jPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPasswordKeyReleased(evt);
            }
        });
        jPanel3.add(jPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 300, 30));

        jbtnRegistro.setBackground(new java.awt.Color(255, 255, 254));
        jbtnRegistro.setFont(new java.awt.Font("Malgun Gothic", 0, 12)); // NOI18N
        jbtnRegistro.setForeground(new java.awt.Color(0, 51, 153));
        jbtnRegistro.setText("No tiene una cuenta? Registrate");
        jbtnRegistro.setBorder(null);
        jbtnRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRegistroActionPerformed(evt);
            }
        });
        jPanel3.add(jbtnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 300, 30));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 790));
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtNombreUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNombreUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNombreUsuarioActionPerformed

    private void jbtnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnIniciarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnIniciarActionPerformed

    private void jbtnRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnRegistroActionPerformed

    private void jPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordKeyReleased
        
    }//GEN-LAST:event_jPasswordKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JPasswordField jPassword;
    private javax.swing.JSeparator jSeparator13;
    public javax.swing.JButton jbtnIniciar;
    public javax.swing.JButton jbtnRegistro;
    public javax.swing.JLabel jlblExito;
    public javax.swing.JTextField jtxtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
