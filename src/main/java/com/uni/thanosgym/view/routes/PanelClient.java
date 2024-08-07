/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.uni.thanosgym.view.routes;

import java.io.File;
import java.util.Date;
import com.uni.thanosgym.utils.Utils;

import javax.swing.text.JTextComponent;

import com.juancamr.route.Route;
import com.juancamr.route.Router;
import com.uni.thanosgym.controllers.ClientController;
import com.uni.thanosgym.dao.CRUDCliente;
import com.uni.thanosgym.dao.CRUDContrato;
import com.uni.thanosgym.dao.CRUDPlan;
import com.uni.thanosgym.model.Client;
import com.uni.thanosgym.model.Contrato;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.utils.Uploader;
import com.uni.thanosgym.utils.UserPreferences;
import com.uni.thanosgym.utils.DateUtils;
import com.uni.thanosgym.utils.FrameUtils;

/**
 *
 * @author jhere
 */
@Route("main:client")
public class PanelClient extends javax.swing.JPanel {
    public static File imageSelected = null;

    /**
     * Creates new form PanelClient
     */
    public PanelClient() {
        initComponents();
        FrameUtils.addOnClickEvent(jbtnRegistro, this::registerEvent);
        FrameUtils.addOnClickEvent(jbtnBuscarReniec, () -> {
            ClientController.buscarReniec(jtxtDNIRegistro.getText(), jtxtNombres, jbtnBuscarReniec);
        });
        FrameUtils.addOnClickEvent(jbtnBuscar, () -> {
            Utils.mostrarPantallaDeCarga(null, () -> {
                ClientController.buscar(jtxtDNIBuscar.getText());
            });
        });
        FrameUtils.addOnClickEvent(jbtnListar, () -> {
            Utils.mostrarPantallaDeCarga(null, () -> {
                Router.getInstance().go("client/all");
            });
        });

        Response<Plan> response = CRUDPlan.getInstance().getAll();
        for (Plan plan : response.getDataList()) {
            jcbxPlanes.addItem(new ComboItemPlan(plan.getId(), plan.getName()));
        }

        jtxtNombres.setEnabled(false);
    }

    private void registerEvent() {
        String dni = jtxtDNIRegistro.getText();
        String nombres = jtxtNombres.getText();
        String email = jtxtCorreo.getText();
        String telefono = jtxtTelefono.getText();
        String direccion = jtxtDireccion.getText();
        String codigo = jtxtCodigoTransaccion.getText();
        ComboItemPlan planItem = (ComboItemPlan) jcbxPlanes.getSelectedItem();

        if (dni.isEmpty() || nombres.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || email.isEmpty()
                || codigo.isEmpty()) {
            Messages.show("Complete todos los campos");
            return;
        }
        if (!StringUtils.isValidEmail(email)) {
            Messages.show("Ingrese un email valido");
            return;
        }
        if (!StringUtils.isValidDni(dni)) {
            Messages.show("Ingrese un DNI valido");
            return;
        }
        if (!StringUtils.isValidPhone(telefono)) {
            Messages.show("El telefono debe ser un numero de 9 digitos.");
            return;
        }
        if (!StringUtils.isInteger(codigo)) {
            Messages.show("Ingrese un código valido");
            return;
        }
        Client cliente = new Client.Builder()
                .setFullName(nombres)
                .setDni(dni)
                .setEmail(email)
                .setPhone(telefono)
                .setPhotoUrl("")
                .setDireccion(direccion)
                .build();

        if (imageSelected != null) {
            Uploader.UploaderResponse resUploader = Uploader.uploadImage(imageSelected);
            if (!resUploader.isSuccess()) {
                Messages.show(resUploader.getMessage());
                imageSelected = null;
                return;
            }
            cliente.setPhotoUrl(resUploader.getUrl());
            imageSelected = null;
        }

        Utils.mostrarPantallaDeCarga(null, () -> {
            jbtnRegistro.setEnabled(false);

            Response<Client> resCliente = CRUDCliente.getInstance().create(cliente);
            if (!resCliente.isSuccess()) {
                Messages.show(resCliente.getMessage());
                jbtnRegistro.setEnabled(true);
                return;
            }
            cliente.setId(resCliente.getId());

            Response<Plan> resPlan = CRUDPlan.getInstance().getById(planItem.getId());
            if (!resPlan.isSuccess()) {
                Messages.show(resPlan.getMessage());
                jbtnRegistro.setEnabled(true);
                return;
            }
            Plan plan = resPlan.getData();
            Contrato contrato = new Contrato.Builder()
                    .setCliente(cliente)
                    .setPlan(plan)
                    .setAdmin(UserPreferences.getData())
                    .setTransactionCode(codigo)
                    .setSubscriptionUntil(DateUtils.addDays(new Date(), plan.getDurationDays()))
                    .build();
            Response<Contrato> resContrato = CRUDContrato.getInstance().create(contrato);

            if (!resContrato.isSuccess()) {
                Messages.show(resContrato.getMessage());
                jbtnRegistro.setEnabled(true);
                return;
            }

            contrato.setCreatedAt(new Date());
            ClientController.enviarContrato(contrato);
            Messages.show("Cliente registrado correctamente");
            jbtnRegistro.setEnabled(true);
        });

        FrameUtils.clearInputs(
                new JTextComponent[] { jtxtDNIRegistro, jtxtNombres, jtxtCorreo, jtxtTelefono, jtxtDireccion,
                        jtxtCodigoTransaccion });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        typography1 = new com.juancamr.components.Typography();
        typography3 = new com.juancamr.components.Typography();
        jtxtDNIBuscar = new com.juancamr.components.InputComponent();
        typography4 = new com.juancamr.components.Typography();
        jtxtDNIRegistro = new com.juancamr.components.InputComponent();
        typography5 = new com.juancamr.components.Typography();
        jtxtNombres = new com.juancamr.components.InputComponent();
        jbtnListar = new com.juancamr.components.ButtonComponent();
        jbtnBuscarReniec = new com.juancamr.components.ButtonComponent();
        jtxtCorreo = new com.juancamr.components.InputComponent();
        typography6 = new com.juancamr.components.Typography();
        jSeparator1 = new javax.swing.JSeparator();
        typography7 = new com.juancamr.components.Typography();
        jtxtTelefono = new com.juancamr.components.InputComponent();
        typography8 = new com.juancamr.components.Typography();
        jtxtDireccion = new com.juancamr.components.InputComponent();
        typography9 = new com.juancamr.components.Typography();
        jcbxPlanes = new javax.swing.JComboBox<>();
        jbtnBuscar = new com.juancamr.components.ButtonComponent();
        jbtnRegistro = new com.juancamr.components.ButtonComponent();
        jtxtCodigoTransaccion = new com.juancamr.components.InputComponent();
        typography10 = new com.juancamr.components.Typography();

        setPreferredSize(new java.awt.Dimension(840, 690));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography1.setText("Clientes");
        typography1.setType(com.juancamr.components.Typography.Type.HEADING1);
        jPanel1.add(typography1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        typography3.setText("DNI");
        jPanel1.add(typography3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, 20));
        jPanel1.add(jtxtDNIBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 260, -1));

