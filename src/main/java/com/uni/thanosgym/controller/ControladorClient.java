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

public class ControladorClient implements ActionListener {

    AddCliente vista;
    Cliente cli;
    boolean flag = false;

    public ControladorClient(AddCliente vista) {
        this.vista = vista;
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
        } else System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jbtnAgregar) {
            String dni = vista.jtxtDniClienteAgregar.getText();
            String phone= vista.jtxtTelefonoClienteAdd.getText();
            String nombre = vista.jtxtNombreClienteAgregar.getText();

            if (dni.isEmpty() || nombre.isEmpty()) {
                Messages.show("Por favor, llene todos los campos");
            } else {
                if (!StringUtils.isValidDni(dni)) {
                    Messages.show("El DNI debe ser un número de 8 dígitos");
                    vista.jtxtDniClienteAgregar.setText("");
                    vista.jtxtDniClienteAgregar.requestFocus();
                    return;
                }

                if (!StringUtils.isValidPhone(phone)) {
                    Messages.show("Telefono con formato incorrecto");
                    vista.jtxtTelefonoClienteAdd.setText("");
                    vista.jtxtTelefonoClienteAdd.requestFocus();
                    return;
                }
                
                String selectedPlanName = vista.jcbxPlanRegistro.getSelectedItem().toString();
                Response<Plan> response = CRUDPlan.getInstance().getByName(selectedPlanName);
                cli.setPlan(response.getData());
                cli.setDni(Integer.parseInt(vista.jtxtDniClienteAgregar.getText()));
                cli.setCreated_At(new Date());
                cli.setSubscription_since(new Date()); //same?
                cli.setFullName(vista.jtxtNombreClienteAgregar.getText());
                cli.setEmail(vista.jtxtDireccionCorreoAdd.getText());
                cli.setDireccion(vista.jtxtDireccionClienteAdd.getText());
                cli.setPhone(Integer.parseInt(vista.jtxtTelefonoClienteAdd.getText()));
                
                Response<Cliente> res = CRUDCliente.getInstance().create(cli);
                if (res.isSuccess()) {
                    vista.jtxtNombreClienteAgregar.setText("");
                    vista.jtxtDireccionClienteAdd.setText("");
                    vista.jtxtTelefonoClienteAdd.setText("");
                    vista.jtxtDniClienteAgregar.requestFocus();
                    vista.jlblExito.setText("Cliente " + cli.getFullName() + " registrado con éxito!");
                    Messages.show(response.getMessage());
                } else Messages.show(response.getMessage());
            }
        }

        if (e.getSource() == vista.jbtnBuscarCliente) {
            if (vista.jtxtDniCliente.getText().isEmpty()) {
                Messages.show("Por favor, digite un DNI");
            } else {
                try {
                    int dniCliente = Integer.parseInt(vista.jtxtDniCliente.getText());
                    Response<Cliente> response = CRUDCliente.getInstance().read(dniCliente);
                    if (response.isSuccess()) {
                        Cliente cli = response.getData();
                        if (cli != null) {
                            vista.jtxtNombreCliente.setText(cli.getFullName());
                            vista.jtxtDniCliente.setText(String.valueOf(cli.getDni()));
                            vista.jtxtDireccionCliente.setText(cli.getDireccion());
                            vista.jtxtTelefonoCliente.setText(String.valueOf(cli.getPhone()));
                        }
                    } else {
                        Messages.show("El cliente no está registrado");
                    }
                } catch (NumberFormatException exception) {
                    Messages.show("Error, el DNI debe ser un número");
                    vista.jtxtDniCliente.setText("");
                    vista.jtxtDniCliente.requestFocus();
                }
            }
        }

        if (e.getSource() == vista.jbtnEditar) {
            if (vista.jtxtNombreCliente.getText().equals("") || vista.jtxtDniCliente.getText().equals("")) {
                Messages.show("Ingrese un cliente");
            } else {
                if (flag) {
                    cli.setDni(Integer.parseInt(vista.jtxtDniCliente.getText()));
                    cli.setFullName(vista.jtxtNombreCliente.getText());
                    cli.setDireccion(vista.jtxtDireccionCliente.getText());
                    cli.setPhone(Integer.parseInt(vista.jtxtTelefonoClienteAdd.getText()));
                    CRUDCliente.getInstance().update(cli);
                    Messages.show("Datos actualizados");
                    vista.jbtnEditar.setText("EDITAR");
                    
                    vista.jtxtDniCliente.setText("");
                    vista.jtxtNombreCliente.setText("");
                    vista.jtxtDireccionCliente.setText("");
                    vista.jtxtTelefonoCliente.setText("");
                    vista.jtxtDniCliente.requestFocus();
                    
                    flag = false;
                    isFocusable(vista, false);
                    vista.jbtnEditar.setForeground(new Color(255, 255, 254));
                    vista.jbtnEditar.setBackground(new Color(20, 23, 31));
                } else {
                    flag = true;
                    isFocusable(vista, true);
                    Messages.show("Modo edicion activado");
                    vista.jbtnEditar.setText("ACTUALIZAR");
                    vista.jbtnEditar.setForeground(new Color(0, 0, 0));
                    vista.jbtnEditar.setBackground(new Color(255, 255, 254));
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
