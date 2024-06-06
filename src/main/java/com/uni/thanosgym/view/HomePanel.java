package com.uni.thanosgym.view;

public class HomePanel extends javax.swing.JPanel {

    /**
     * Creates new form Home
     */
    public HomePanel() {
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
        jPanel4 = new javax.swing.JPanel();
        jbtnAgregarClient = new javax.swing.JButton();
        jbtnAgregarPlan = new javax.swing.JButton();
        planesListPanel = new javax.swing.JPanel();
        jlblNombreAdministrador = new javax.swing.JLabel();
        jbtnCerrarSesion = new javax.swing.JButton();

        jpnlInterfaz.setBackground(new java.awt.Color(255, 255, 255));
        jpnlInterfaz.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpnlInterfazMouseClicked(evt);
            }
        });
        jpnlInterfaz.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(243, 243, 243)));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });
        jPanel4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel4KeyPressed(evt);
            }
        });
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtnAgregarClient.setBackground(new java.awt.Color(254, 254, 254));
        jbtnAgregarClient.setText("Agregar Cliente");
        jPanel4.add(jbtnAgregarClient, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 70, 180, 60));

        jbtnAgregarPlan.setBackground(new java.awt.Color(254, 254, 254));
        jbtnAgregarPlan.setText("Agregar Plan");
        jbtnAgregarPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAgregarPlanActionPerformed(evt);
            }
        });
        jPanel4.add(jbtnAgregarPlan, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 180, 60));

        planesListPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout planesListPanelLayout = new javax.swing.GroupLayout(planesListPanel);
        planesListPanel.setLayout(planesListPanelLayout);
        planesListPanelLayout.setHorizontalGroup(
            planesListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        planesListPanelLayout.setVerticalGroup(
            planesListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE)
        );

        jPanel4.add(planesListPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 200, 530));

        jpnlInterfaz.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 840, 700));

        jlblNombreAdministrador.setFont(new java.awt.Font("Malgun Gothic", 0, 16)); // NOI18N
        jlblNombreAdministrador.setText("Administrador");
        jpnlInterfaz.add(jlblNombreAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 150, 40));

        jbtnCerrarSesion.setBackground(new java.awt.Color(255, 255, 254));
        jbtnCerrarSesion.setFont(new java.awt.Font("Malgun Gothic", 0, 12)); // NOI18N
        jbtnCerrarSesion.setForeground(new java.awt.Color(204, 0, 51));
        jbtnCerrarSesion.setText("Cerrar sesión");
        jbtnCerrarSesion.setBorder(null);
        jbtnCerrarSesion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCerrarSesionActionPerformed(evt);
            }
        });
        jpnlInterfaz.add(jbtnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 150, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlInterfaz, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnlInterfaz, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jpnlInterfazMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpnlInterfazMouseClicked

    }//GEN-LAST:event_jpnlInterfazMouseClicked

    private void jPanel4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel4KeyPressed
        //
    }//GEN-LAST:event_jPanel4KeyPressed

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jbtnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCerrarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnCerrarSesionActionPerformed

    private void jbtnAgregarPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAgregarPlanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnAgregarPlanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel4;
    public javax.swing.JButton jbtnAgregarClient;
    public javax.swing.JButton jbtnAgregarPlan;
    public javax.swing.JButton jbtnCerrarSesion;
    public javax.swing.JLabel jlblNombreAdministrador;
    private javax.swing.JPanel jpnlInterfaz;
    public javax.swing.JPanel planesListPanel;
    // End of variables declaration//GEN-END:variables
}
