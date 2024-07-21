/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.uni.thanosgym.view.dialogs;

import javax.swing.JFrame;

import com.uni.thanosgym.controllers.ClientController;
import com.uni.thanosgym.model.Client;
import com.uni.thanosgym.model.Contrato;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.utils.Utils;
import com.uni.thanosgym.utils.Messages;

/**
 *
 * @author juancamr
 */
public class ContratoData extends javax.swing.JFrame {
    private Contrato contrato;

    /**
     * Creates new form ContratoData
     */
    public ContratoData(Contrato contrato) {
        initComponents();
        this.contrato = contrato;

        jlblPlanName.setText(contrato.getPlan().getName());
        Client cliente = contrato.getCliente();
        jlblDniCliente.setText(cliente.getDni());
        jlblNombreCliente.setText(cliente.getFullName());
        jlblDireccionCiente.setText(cliente.getDireccion());

        jlblContrataHasta.setText(StringUtils.parseSpanishDate(contrato.getSubscriptionUntil()));
        jlblContratoDesde.setText(StringUtils.parseSpanishDate(contrato.getCreatedAt()));
        jlblCodigoTransaccion.setText(contrato.getTransactionCode());

        FrameUtils.addOnClickEvent(jbtnEnviarCorreo, this::enviarContratoPorCorreo);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void enviarContratoPorCorreo() {
        jbtnEnviarCorreo.setEnabled(false);
        Utils.mostrarPantallaDeCarga(this, () -> {
            ClientController.enviarContrato(contrato);
            Messages.show("Contrato enviado a " + contrato.getCliente().getEmail());
            jbtnEnviarCorreo.setEnabled(true);
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jlblPlanName = new com.juancamr.components.Typography();
        jPanel2 = new javax.swing.JPanel();
        typography4 = new com.juancamr.components.Typography();
        typography5 = new com.juancamr.components.Typography();
        jlblNombreCliente = new com.juancamr.components.Typography();
        jlblDireccionCiente = new com.juancamr.components.Typography();
        typography6 = new com.juancamr.components.Typography();
        jlblDniCliente = new com.juancamr.components.Typography();
        typography3 = new com.juancamr.components.Typography();
        jlblContratoDesde = new com.juancamr.components.Typography();
        typography7 = new com.juancamr.components.Typography();
        jlblContrataHasta = new com.juancamr.components.Typography();
        typography8 = new com.juancamr.components.Typography();
        jlblCodigoTransaccion = new com.juancamr.components.Typography();
        jbtnEnviarCorreo = new com.juancamr.components.ButtonComponent();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlblPlanName.setText("PLAN");
        jlblPlanName.setType(com.juancamr.components.Typography.Type.HEADING1);
        jPanel1.add(jlblPlanName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 360, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography4.setText("Cliente");
        typography4.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(typography4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        typography5.setText("Dirección");
        typography5.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(typography5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jlblNombreCliente.setText("Nombre del cliente");
        jlblNombreCliente.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(jlblNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 320, -1));

        jlblDireccionCiente.setText("Direccion del cliente");
        jlblDireccionCiente.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(jlblDireccionCiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 330, -1));

        typography6.setText("DNI");
        typography6.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(typography6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jlblDniCliente.setText("DNI del cliente");
        jlblDniCliente.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(jlblDniCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 120, -1));

        typography3.setText("Activo desde");
        typography3.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(typography3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jlblContratoDesde.setText("Fecha actual");
        jlblContratoDesde.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(jlblContratoDesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 250, -1));

        typography7.setText("Activo hasta");
        typography7.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(typography7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jlblContrataHasta.setText("Fecha actual");
        jlblContrataHasta.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(jlblContrataHasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 290, -1));

        typography8.setText("Código de transacción");
        typography8.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(typography8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jlblCodigoTransaccion.setText("Fecha actual");
        jlblCodigoTransaccion.setType(com.juancamr.components.Typography.Type.SMALL);
        jPanel2.add(jlblCodigoTransaccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 290, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 380, 300));

        jbtnEnviarCorreo.setText("Enviar contrato");
        jbtnEnviarCorreo.setType(com.juancamr.components.ButtonComponent.Type.PRIMARY);
        jPanel1.add(jbtnEnviarCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 230, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.juancamr.components.ButtonComponent jbtnEnviarCorreo;
    private com.juancamr.components.Typography jlblCodigoTransaccion;
    private com.juancamr.components.Typography jlblContrataHasta;
    private com.juancamr.components.Typography jlblContratoDesde;
    private com.juancamr.components.Typography jlblDireccionCiente;
    private com.juancamr.components.Typography jlblDniCliente;
    private com.juancamr.components.Typography jlblNombreCliente;
    private com.juancamr.components.Typography jlblPlanName;
    private com.juancamr.components.Typography typography3;
    private com.juancamr.components.Typography typography4;
    private com.juancamr.components.Typography typography5;
    private com.juancamr.components.Typography typography6;
    private com.juancamr.components.Typography typography7;
    private com.juancamr.components.Typography typography8;
    // End of variables declaration//GEN-END:variables
}
