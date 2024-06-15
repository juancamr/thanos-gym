package com.uni.thanosgym.controller;

import com.uni.thanosgym.dao.CRUDCliente;
import com.uni.thanosgym.dao.CRUDPayment;
import com.uni.thanosgym.dao.CRUDPlan;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.model.Cliente;
import com.uni.thanosgym.model.Payment;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.DateUtils;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.view.MainWindow;
import com.uni.thanosgym.view.PanelClientBuscar;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import javax.swing.JTextField;

public class ControladorClientBuscar {

    private static boolean flag = false;
    public static PanelClientBuscar panel;

    public static void showPanel() {
        MainWindow vista = ControladorMainWindow.getMainWindow();
        PanelClientBuscar panel = ControladorClientBuscar.getPanel();
        FrameUtils.showPanel(vista, panel);
        FrameUtils.addOnClickEvent(panel.jbtnBuscarCliente, ControladorClientBuscar::buscar);
        FrameUtils.addOnClickEvent(panel.jbtnEditar, ControladorClientBuscar::editar);
        FrameUtils.addOnClickEvent(panel.jbtnRenovar, ControladorClientBuscar::renovar);

        Response<Plan> response = CRUDPlan.getInstance().getAll();
        if (response.isSuccess()) {
            List<Plan> listaPlanes = response.getDataList();
            panel.jcbxPlanRegistro.removeAllItems();
            for (Plan plan : listaPlanes) {
                panel.jcbxPlanRegistro.addItem(plan.getName());
            }
        } else {
            Messages.show(response.getMessage());
        }
    }

    public static PanelClientBuscar getPanel() {
        if (panel == null) {
            panel = new PanelClientBuscar();
        }
        return panel;
    }

    public static void buscar() {
        PanelClientBuscar panel = ControladorClientBuscar.getPanel();
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
                        panel.jtxtDireccionCliente.setText(cli.getDireccion());
                        panel.jtxtTelefonoCliente.setText(String.valueOf(cli.getPhone()));

                        Response<Payment> paymentResponse = CRUDPayment.getInstance().getByCliente(cli.getId());
                        if (paymentResponse.isSuccess()) {
                            Payment payment = paymentResponse.getData();
                            Date fechaActual = new Date();
                            Date fechaFinal = cli.getSubscription_until();

                            if (fechaFinal != null) {
                                if (fechaActual.after(fechaFinal)) {
                                    // Plan vencido
                                    panel.jPanelEstado.setBackground(Color.RED);
                                    panel.jtxtPlanActual.setText("Plan vencido");
                                } else {
                                    // Plan válido
                                    panel.jPanelEstado.setBackground(Color.GREEN);
                                    panel.jtxtPlanActual.setText(payment.getPlan().getName());
                                }
                            } else {
                                panel.jPanelEstado.setBackground(Color.GRAY);
                                panel.jtxtPlanActual.setText("Plan no asignado");
                            }
                        } else {
                            panel.jPanelEstado.setBackground(Color.GRAY);
                            panel.jtxtPlanActual.setText("Pago no encontrado");
                        }
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

    public static void editar() {
        PanelClientBuscar panel = ControladorClientBuscar.getPanel();

        if (panel.jtxtNombreCliente.getText().isEmpty() || panel.jtxtDniCliente.getText().isEmpty()) {
            Messages.show("Ingrese un cliente");
        } else {
            try {
                int dniCliente = Integer.parseInt(panel.jtxtDniCliente.getText());
                Response<Cliente> response = CRUDCliente.getInstance().read(dniCliente);

                if (response.isSuccess()) {
                    Cliente cli = response.getData();

                    if (flag) {
                        cli.setFullName(panel.jtxtNombreCliente.getText());
                        cli.setDireccion(panel.jtxtDireccionCliente.getText());
                        cli.setPhone(Integer.parseInt(panel.jtxtTelefonoCliente.getText()));

                        Response<Cliente> updateResponse = CRUDCliente.getInstance().update(cli);
                        if (updateResponse.isSuccess()) {
                            Messages.show("Datos actualizados");
                        } else {
                            Messages.show(updateResponse.getMessage());
                        }

                        panel.jbtnEditar.setText("EDITAR");

                        JTextField[] inputs = {panel.jtxtDniCliente, panel.jtxtNombreCliente, panel.jtxtDireccionCliente, panel.jtxtTelefonoCliente};
                        FrameUtils.clearInputs(inputs);

                        flag = false;
                        isFocusable(panel, false);
                        panel.jbtnEditar.setForeground(new Color(255, 255, 254));
                        panel.jbtnEditar.setBackground(new Color(20, 23, 31));
                    } else {
                        flag = true;
                        isFocusable(panel, true);
                        panel.jbtnEditar.setText("ACTUALIZAR");
                        panel.jbtnEditar.setForeground(new Color(0, 0, 0));
                        panel.jbtnEditar.setBackground(new Color(255, 255, 254));
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

    public static void renovar() {
        PanelClientBuscar panel = ControladorClientBuscar.getPanel();

        try {
            int dniCliente = Integer.parseInt(panel.jtxtDniCliente.getText());
            Response<Cliente> responseCliente = CRUDCliente.getInstance().read(dniCliente);

            if (responseCliente.isSuccess()) {
                Cliente cliente = responseCliente.getData();

                String nombrePlanSeleccionado = (String) panel.jcbxPlanRegistro.getSelectedItem();
                Response<Plan> responsePlan = CRUDPlan.getInstance().getByName(nombrePlanSeleccionado);

                if (responsePlan.isSuccess()) {
                    Plan planSeleccionado = responsePlan.getData();

                    Date subscriptionUntil = cliente.getSubscription_until();
                    Date nuevaSubscriptionUntil = DateUtils.addDays(subscriptionUntil, planSeleccionado.getDurationDays());
                    cliente.setSubscription_until(nuevaSubscriptionUntil);

                    Response<Cliente> responseUpdate = CRUDCliente.getInstance().update(cliente);

                    if (responseUpdate.isSuccess()) {
                        // Actualizar el pago
                        Payment payment = new Payment(new Date(), generateTicketCode(), generateTransactionCode(), cliente, planSeleccionado);
                        Response<Payment> paymentResponse = CRUDPayment.getInstance().create(payment);

                        if (paymentResponse.isSuccess()) {
                            Messages.show("Suscripción renovada exitosamente");
                            panel.jtxtPlanActual.setText(planSeleccionado.getName());
                        } else {
                            Messages.show("Error al registrar el pago: " + paymentResponse.getMessage());
                        }
                    } else {
                        Messages.show("Error al actualizar la suscripción: " + responseUpdate.getMessage());
                    }
                } else {
                    Messages.show("Error al obtener el plan seleccionado: " + responsePlan.getMessage());
                }
            } else {
                Messages.show("Error al obtener el cliente: " + responseCliente.getMessage());
            }
        } catch (NumberFormatException e) {
            Messages.show("Error, el DNI debe ser un número válido");
            FrameUtils.clearInputs(panel.jtxtDniCliente);
        }
    }

    private static int generateTicketCode() {
        return (int) (Math.random() * 1000000);
    }

    private static int generateTransactionCode() {
        return (int) (Math.random() * 1000000);
    }

    public static void isFocusable(PanelClientBuscar panel, boolean flag) {
        panel.jtxtNombreCliente.setFocusable(flag);
        panel.jtxtDireccionCliente.setFocusable(flag);
        panel.jtxtTelefonoCliente.setFocusable(flag);
    }
}
