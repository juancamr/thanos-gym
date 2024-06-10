package com.uni.thanosgym.controller;

import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.dao.CRUDCliente;
import com.uni.thanosgym.dao.CRUDPlan;
import java.util.Date;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.model.Cliente;
import com.uni.thanosgym.model.Plan;
import java.awt.Color;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.DateUtils;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.view.MainWindow;
import com.uni.thanosgym.view.PanelClient;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JTextField;

public class ControladorClient {

    MainWindow vista;
    PanelClient panel;
    Cliente cli;
    boolean flag = false;

    public ControladorClient(MainWindow v, PanelClient pan, boolean flag) {
        this.panel = pan;
        this.vista = v;
        cli = new Cliente();
        FrameUtils.showPanel(vista, panel);
        panel.jtxtDniClienteAgregar.requestFocus();
        FrameUtils.addOnClickEvent(panel.jbtnBuscarCliente, this::buscar);
        FrameUtils.addOnClickEvent(panel.jbtnAgregar, this::agregar);
        FrameUtils.addOnClickEvent(panel.jbtnEditar, this::editar);

        // bro what the hell?
        // no se que hace esto, deja un comentario
        panel.jdchFechaInicial.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Date fechaInicio = (Date) evt.getNewValue();
                if (fechaInicio != null) {
                    String selectedPlanName = panel.jcbxPlanRegistro.getSelectedItem().toString();
                    Response<Plan> response = CRUDPlan.getInstance().getByName(selectedPlanName);
                    if (response.isSuccess()) {
                        Plan plan = response.getData();
                        Date fechaFinal = DateUtils.addDays(fechaInicio, plan.getDurationDays());
                        panel.jdchFechaFinal.setDate(fechaFinal);
                    }
                }
            }
        });

        Response<Plan> response = CRUDPlan.getInstance().getAll();
        if (response.isSuccess()) {
            List<Plan> listaPlanes = response.getDataList();
            for (int i = 0; i < listaPlanes.size(); i++)
                panel.jcbxPlanRegistro.addItem(listaPlanes.get(i).getName());
        } else
            Messages.show(response.getMessage());
    }

    private void agregar() {
        String dni = panel.jtxtDniClienteAgregar.getText();
        String phone = panel.jtxtTelefonoClienteAdd.getText();
        String nombre = panel.jtxtNombreClienteAgregar.getText();

        if (dni.isEmpty() || nombre.isEmpty()) {
            Messages.show("Por favor, llene todos los campos");
        } else {
            if (!StringUtils.isValidDni(dni)) {
                Messages.show("El DNI debe ser un número de 8 dígitos");
                panel.jtxtDniClienteAgregar.setText("");
                panel.jtxtDniClienteAgregar.requestFocus();
                return;
            }
            if (!StringUtils.isValidPhone(phone)) {
                Messages.show("Telefono con formato incorrecto");
                panel.jtxtTelefonoClienteAdd.setText("");
                panel.jtxtTelefonoClienteAdd.requestFocus();
                return;
            }
            String selectedPlanName = panel.jcbxPlanRegistro.getSelectedItem().toString();
            Response<Plan> response = CRUDPlan.getInstance().getByName(selectedPlanName);
            Plan plan = response.getData();
            cli.setPlan(plan);
            cli.setDni(Integer.parseInt(panel.jtxtDniClienteAgregar.getText()));
            cli.setCreated_At(new Date());

            Date fechaInicio = panel.jdchFechaInicial.getDate();
            cli.setSubscription_since(fechaInicio);

            Date fechaFinal = DateUtils.addDays(fechaInicio, plan.getDurationDays());
            panel.jdchFechaFinal.setDate(fechaFinal);

            cli.setFullName(panel.jtxtNombreClienteAgregar.getText());
            cli.setEmail(panel.jtxtDireccionCorreoAdd.getText());
            cli.setDireccion(panel.jtxtDireccionClienteAdd.getText());
            cli.setPhone(Integer.parseInt(panel.jtxtTelefonoClienteAdd.getText()));

            Response<Cliente> res = CRUDCliente.getInstance().create(cli);
            if (res.isSuccess()) {
                JTextField[] inputs = { panel.jtxtNombreClienteAgregar, panel.jtxtDireccionClienteAdd,
                        panel.jtxtDireccionCorreoAdd, panel.jtxtTelefonoClienteAdd };
                FrameUtils.clearInputs(inputs);
                panel.jlblExito.setText("Cliente " + cli.getFullName() + " registrado con éxito!");
                Messages.show(response.getMessage());
            } else {
                Messages.show(response.getMessage());
            }
        }
    }

    private void buscar() {
        if (panel.jtxtDniCliente.getText().isEmpty()) {
            Messages.show("Por favor, digite un DNI");
        } else {
            try {
                int dniCliente = Integer.parseInt(panel.jtxtDniCliente.getText());
                Response<Cliente> response = CRUDCliente.getInstance().read(dniCliente);
                if (response.isSuccess()) {
                    Cliente cli = response.getData();
                    if (cli != null) {
                        panel.jtxtNombreCliente.setText(cli.getFullName());
                        panel.jtxtDniCliente.setText(String.valueOf(cli.getDni()));
                        panel.jtxtDireccionCliente.setText(cli.getDireccion());
                        panel.jtxtTelefonoCliente.setText(String.valueOf(cli.getPhone()));
                    }
                } else {
                    Messages.show("El cliente no está registrado");
                }
            } catch (NumberFormatException exception) {
                Messages.show("Error, el DNI debe ser un número");
                FrameUtils.clearInputs(panel.jtxtDniCliente);
            }
        }
    }

    private void editar() {
        if (panel.jtxtNombreCliente.getText().equals("") || panel.jtxtDniCliente.getText().equals("")) {
            Messages.show("Ingrese un cliente");
        } else {
            if (flag) {
                cli.setDni(Integer.parseInt(panel.jtxtDniCliente.getText()));
                cli.setFullName(panel.jtxtNombreCliente.getText());
                cli.setDireccion(panel.jtxtDireccionCliente.getText());
                cli.setPhone(Integer.parseInt(panel.jtxtTelefonoClienteAdd.getText()));
                CRUDCliente.getInstance().update(cli);
                Messages.show("Datos actualizados");
                panel.jbtnEditar.setText("EDITAR");

                JTextField[] inputs = { panel.jtxtDniCliente, panel.jtxtNombreCliente, panel.jtxtDireccionCliente,
                        panel.jtxtTelefonoCliente };
                FrameUtils.clearInputs(inputs);

                flag = false;

                isFocusable(panel, false);
                panel.jbtnEditar.setForeground(new Color(255, 255, 254));
                panel.jbtnEditar.setBackground(new Color(20, 23, 31));
            } else {
                flag = true;
                isFocusable(panel, true);
                Messages.show("Modo edicion activado");
                panel.jbtnEditar.setText("ACTUALIZAR");
                panel.jbtnEditar.setForeground(new Color(0, 0, 0));
                panel.jbtnEditar.setBackground(new Color(255, 255, 254));
            }
        }
    }

    public void isFocusable(PanelClient panel, boolean flag) {
        panel.jtxtNombreCliente.setFocusable(flag);
        panel.jtxtDireccionCliente.setFocusable(flag);
        panel.jtxtTelefonoCliente.setFocusable(flag);
    }

}
