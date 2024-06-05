package com.uni.thanosgym.controller;

import com.uni.thanosgym.dao.CRUDPlan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.view.AddPlan;

public class ControladorAgregarPlan implements ActionListener {
    AddPlan vista;

    public ControladorAgregarPlan(AddPlan vista) {
        this.vista = vista;
        FrameUtils.showWindow(vista, "Crear nuevo plan");
        vista.jtxtNombrePlan.requestFocus();
        vista.jbtnCrear.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jbtnCrear) {
            String nombre = vista.jtxtNombrePlan.getText();
            String precio = vista.jtxtPrecio.getText();
            String duracion = vista.jtxtDuracion.getText();

            if (nombre.isEmpty() || precio.isEmpty() || duracion.isEmpty()) {
                Messages.show("Por favor, llene todos los campos");
            } else {
                if (!precio.matches("[0-9]+") || !duracion.matches("[0-9]+")) {
                    Messages.show("El precio debe ser un número entero");
                    return;
                }
                CRUDPlan.getInstance().create(new Plan(nombre, Double.valueOf(precio), Integer.valueOf(duracion)));
                Messages.show("Plan creado exitosamente");
                vista.dispose();
            }
        }
    }

}
