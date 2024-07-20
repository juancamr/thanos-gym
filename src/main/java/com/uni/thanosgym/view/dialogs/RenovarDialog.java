/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.uni.thanosgym.view.dialogs;

import com.juancamr.route.DialogPanel;
import com.uni.thanosgym.dao.CRUDPlan;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.view.routes.PanelClient;

/**
 *
 * @author juancamr
 */
public class RenovarDialog extends DialogPanel {

    /**
     * Creates new form RenovarDialog
     */
    public RenovarDialog() {
        initComponents();
        setButtonAction(button);
        setOnAction((params) -> {
            params.put("plan_item", jcbxPlanes.getSelectedItem());
            params.put("codigo", jtxtCodigoTransaccion.getText());
            return params;
        });
        Response<Plan> response = CRUDPlan.getInstance().getAll();
        for (Plan plan : response.getDataList()) {
            jcbxPlanes.addItem(new PanelClient.ComboItemPlan(plan.getId(), plan.getName()));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        typography1 = new com.juancamr.components.Typography();
        button = new com.juancamr.components.ButtonComponent();
        typography6 = new com.juancamr.components.Typography();
        jcbxPlanes = new javax.swing.JComboBox<>();
        jtxtCodigoTransaccion = new com.juancamr.components.InputComponent();
        typography2 = new com.juancamr.components.Typography();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typography1.setText("Renovar");
        typography1.setType(com.juancamr.components.Typography.Type.HEADING2);
        jPanel1.add(typography1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        button.setText("Renovar");
        button.setType(com.juancamr.components.ButtonComponent.Type.PRIMARY);
        jPanel1.add(button, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 310, 40));

        typography6.setText("Seleccione un plan");
        typography6.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel1.add(typography6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        jPanel1.add(jcbxPlanes, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 310, -1));
        jPanel1.add(jtxtCodigoTransaccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 310, -1));

        typography2.setText("Código de transacción");
        typography2.setType(com.juancamr.components.Typography.Type.MEDIUM);
        jPanel1.add(typography2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.juancamr.components.ButtonComponent button;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<PanelClient.ComboItemPlan> jcbxPlanes;
    private com.juancamr.components.InputComponent jtxtCodigoTransaccion;
    private com.juancamr.components.Typography typography1;
    private com.juancamr.components.Typography typography2;
    private com.juancamr.components.Typography typography6;
    // End of variables declaration//GEN-END:variables
}