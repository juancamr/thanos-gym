/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.uni.thanosgym.view;

/**
 *
 * @author jhere
 */
public class PanelClientBuscar extends javax.swing.JPanel {

    /**
     * Creates new form PanelClient
     */
    public PanelClientBuscar() {
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

        jpnlInterfaz = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtxtDniCliente = new javax.swing.JTextField();
        jbtnBuscarCliente = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jtxtNombreCliente = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel25 = new javax.swing.JLabel();
        jtxtDireccionCliente = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jSeparator19 = new javax.swing.JSeparator();
        jtxtTelefonoCliente = new javax.swing.JTextField();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        jbtnEditar = new javax.swing.JButton();
        jcbxPlanRegistro = new javax.swing.JComboBox<>();
        jPanelEstado = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jtxtPlanActual = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jbtnRenovar = new javax.swing.JButton();
        jbtnCongelar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(840, 690));

        jpnlInterfaz.setBackground(new java.awt.Color(255, 255, 255));
        jpnlInterfaz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnlInterfazMouseClicked(evt);
            }
        });
        jpnlInterfaz.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(252, 252, 252));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(243, 243, 243)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(105, 105, 118));
        jLabel1.setText("DNI :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, 30));

        jtxtDniCliente.setBackground(new java.awt.Color(250, 250, 250));
        jtxtDniCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtxtDniCliente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtxtDniCliente.setBorder(null);
        jtxtDniCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtDniClienteActionPerformed(evt);
            }
        });
        jPanel1.add(jtxtDniCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 210, 30));

        jbtnBuscarCliente.setBackground(new java.awt.Color(255, 255, 254));
        jbtnBuscarCliente.setFont(new java.awt.Font("Malgun Gothic", 1, 12)); // NOI18N
        jbtnBuscarCliente.setForeground(new java.awt.Color(105, 105, 118));
        jbtnBuscarCliente.setText("BUSCAR");
        jPanel1.add(jbtnBuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 100, 30));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 210, 30));

        jLabel2.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(105, 105, 118));
        jLabel2.setText("Nombres y Apellidos");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 160, 30));

        jtxtNombreCliente.setBackground(new java.awt.Color(250, 250, 250));
        jtxtNombreCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtxtNombreCliente.setBorder(null);
        jtxtNombreCliente.setFocusable(false);
        jtxtNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtNombreClienteActionPerformed(evt);
            }
        });
        jPanel1.add(jtxtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 310, 30));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 310, 10));

        jLabel25.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(105, 105, 118));
        jLabel25.setText("Direccion");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 100, 30));

        jtxtDireccionCliente.setBackground(new java.awt.Color(250, 250, 250));
        jtxtDireccionCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtxtDireccionCliente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtxtDireccionCliente.setBorder(null);
        jtxtDireccionCliente.setFocusable(false);
        jtxtDireccionCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtDireccionClienteActionPerformed(evt);
            }
        });
        jPanel1.add(jtxtDireccionCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 310, 30));

        jLabel26.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(105, 105, 118));
        jLabel26.setText("Telefono");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, 80, 30));

        jSeparator19.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 310, 10));

        jtxtTelefonoCliente.setBackground(new java.awt.Color(250, 250, 250));
        jtxtTelefonoCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtxtTelefonoCliente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtxtTelefonoCliente.setBorder(null);
        jtxtTelefonoCliente.setFocusable(false);
        jtxtTelefonoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtTelefonoClienteActionPerformed(evt);
            }
        });
        jPanel1.add(jtxtTelefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 310, 30));

        jSeparator20.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, 310, 10));

        jLabel27.setBackground(new java.awt.Color(105, 105, 118));
        jLabel27.setFont(new java.awt.Font("Malgun Gothic", 0, 24)); // NOI18N
        jLabel27.setText("Busqueda de Cliente");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, -1, -1));

        jbtnEditar.setBackground(new java.awt.Color(20, 23, 31));
        jbtnEditar.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jbtnEditar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnEditar.setText("EDITAR");
        jbtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 590, 160, 40));

        jpnlInterfaz.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 790));

        jcbxPlanRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbxPlanRegistroActionPerformed(evt);
            }
        });
        jpnlInterfaz.add(jcbxPlanRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 360, 120, 30));

        javax.swing.GroupLayout jPanelEstadoLayout = new javax.swing.GroupLayout(jPanelEstado);
        jPanelEstado.setLayout(jPanelEstadoLayout);
        jPanelEstadoLayout.setHorizontalGroup(
            jPanelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelEstadoLayout.setVerticalGroup(
            jPanelEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jpnlInterfaz.add(jPanelEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, 60, 20));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jpnlInterfaz.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 200, 10));

        jLabel3.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(105, 105, 118));
        jLabel3.setText("Nuevo Plan");
        jpnlInterfaz.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 330, 160, 30));

        jtxtPlanActual.setBackground(new java.awt.Color(250, 250, 250));
        jtxtPlanActual.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jtxtPlanActual.setBorder(null);
        jtxtPlanActual.setFocusable(false);
        jtxtPlanActual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtPlanActualActionPerformed(evt);
            }
        });
        jpnlInterfaz.add(jtxtPlanActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, 200, 30));

        jLabel4.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(105, 105, 118));
        jLabel4.setText("Plan Actual");
        jpnlInterfaz.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 230, 160, 30));

        jbtnRenovar.setBackground(new java.awt.Color(20, 23, 31));
        jbtnRenovar.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jbtnRenovar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnRenovar.setText("RENOVAR");
        jbtnRenovar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnRenovarActionPerformed(evt);
            }
        });
        jpnlInterfaz.add(jbtnRenovar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 590, 160, 40));

        jbtnCongelar.setBackground(new java.awt.Color(20, 23, 31));
        jbtnCongelar.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        jbtnCongelar.setForeground(new java.awt.Color(255, 255, 255));
        jbtnCongelar.setText("CONGELAR");
        jbtnCongelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCongelarActionPerformed(evt);
            }
        });
        jpnlInterfaz.add(jbtnCongelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 590, 160, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlInterfaz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlInterfaz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtDniClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtDniClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtDniClienteActionPerformed

    private void jtxtNombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNombreClienteActionPerformed

    private void jbtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnEditarActionPerformed

    private void jtxtDireccionClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtDireccionClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtDireccionClienteActionPerformed

    private void jtxtTelefonoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtTelefonoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtTelefonoClienteActionPerformed

    private void jpnlInterfazMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlInterfazMouseClicked

    }//GEN-LAST:event_jpnlInterfazMouseClicked

    private void jtxtPlanActualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtPlanActualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtPlanActualActionPerformed

    private void jcbxPlanRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbxPlanRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbxPlanRegistroActionPerformed

    private void jbtnCongelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCongelarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnCongelarActionPerformed

    private void jbtnRenovarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnRenovarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnRenovarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanelEstado;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    public javax.swing.JButton jbtnBuscarCliente;
    public javax.swing.JButton jbtnCongelar;
    public javax.swing.JButton jbtnEditar;
    public javax.swing.JButton jbtnRenovar;
    public javax.swing.JComboBox<String> jcbxPlanRegistro;
    private javax.swing.JPanel jpnlInterfaz;
    public javax.swing.JTextField jtxtDireccionCliente;
    public javax.swing.JTextField jtxtDniCliente;
    public javax.swing.JTextField jtxtNombreCliente;
    public javax.swing.JTextField jtxtPlanActual;
    public javax.swing.JTextField jtxtTelefonoCliente;
    // End of variables declaration//GEN-END:variables
}