/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.uni.thanosgym.view.layouts;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import com.juancamr.route.Layout;
import com.juancamr.route.LayoutPanel;
import com.juancamr.route.Router;
import com.uni.thanosgym.config.Theme;
import com.uni.thanosgym.dao.CRUDPlan;
import com.uni.thanosgym.model.Admin;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Auth;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.UserPreferences;

/**
 *
 * @author juancamr
 */
@Layout("main")
public class MainLayout extends LayoutPanel {

    static Color baseColor = Theme.colors.blue;
    static Color focusColor = Theme.colors.blueCenizo;
    static Font fontButtonMenu = Theme.getMainFont(Font.PLAIN, 15);

    /**
     * Creates new form MainLayout
     */
    public MainLayout() {
        initComponents();
        setContent(content);
        jpnlBarra.setBackground(baseColor);

        String userExample = "https://cdn-icons-png.flaticon.com/512/219/219970.png";
        FrameUtils.renderImageFromWeb(userExample, photoAdmin);
        jlblNombreAdmin.setText(UserPreferences.getData().getFullName());
        JButton[] buttons = new JButton[] { jbtnPrimero, jbtnSegundo, jbtnTercero, jbtnCuarto, jbtnQuinto, jbtnSexto, jbtnSeptimo, jbtnOctavo };
        String[] labels = { "Dashboard", "Planes", "Clientes", "Productos", "Generar venta", "Utilidades", "Proveedores", "Reportes" };
        String[] routes = { "dashboard", "plan", "client", "producto", "venta", "utilidad", "proveedor", "reporte" };

        for (int i = 0; i < buttons.length; i++) {
            JButton button = buttons[i];
            button.setText("    " + labels[i]);
            button.setFont(fontButtonMenu);
            button.setBackground(baseColor);
            int finalI = i;
            FrameUtils.addOnClickEvent(button, () -> {
                setFocusButton(button);
                Router.getInstance().go(routes[finalI]);
            });
        }
        jbtnPrimero.setBackground(focusColor);
        if (!UserPreferences.getData().getRol().equals(Admin.Rol.MASTER)) {
            jbtnSegundo.setVisible(false);
            jbtnOctavo.setVisible(false);
        }
        FrameUtils.removeAllEvents(jbtnTercero);
        FrameUtils.addOnClickEvent(jbtnTercero, () -> {
            Response<Plan> res = CRUDPlan.getInstance().getAll();
            if (res.getDataList().isEmpty()) {
                Messages.show("Por favor, primero registre planes");
                Router.getInstance().go("dashboard");
            } else {
                setFocusButton(jbtnTercero);
                Router.getInstance().go("client");
            }
        });

    }

    private void quitarFondosBotones(JButton[] botones) {
        for (JButton boton : botones) {
            boton.setBackground(baseColor);
        }
    }

    private void setFocusButton(JButton boton) {
        quitarFondosBotones(new JButton[] { jbtnPrimero, jbtnSegundo, jbtnTercero, jbtnCuarto,
                jbtnQuinto, jbtnSeptimo });
        boton.setBackground(focusColor);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jpnlBarra = new javax.swing.JPanel();
        jbtnPrimero = new javax.swing.JButton();
        jbtnQuinto = new javax.swing.JButton();
        jbtnSegundo = new javax.swing.JButton();
        jbtnSeptimo = new javax.swing.JButton();
        jbtnCuarto = new javax.swing.JButton();
        jbtnTercero = new javax.swing.JButton();
        typography1 = new com.juancamr.components.Typography();
        jlblNombreAdmin = new com.juancamr.components.Typography();
        photoAdmin = new javax.swing.JLabel();
        signout = new com.juancamr.components.Typography();
        jbtnSexto = new javax.swing.JButton();
        jbtnOctavo = new javax.swing.JButton();
        content = new javax.swing.JPanel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpnlBarra.setBackground(new java.awt.Color(0, 0, 0));
        jpnlBarra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jbtnPrimero.setBackground(new java.awt.Color(0, 0, 0));
        jbtnPrimero.setFont(new java.awt.Font("Malgun Gothic", 1, 16)); // NOI18N
        jbtnPrimero.setForeground(new java.awt.Color(255, 255, 255));
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
        jpnlBarra.add(jbtnPrimero, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 180, 34));