        typography4.setText("DNI");
        jPanel1.add(typography4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, -1, 20));
        jPanel1.add(jtxtDNIRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 260, -1));

        typography5.setText("Nombres completos");
        jPanel1.add(typography5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, -1, 20));
        jPanel1.add(jtxtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 260, -1));

        jbtnListar.setText("LISTAR");
        jbtnListar.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel1.add(jbtnListar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, -1, -1));

        jbtnBuscarReniec.setText("BUSCAR EN RENIEC");
        jbtnBuscarReniec.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel1.add(jbtnBuscarReniec, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, -1, -1));
        jPanel1.add(jtxtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 330, 260, -1));

        typography6.setText("Correo");
        typography6.setType(com.juancamr.components.Typography.Type.BODY);
        jPanel1.add(typography6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, -1, 20));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 750, 20));

        typography7.setText("Teléfono");
        jPanel1.add(typography7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, 20));
        jPanel1.add(jtxtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 260, -1));

        typography8.setText("Dirección");
        jPanel1.add(typography8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, -1, 20));
        jPanel1.add(jtxtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 410, 260, -1));

        typography9.setText("Selecciona un plan");
        jPanel1.add(typography9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, -1, 30));

        jPanel1.add(jcbxPlanes, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 260, -1));

        jbtnBuscar.setText("BUSCAR");
        jbtnBuscar.setType(com.juancamr.components.ButtonComponent.Type.SMALL);
        jPanel1.add(jbtnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, -1, -1));

        jbtnRegistro.setText("Registrar");
        jbtnRegistro.setType(com.juancamr.components.ButtonComponent.Type.PRIMARY);
        jPanel1.add(jbtnRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 590, 270, 40));
        jPanel1.add(jtxtCodigoTransaccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 500, 260, -1));

        typography10.setText("Código de transacción");
        jPanel1.add(typography10, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 470, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 840,
                                javax.swing.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 690,
                                javax.swing.GroupLayout.PREFERRED_SIZE));
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnChooseImageActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbtnChooseImageActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_jbtnChooseImageActionPerformed

    private void buttonComponent6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_buttonComponent6ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_buttonComponent6ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private com.juancamr.components.ButtonComponent jbtnBuscar;
    private com.juancamr.components.ButtonComponent jbtnBuscarReniec;
    private com.juancamr.components.ButtonComponent jbtnListar;
    private com.juancamr.components.ButtonComponent jbtnRegistro;
    private javax.swing.JComboBox<ComboItemPlan> jcbxPlanes;
    private com.juancamr.components.InputComponent jtxtCodigoTransaccion;
    private com.juancamr.components.InputComponent jtxtCorreo;
    private com.juancamr.components.InputComponent jtxtDNIBuscar;
    private com.juancamr.components.InputComponent jtxtDNIRegistro;
    private com.juancamr.components.InputComponent jtxtDireccion;
    private com.juancamr.components.InputComponent jtxtNombres;
    private com.juancamr.components.InputComponent jtxtTelefono;
    private com.juancamr.components.Typography typography1;
    private com.juancamr.components.Typography typography10;
    private com.juancamr.components.Typography typography3;
    private com.juancamr.components.Typography typography4;
    private com.juancamr.components.Typography typography5;
    private com.juancamr.components.Typography typography6;
    private com.juancamr.components.Typography typography7;
    private com.juancamr.components.Typography typography8;
    private com.juancamr.components.Typography typography9;

    // End of variables declaration//GEN-END:variables
    //
    public static class ComboItemPlan {
        private int id;
        private String name;

        public ComboItemPlan(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
