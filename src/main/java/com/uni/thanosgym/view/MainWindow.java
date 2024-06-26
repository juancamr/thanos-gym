package com.uni.thanosgym.view;

import java.awt.Color;

public class MainWindow extends javax.swing.JFrame {
    
    Color base = new Color(20,23,31);
    Color focus = new Color(55, 58, 64);
    
    boolean firstButton = true;
    boolean secondButton = false;
    public boolean thirdButton = false;
    boolean fourthButton = false;
    public boolean fifthButton = false;
    
    public MainWindow() {
        initComponents();
        this.setLocationRelativeTo(this);
        setSize(1060, 690);
    }
    
    public void turnAllFalse() {
        firstButton = false;
        secondButton = false;
        thirdButton = false;
        fourthButton = false;
        fifthButton = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnlBarra = new javax.swing.JPanel();
        jbtnPrimero = new javax.swing.JButton();
        jbtnQuinto = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jlblNombreAdministrador = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jbtnSegundo = new javax.swing.JButton();
        jbtnSexto = new javax.swing.JButton();
        jbtnCuarto = new javax.swing.JButton();
        jbtnTercero = new javax.swing.JButton();
        jbtnCerrarSesion = new javax.swing.JButton();
        content = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(getIconImage());
        setLocation(new java.awt.Point(0, 0));
        setLocationByPlatform(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnlBarra.setBackground(new java.awt.Color(250, 250, 250));
        jpnlBarra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtnPrimero.setBackground(new java.awt.Color(245, 245, 245));
        jbtnPrimero.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        jbtnPrimero.setText("    Primero");
        jbtnPrimero.setBorder(null);
        jbtnPrimero.setFocusable(false);
        jbtnPrimero.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnPrimero.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jbtnPrimeroMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jbtnPrimeroMouseMoved(evt);
            }
        });
        jbtnPrimero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jbtnPrimeroFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jbtnPrimeroFocusLost(evt);
            }
        });
        jbtnPrimero.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jbtnPrimeroMouseWheelMoved(evt);
            }
        });
        jbtnPrimero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnPrimeroMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnPrimeroMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jbtnPrimeroMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jbtnPrimeroMouseReleased(evt);
            }
        });
        jbtnPrimero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPrimeroActionPerformed(evt);
            }
        });
        jpnlBarra.add(jbtnPrimero, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 130, 50));

        jbtnQuinto.setBackground(new java.awt.Color(250, 250, 250));
        jbtnQuinto.setFont(new java.awt.Font("Malgun Gothic", 0, 16)); // NOI18N
        jbtnQuinto.setText("    Quinto");
        jbtnQuinto.setBorder(null);
        jbtnQuinto.setFocusable(false);
        jbtnQuinto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnQuinto.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jbtnQuintoMouseMoved(evt);
            }
        });
        jbtnQuinto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnQuintoMouseExited(evt);
            }
        });
        jbtnQuinto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnQuintoActionPerformed(evt);
            }
        });
        jpnlBarra.add(jbtnQuinto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 130, 50));

        jLabel2.setBackground(new java.awt.Color(241, 240, 243));
        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 26)); // NOI18N
        jLabel2.setText("ThanosGym");
        jpnlBarra.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        jlblNombreAdministrador.setFont(new java.awt.Font("Malgun Gothic", 0, 14)); // NOI18N
        jlblNombreAdministrador.setForeground(new java.awt.Color(102, 102, 102));
        jlblNombreAdministrador.setText("Administrador");
        jpnlBarra.add(jlblNombreAdministrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 580, 140, -1));

        jLabel9.setFont(new java.awt.Font("Malgun Gothic", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("MAIN MENU");
        jpnlBarra.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jbtnSegundo.setBackground(new java.awt.Color(250, 250, 250));
        jbtnSegundo.setFont(new java.awt.Font("Malgun Gothic", 0, 16)); // NOI18N
        jbtnSegundo.setText("    Segundo");
        jbtnSegundo.setBorder(null);
        jbtnSegundo.setFocusable(false);
        jbtnSegundo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnSegundo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jbtnSegundoMouseMoved(evt);
            }
        });
        jbtnSegundo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnSegundoMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnSegundoMouseExited(evt);
            }
        });
        jbtnSegundo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSegundoActionPerformed(evt);
            }
        });
        jpnlBarra.add(jbtnSegundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 130, 50));

        jbtnSexto.setBackground(new java.awt.Color(250, 250, 250));
        jbtnSexto.setFont(new java.awt.Font("Malgun Gothic", 0, 16)); // NOI18N
        jbtnSexto.setText("    Sexto");
        jbtnSexto.setBorder(null);
        jbtnSexto.setFocusable(false);
        jbtnSexto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnSexto.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jbtnSextoMouseMoved(evt);
            }
        });
        jbtnSexto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnSextoMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnSextoMouseExited(evt);
            }
        });
        jbtnSexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSextoActionPerformed(evt);
            }
        });
        jpnlBarra.add(jbtnSexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 130, 50));

        jbtnCuarto.setBackground(new java.awt.Color(250, 250, 250));
        jbtnCuarto.setFont(new java.awt.Font("Malgun Gothic", 0, 16)); // NOI18N
        jbtnCuarto.setText("    Cuarto");
        jbtnCuarto.setBorder(null);
        jbtnCuarto.setFocusable(false);
        jbtnCuarto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnCuarto.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jbtnCuartoMouseMoved(evt);
            }
        });
        jbtnCuarto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnCuartoMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnCuartoMouseExited(evt);
            }
        });
        jbtnCuarto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCuartoActionPerformed(evt);
            }
        });
        jpnlBarra.add(jbtnCuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 130, 50));

        jbtnTercero.setBackground(new java.awt.Color(250, 250, 250));
        jbtnTercero.setFont(new java.awt.Font("Malgun Gothic", 0, 16)); // NOI18N
        jbtnTercero.setText("    Tercero");
        jbtnTercero.setBorder(null);
        jbtnTercero.setFocusable(false);
        jbtnTercero.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnTercero.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jbtnTerceroMouseMoved(evt);
            }
        });
        jbtnTercero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnTerceroMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnTerceroMouseExited(evt);
            }
        });
        jbtnTercero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnTerceroActionPerformed(evt);
            }
        });
        jpnlBarra.add(jbtnTercero, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 130, 50));

        jbtnCerrarSesion.setBackground(new java.awt.Color(250, 250, 250));
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
        jpnlBarra.add(jbtnCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 600, 110, 30));

        getContentPane().add(jpnlBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 690));

        content.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 840, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnPrimeroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jbtnPrimeroFocusGained
        
    }//GEN-LAST:event_jbtnPrimeroFocusGained

    private void jbtnPrimeroMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnPrimeroMouseDragged

    }//GEN-LAST:event_jbtnPrimeroMouseDragged

    private void jbtnPrimeroMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnPrimeroMouseMoved
    }//GEN-LAST:event_jbtnPrimeroMouseMoved

    private void jbtnPrimeroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jbtnPrimeroFocusLost
        //
    }//GEN-LAST:event_jbtnPrimeroFocusLost

    private void jbtnPrimeroMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jbtnPrimeroMouseWheelMoved
        
    }//GEN-LAST:event_jbtnPrimeroMouseWheelMoved

    private void jbtnPrimeroMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnPrimeroMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnPrimeroMouseReleased

    private void jbtnPrimeroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnPrimeroMouseExited
    }//GEN-LAST:event_jbtnPrimeroMouseExited

    private void jbtnQuintoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnQuintoMouseExited
    }//GEN-LAST:event_jbtnQuintoMouseExited

    private void jbtnQuintoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnQuintoMouseMoved
    }//GEN-LAST:event_jbtnQuintoMouseMoved

    private void jbtnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {
    }                                           

    private void jbtnQuintoActionPerformed(java.awt.event.ActionEvent evt) {
    }                                          

    private void jbtnPrimeroMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnPrimeroMousePressed
        
    }//GEN-LAST:event_jbtnPrimeroMousePressed

    private void jbtnPrimeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnPrimeroMouseClicked
        
    }//GEN-LAST:event_jbtnPrimeroMouseClicked

    private void jbtnSegundoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnSegundoMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnSegundoMouseMoved

    private void jbtnSegundoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnSegundoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnSegundoMouseClicked

    private void jbtnSegundoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnSegundoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnSegundoMouseExited

    private void jbtnSegundoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSegundoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnSegundoActionPerformed

    private void jbtnSextoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnSextoMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnSextoMouseMoved

    private void jbtnSextoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnSextoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnSextoMouseClicked

    private void jbtnSextoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnSextoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnSextoMouseExited

    private void jbtnSextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSextoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnSextoActionPerformed

    private void jbtnCuartoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnCuartoMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnCuartoMouseMoved

    private void jbtnCuartoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnCuartoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnCuartoMouseClicked

    private void jbtnCuartoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnCuartoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnCuartoMouseExited

    private void jbtnCuartoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCuartoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnCuartoActionPerformed

    private void jbtnTerceroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnTerceroMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnTerceroMouseClicked

    private void jbtnTerceroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnTerceroMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnTerceroMouseExited

    private void jbtnTerceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnTerceroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnTerceroActionPerformed

    private void jbtnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCerrarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnCerrarSesionActionPerformed

    private void jbtnTerceroMouseMoved(java.awt.event.MouseEvent evt) {
    }                                      

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel content;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JButton jbtnCerrarSesion;
    public javax.swing.JButton jbtnCuarto;
    public javax.swing.JButton jbtnPrimero;
    public javax.swing.JButton jbtnQuinto;
    public javax.swing.JButton jbtnSegundo;
    public javax.swing.JButton jbtnSexto;
    public javax.swing.JButton jbtnTercero;
    public javax.swing.JLabel jlblNombreAdministrador;
    private javax.swing.JPanel jpnlBarra;
    // End of variables declaration//GEN-END:variables
}
