package com.uni.thanosgym.controller;

import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.dao.CRUDCliente;
import com.uni.thanosgym.dao.CRUDPlan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.model.Cliente;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.view.AddCliente;
import java.awt.Color;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.FrameUtils;
import java.util.List;

import javax.swing.JTextField;

public class ControladorClient implements ActionListener {

    AddCliente v;
    Cliente cli;
    boolean flag = false;

    public ControladorClient(AddCliente vista) {
        this.v = vista;
        vista.jbtnAgregar.addActionListener(this);
        vista.jbtnBuscarCliente.addActionListener(this);
        vista.jbtnEditar.addActionListener(this);
        vista.setSize(840, 790);
        FrameUtils.showWindow(vista, "Nuevo Cliente");
        vista.jtxtDniClienteAgregar.requestFocus();

        Response<Plan> response = CRUDPlan.getInstance().getAll();
        if (response.isSuccess()) {
            List<Plan> listaPlanes = response.getDataList();
            for (int i = 0; i < listaPlanes.size(); i++) {
                vista.jcbxPlanRegistro.addItem(listaPlanes.get(i).getName());
            }
        } else
            System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == v.jbtnAgregar) {
            String dni = v.jtxtDniClienteAgregar.getText();
            String phone = v.jtxtTelefonoClienteAdd.getText();
            String nombre = v.jtxtNombreClienteAgregar.getText();

            if (dni.isEmpty() || nombre.isEmpty()) {
                Messages.show("Por favor, llene todos los campos");
            } else {
                if (!StringUtils.isValidDni(dni)) {
                    Messages.show("El DNI debe ser un número de 8 dígitos");
                    v.jtxtDniClienteAgregar.setText("");
                    v.jtxtDniClienteAgregar.requestFocus();
                    return;
                }

                if (!StringUtils.isValidPhone(phone)) {
                    Messages.show("Telefono con formato incorrecto");
                    v.jtxtTelefonoClienteAdd.setText("");
                    v.jtxtTelefonoClienteAdd.requestFocus();
                    return;
                }

                String selectedPlanName = v.jcbxPlanRegistro.getSelectedItem().toString();
                Response<Plan> response = CRUDPlan.getInstance().getByName(selectedPlanName);
                cli.setPlan(response.getData());
                cli.setDni(Integer.parseInt(v.jtxtDniClienteAgregar.getText()));
                cli.setCreated_At(new Date());
                cli.setSubscription_since(new Date()); // same?
                cli.setFullName(v.jtxtNombreClienteAgregar.getText());
                cli.setEmail(v.jtxtDireccionCorreoAdd.getText());
                cli.setDireccion(v.jtxtDireccionClienteAdd.getText());
                cli.setPhone(Integer.parseInt(v.jtxtTelefonoClienteAdd.getText()));

                Response<Cliente> res = CRUDCliente.getInstance().create(cli);
                if (res.isSuccess()) {
                    JTextField[] inputs = { v.jtxtNombreClienteAgregar, v.jtxtDireccionClienteAdd,
                            v.jtxtTelefonoClienteAdd };
                    FrameUtils.clearInputs(inputs);
                    v.jlblExito.setText("Cliente " + cli.getFullName() + " registrado con éxito!");
                    Messages.show(response.getMessage());
                } else
                    Messages.show(response.getMessage());
            }
        }

        if (e.getSource() == v.jbtnBuscarCliente) {
            if (v.jtxtDniCliente.getText().isEmpty()) {
                Messages.show("Por favor, digite un DNI");
            } else {
                try {
                    int dniCliente = Integer.parseInt(v.jtxtDniCliente.getText());
                    Response<Cliente> response = CRUDCliente.getInstance().read(dniCliente);
                    if (response.isSuccess()) {
                        Cliente cli = response.getData();
                        if (cli != null) {
                            v.jtxtNombreCliente.setText(cli.getFullName());
                            v.jtxtDniCliente.setText(String.valueOf(cli.getDni()));
                            v.jtxtDireccionCliente.setText(cli.getDireccion());
                            v.jtxtTelefonoCliente.setText(String.valueOf(cli.getPhone()));
                        }
                    } else {
                        Messages.show("El cliente no está registrado");
                    }
                } catch (NumberFormatException exception) {
                    Messages.show("Error, el DNI debe ser un número");
                    FrameUtils.clearInputs(v.jtxtDniCliente);
                }
            }
        }

        if (e.getSource() == v.jbtnEditar) {
            if (v.jtxtNombreCliente.getText().equals("") || v.jtxtDniCliente.getText().equals("")) {
                Messages.show("Ingrese un cliente");
            } else {
                if (flag) {
                    cli.setDni(Integer.parseInt(v.jtxtDniCliente.getText()));
                    cli.setFullName(v.jtxtNombreCliente.getText());
                    cli.setDireccion(v.jtxtDireccionCliente.getText());
                    cli.setPhone(Integer.parseInt(v.jtxtTelefonoClienteAdd.getText()));
                    CRUDCliente.getInstance().update(cli);
                    Messages.show("Datos actualizados");
                    v.jbtnEditar.setText("EDITAR");

                    JTextField[] inputs = { v.jtxtDniCliente, v.jtxtNombreCliente, v.jtxtDireccionCliente,
                            v.jtxtTelefonoCliente };
                    FrameUtils.clearInputs(inputs);

                    flag = false;
                    isFocusable(v, false);
                    v.jbtnEditar.setForeground(new Color(255, 255, 254));
                    v.jbtnEditar.setBackground(new Color(20, 23, 31));
                } else {
                    flag = true;
                    isFocusable(v, true);
                    Messages.show("Modo edicion activado");
                    v.jbtnEditar.setText("ACTUALIZAR");
                    v.jbtnEditar.setForeground(new Color(0, 0, 0));
                    v.jbtnEditar.setBackground(new Color(255, 255, 254));
                }
            }
        }
    }

    public void isFocusable(AddCliente vista, boolean flag) {
        vista.jtxtNombreCliente.setFocusable(flag);
        vista.jtxtDireccionCliente.setFocusable(flag);
        vista.jtxtTelefonoCliente.setFocusable(flag);
    }

}
