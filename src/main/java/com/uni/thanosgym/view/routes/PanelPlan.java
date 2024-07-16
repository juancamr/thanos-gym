package com.uni.thanosgym.view.routes;

import com.juancamr.route.Route;

@Route("plan")
public class PanelPlan extends javax.swing.JPanel {

    /**
     * Creates new form Home
     */
    public PanelPlan() {
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
        jbtnAgregarPlan = new javax.swing.JButton();
        planesListPanel = new javax.swing.JPanel();

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

        jPanel4.add(planesListPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 200, 530));

        jpnlInterfaz.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 790));

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

    private void jbtnAgregarPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAgregarPlanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnAgregarPlanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel4;
    public javax.swing.JButton jbtnAgregarPlan;
    private javax.swing.JPanel jpnlInterfaz;
    public javax.swing.JPanel planesListPanel;
    // End of variables declaration//GEN-END:variables
}