        jbtnQuinto.setBackground(new java.awt.Color(0, 0, 0));
        jbtnQuinto.setFont(new java.awt.Font("Malgun Gothic", 0, 16)); // NOI18N
        jbtnQuinto.setForeground(new java.awt.Color(255, 255, 255));
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
        jpnlBarra.add(jbtnQuinto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 180, 34));

        jbtnSegundo.setBackground(new java.awt.Color(0, 0, 0));
        jbtnSegundo.setFont(new java.awt.Font("Malgun Gothic", 0, 16)); // NOI18N
        jbtnSegundo.setForeground(new java.awt.Color(255, 255, 255));
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
        jpnlBarra.add(jbtnSegundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 180, 34));

        jbtnSeptimo.setBackground(new java.awt.Color(0, 0, 0));
        jbtnSeptimo.setFont(new java.awt.Font("Malgun Gothic", 0, 16)); // NOI18N
        jbtnSeptimo.setForeground(new java.awt.Color(255, 255, 255));
        jbtnSeptimo.setText("    Septimo");
        jbtnSeptimo.setBorder(null);
        jbtnSeptimo.setFocusable(false);
        jbtnSeptimo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jpnlBarra.add(jbtnSeptimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 180, 34));

        jbtnCuarto.setBackground(new java.awt.Color(0, 0, 0));
        jbtnCuarto.setFont(new java.awt.Font("Malgun Gothic", 0, 16)); // NOI18N
        jbtnCuarto.setForeground(new java.awt.Color(255, 255, 255));
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
        jpnlBarra.add(jbtnCuarto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 180, 34));

        jbtnTercero.setBackground(new java.awt.Color(0, 0, 0));
        jbtnTercero.setFont(new java.awt.Font("Malgun Gothic", 0, 16)); // NOI18N
        jbtnTercero.setForeground(new java.awt.Color(255, 255, 255));
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
        jpnlBarra.add(jbtnTercero, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 180, 34));

        typography1.setForeground(new java.awt.Color(255, 255, 255));
        typography1.setText("ThanosGym");
        typography1.setType(com.juancamr.components.Typography.Type.HEADING2);
        jpnlBarra.add(typography1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jlblNombreAdmin.setForeground(new java.awt.Color(255, 255, 255));
        jlblNombreAdmin.setText("Nombre");
        jlblNombreAdmin.setType(com.juancamr.components.Typography.Type.SMALL);
        jpnlBarra.add(jlblNombreAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 620, -1, -1));

        photoAdmin.setText("jLabel1");
        jpnlBarra.add(photoAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 35, 35));

        signout.setForeground(new java.awt.Color(255, 51, 51));
        signout.setText("Cerrar sesión");
        signout.setType(com.juancamr.components.Typography.Type.SMALL);
        signout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signoutMouseClicked(evt);
            }
        });
        jpnlBarra.add(signout, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 640, -1, -1));

        jbtnSexto.setBackground(new java.awt.Color(0, 0, 0));
        jbtnSexto.setFont(new java.awt.Font("Malgun Gothic", 0, 16)); // NOI18N
        jbtnSexto.setForeground(new java.awt.Color(255, 255, 255));
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
        jpnlBarra.add(jbtnSexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 180, 34));

        jbtnOctavo.setBackground(new java.awt.Color(0, 0, 0));
        jbtnOctavo.setFont(new java.awt.Font("Malgun Gothic", 0, 16)); // NOI18N
        jbtnOctavo.setForeground(new java.awt.Color(255, 255, 255));
        jbtnOctavo.setText("    Octavo");
        jbtnOctavo.setBorder(null);
        jbtnOctavo.setFocusable(false);
        jbtnOctavo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jbtnOctavo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jbtnOctavoMouseMoved(evt);
            }
        });
        jbtnOctavo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnOctavoMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jbtnOctavoMouseExited(evt);
            }
        });
        jbtnOctavo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnOctavoActionPerformed(evt);
            }
        });
        jpnlBarra.add(jbtnOctavo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 180, 34));

        jPanel1.add(jpnlBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 690));

        content.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 840, 690));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 690));
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnOctavoMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnOctavoMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnOctavoMouseMoved

    private void jbtnOctavoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnOctavoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnOctavoMouseClicked

    private void jbtnOctavoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jbtnOctavoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnOctavoMouseExited

    private void jbtnOctavoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnOctavoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnOctavoActionPerformed



    private void signoutMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_signoutMouseClicked
        Auth.logOut();
    }// GEN-LAST:event_signoutMouseClicked

    private void jbtnPrimeroMouseDragged(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnPrimeroMouseDragged

    }// GEN-LAST:event_jbtnPrimeroMouseDragged

    private void jbtnPrimeroMouseMoved(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnPrimeroMouseMoved

    }// GEN-LAST:event_jbtnPrimeroMouseMoved

    private void jbtnPrimeroFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jbtnPrimeroFocusGained

    }// GEN-LAST:event_jbtnPrimeroFocusGained

    private void jbtnPrimeroFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_jbtnPrimeroFocusLost
        //
    }// GEN-LAST:event_jbtnPrimeroFocusLost

    private void jbtnPrimeroMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {// GEN-FIRST:event_jbtnPrimeroMouseWheelMoved

    }// GEN-LAST:event_jbtnPrimeroMouseWheelMoved

    private void jbtnPrimeroMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnPrimeroMouseClicked

    }// GEN-LAST:event_jbtnPrimeroMouseClicked

    private void jbtnPrimeroMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnPrimeroMouseExited

    }// GEN-LAST:event_jbtnPrimeroMouseExited

    private void jbtnPrimeroMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnPrimeroMousePressed

    }// GEN-LAST:event_jbtnPrimeroMousePressed

    private void jbtnPrimeroMouseReleased(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnPrimeroMouseReleased
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnPrimeroMouseReleased

    private void jbtnPrimeroActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtnPrimeroActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnPrimeroActionPerformed

    private void jbtnQuintoMouseMoved(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnQuintoMouseMoved

    }// GEN-LAST:event_jbtnQuintoMouseMoved

    private void jbtnQuintoMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnQuintoMouseExited

    }// GEN-LAST:event_jbtnQuintoMouseExited

    private void jbtnQuintoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtnQuintoActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnQuintoActionPerformed

    private void jbtnSegundoMouseMoved(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnSegundoMouseMoved
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnSegundoMouseMoved

    private void jbtnSegundoMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnSegundoMouseClicked
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnSegundoMouseClicked

    private void jbtnSegundoMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnSegundoMouseExited
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnSegundoMouseExited

    private void jbtnSegundoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtnSegundoActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnSegundoActionPerformed

    private void jbtnSextoMouseMoved(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnSextoMouseMoved
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnSextoMouseMoved

    private void jbtnSextoMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnSextoMouseClicked
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnSextoMouseClicked

    private void jbtnSextoMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnSextoMouseExited
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnSextoMouseExited

    private void jbtnSextoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtnSextoActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnSextoActionPerformed

    private void jbtnCuartoMouseMoved(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnCuartoMouseMoved
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnCuartoMouseMoved

    private void jbtnCuartoMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnCuartoMouseClicked
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnCuartoMouseClicked

    private void jbtnCuartoMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnCuartoMouseExited
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnCuartoMouseExited

    private void jbtnCuartoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtnCuartoActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnCuartoActionPerformed

    private void jbtnTerceroMouseMoved(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnTerceroMouseMoved
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnTerceroMouseMoved

    private void jbtnTerceroMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnTerceroMouseClicked
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnTerceroMouseClicked

    private void jbtnTerceroMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnTerceroMouseExited
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnTerceroMouseExited

    private void jbtnTerceroActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtnTerceroActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnTerceroActionPerformed

    private void jbtnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtnCerrarSesionActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnCerrarSesionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel content;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JButton jbtnCuarto;
    public javax.swing.JButton jbtnOctavo;
    public javax.swing.JButton jbtnPrimero;
    public javax.swing.JButton jbtnQuinto;
    public javax.swing.JButton jbtnSegundo;
    public javax.swing.JButton jbtnSeptimo;
    public javax.swing.JButton jbtnSexto;
    public javax.swing.JButton jbtnTercero;
    private com.juancamr.components.Typography jlblNombreAdmin;
    private javax.swing.JPanel jpnlBarra;
    private javax.swing.JLabel photoAdmin;
    private com.juancamr.components.Typography signout;
    private com.juancamr.components.Typography typography1;
    // End of variables declaration//GEN-END:variables
}
