/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.uni.thanosgym.view.routes;

import com.juancamr.route.Route;
import com.juancamr.route.RoutingUtils;

import java.util.List;

import com.uni.thanosgym.controllers.ProveedorController;
import com.uni.thanosgym.dao.CRUDProveedor;
import com.uni.thanosgym.model.Proveedor;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.view.dialogs.AgregarProveedor;
import com.uni.thanosgym.view.dialogs.EditarProveedor;

import java.util.Map;

/**
 *
 * @author juancamr
 */
@Route("main:proveedor*")
public class PanelProveedor extends javax.swing.JPanel {

    private List<Proveedor> proveedores;

    /**
     * Creates new form PanelProveedor
     */
    public PanelProveedor() {
        initComponents();
        refreshProveedores();
    }

    public void refreshProveedores() {
        Response<Proveedor> response = CRUDProveedor.getInstance().getAll();
        if (!response.isSuccess()) {
            return;
        }
        ((javax.swing.table.DefaultTableModel) jtblProveedores.getModel()).setRowCount(0);
        proveedores = response.getDataList();
        for (Proveedor proveedor : proveedores) {
            String[] datos = new String[]{
                proveedor.getRuc(),
                proveedor.getNombre(),
                proveedor.getAddress(),
                proveedor.getPhone()
            };
            ((javax.swing.table.DefaultTableModel) jtblProveedores.getModel()).addRow(datos);
        }
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
        typography1 = new com.juancamr.components.Typography();
        jbtnAgregar = new com.juancamr.components.ButtonComponent();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblProveedores = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography1.setText("Proveedores");
        typography1.setType(com.juancamr.components.Typography.Type.HEADING1);
        jPanel1.add(typography1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        jbtnAgregar.setText("Agregar");
        jbtnAgregar.setType(com.juancamr.components.ButtonComponent.Type.PRIMARY);
        jbtnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jbtnAgregarMouseClicked(evt);
            }
        });
        jPanel1.add(jbtnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 40, -1, -1));

        jtblProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RUC", "Nombre", "Dirección", "Teléfono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtblProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblProveedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtblProveedores);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 750, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jtblProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblProveedoresMouseClicked
        int row = jtblProveedores.getSelectedRow();
        if (row == -1) {
            return;
        }
        Map<String, Object> params = RoutingUtils.openDialog(new EditarProveedor());
        if (params == null) {
            return;
        }

        String ruc = (String) params.get("ruc");
        String nombre = (String) params.get("nombre");
        String direccion = (String) params.get("direccion");
        String telefono = (String) params.get("telefono");

        if (ruc.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
            Messages.show("Complete todos los campos");
            return;
        }

        if (!StringUtils.isValidRuc(ruc)) {
            Messages.show("El RUC no es valido");
            return;
        }

        if (!StringUtils.isValidPhone(telefono)) {
            Messages.show("El telefono debe ser un numero de 9 digitos.");
            return;
        }

        Proveedor proveedor = proveedores.get(row);
        proveedor.setRuc(ruc);
        proveedor.setNombre(nombre);
        proveedor.setAddress(direccion);
        proveedor.setPhone(telefono);
        Response<Proveedor> response = CRUDProveedor.getInstance().update(proveedor);
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return;
        }
        refreshProveedores();
    }//GEN-LAST:event_jtblProveedoresMouseClicked
     //

    private void jbtnAgregarMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jbtnAgregarMouseClicked
        Map<String, Object> params = RoutingUtils.openDialog(new AgregarProveedor());
        if (params == null) {
            return;
        }
        if (ProveedorController.registrar(params)) {
            refreshProveedores();
        }
    }// GEN-LAST:event_jbtnAgregarMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.juancamr.components.ButtonComponent jbtnAgregar;
    private javax.swing.JTable jtblProveedores;
    private com.juancamr.components.Typography typography1;
    // End of variables declaration//GEN-END:variables
}
