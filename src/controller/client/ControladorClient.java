package controller.client;

import DAO.CRUDCliente;
import Formato.FormatoCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.Messages;
import model.Cliente;
import view.client.AddCliente;
import java.awt.Color;
import model.Response;
import utils.FrameUtils;

public class ControladorClient implements ActionListener {

    AddCliente vista;
    Cliente cli;
    boolean flag = false;

    public ControladorClient(AddCliente vista) {
        this.vista = vista;
        vista.jbtnAgregar.addActionListener(this);
        vista.jbtnBuscarCliente.addActionListener(this);
        vista.jbtnEditar.addActionListener(this);
        FrameUtils.showWindow(vista, "Nuevo Cliente");
        vista.jtxtDniClienteAgregar.requestFocus();
        FormatoCliente.cargarComboPlanes(vista.jcbxPlanRegistro);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jbtnAgregar) {
            String dni = vista.jtxtDniClienteAgregar.getText();
            String nombre = vista.jtxtNombreClienteAgregar.getText();

            if (dni.isEmpty() || nombre.isEmpty()) {
                Messages.show("Por favor, llene todos los campos");
            } else {
                if (!dni.matches("\\d{8}")) {
                    Messages.show("El DNI debe ser un número de 8 dígitos");
                    vista.jtxtDniClienteAgregar.setText("");
                    vista.jtxtDniClienteAgregar.requestFocus();
                    return;
                }
                Cliente cli = FormatoCliente.leerClienteRegistro(vista);
                CRUDCliente.getInstance().create(cli);
                FormatoCliente.limpiarEntradasRegistro(vista);
                vista.jlblExito.setText("Cliente " + cli.getNombre() + " registrado con éxito!");
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
                            vista.jtxtNombreCliente.setText(cli.getNombre());
                            vista.jtxtDniCliente.setText(String.valueOf(cli.getDni()));
                            vista.jtxtDireccionCliente.setText(cli.getDireccion());
                            vista.jtxtTelefonoCliente.setText(String.valueOf(cli.getTelefono()));
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
                    FormatoCliente.leerClienteBusqueda(vista, cli);
                    CRUDCliente.getInstance().update(cli);
                    Messages.show("Datos actualizados");
                    vista.jbtnEditar.setText("EDITAR");
                    FormatoCliente.limpiarEntradasBusqueda(vista);
                    flag = false;
                    FormatoCliente.isFocusable(vista, false);
                    vista.jbtnEditar.setForeground(new Color(255, 255, 254));
                    vista.jbtnEditar.setBackground(new Color(20, 23, 31));
                } else {
                    flag = true;
                    FormatoCliente.isFocusable(vista, true);
                    Messages.show("Modo edicion activado");
                    vista.jbtnEditar.setText("ACTUALIZAR");
                    vista.jbtnEditar.setForeground(new Color(0, 0, 0));
                    vista.jbtnEditar.setBackground(new Color(255, 255, 254));
                }
            }
        }
    }

}
