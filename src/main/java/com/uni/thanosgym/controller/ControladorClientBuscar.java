package com.uni.thanosgym.controller;

import com.uni.thanosgym.dao.CRUDCliente;
import com.uni.thanosgym.dao.CRUDPayment;
import com.uni.thanosgym.dao.CRUDPlan;
import com.uni.thanosgym.model.Cliente;
import com.uni.thanosgym.model.Payment;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.DateUtils;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.view.MainWindow;
import com.uni.thanosgym.view.PanelClient;
import com.uni.thanosgym.view.PanelClientBuscar;
import com.uni.thanosgym.controller.ControladorWindowClients;
import com.google.gson.Gson;
import com.uni.thanosgym.utils.EnvVariables;
import com.uni.thanosgym.utils.HttpUtils;
import com.uni.thanosgym.utils.ResponseByCliente;
import com.uni.thanosgym.utils.Utils;
import com.uni.thanosgym.view.CongelarPlan;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ControladorClientBuscar {

    private static boolean flag = false;
    public static PanelClientBuscar panelBuscar;
    public static PanelClient panelClient;
    public static CongelarPlan windowCongelar;
    public static boolean panelIsRendered = false;

    public static void showPanel() {
        MainWindow vista = ControladorMainWindow.getMainWindow();
        PanelClientBuscar panel = ControladorClientBuscar.getPanelBuscar();
        FrameUtils.showPanel(vista, panel);
        FrameUtils.addOnClickEvent(panel.jbtnBuscarCliente, ControladorClientBuscar::busqueda);
        FrameUtils.addOnClickEvent(panel.jbtnEditar, ControladorClientBuscar::editar);
        FrameUtils.addOnClickEvent(panel.jbtnRenovar, ControladorClientBuscar::renovar);
        FrameUtils.addOnClickEvent(panel.jbtnBoletas, ControladorClientBuscar::abrirWindowClients);
        FrameUtils.addOnClickEvent(panel.jbtnRegistrar, ControladorClientBuscar::showPanelClient);
        FrameUtils.addOnClickEvent(panel.jbtnCongelar, ControladorClientBuscar::showWindowCongelar);
        panel.jtxtNombreCliente.setEnabled(false);
        panel.jtxtPlanActual.setEnabled(false);
        panel.jtxtNombreCliente.setBackground(new Color(240, 240, 240));
        panel.jtxtPlanActual.setBackground(new Color(240, 240, 240));
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

    public static PanelClientBuscar getPanelBuscar() {
        if (panelBuscar == null) {
            panelBuscar = new PanelClientBuscar();
        }
        return panelBuscar;
    }

    public static void busqueda() {
        PanelClientBuscar panel = ControladorClientBuscar.getPanelBuscar();
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

                        if (cli.getIsFrozen() == Cliente.IsFrozen.SI) {
                            // Cliente congelado
                            panel.jPanelEstado.setBackground(Color.CYAN);
                            panel.jtxtPlanActual.setText("Cliente congelado");
                        } else {
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
        PanelClientBuscar panel = ControladorClientBuscar.getPanelBuscar();

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

                        JTextField[] inputs = {panel.jtxtDniCliente, panel.jtxtNombreCliente,
                            panel.jtxtDireccionCliente, panel.jtxtTelefonoCliente, panel.jtxtPlanActual};
                        FrameUtils.clearInputs(inputs);

                        flag = false;
                        isFocusable(panel, false);
                        panel.jbtnEditar.setForeground(new Color(255, 255, 254));
                        panel.jbtnEditar.setBackground(new Color(20, 23, 31));
                        panel.jPanelEstado.setBackground(Color.GRAY);
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
        PanelClientBuscar panel = ControladorClientBuscar.getPanelBuscar();

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
                    Date nuevaSubscriptionUntil = DateUtils.addDays(subscriptionUntil,
                            planSeleccionado.getDurationDays());
                    cliente.setSubscription_until(nuevaSubscriptionUntil);

                    Response<Cliente> responseUpdate = CRUDCliente.getInstance().update(cliente);

                    if (responseUpdate.isSuccess()) {
                        // Actualizar el pago
                        Payment payment = new Payment(new Date(), generateTransactionCode(), cliente, planSeleccionado);
                        Response<Payment> paymentResponse = CRUDPayment.getInstance().create(payment);

                        if (paymentResponse.isSuccess()) {
                            Messages.show("Suscripción renovada exitosamente");
                            panel.jtxtPlanActual.setText(planSeleccionado.getName());
                            payment.setPlan(planSeleccionado);
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

    // metodo congelar
    private static int generateTransactionCode() {
        return (int) (Math.random() * 1000000);
    }

    public static void isFocusable(PanelClientBuscar panel, boolean flag) {
        panel.jtxtNombreCliente.setFocusable(flag);
        panel.jtxtDireccionCliente.setFocusable(flag);
        panel.jtxtTelefonoCliente.setFocusable(flag);
    }

    public static void abrirWindowClients() {
        ControladorWindowClients.showWindow();
    }

    public static void showPanelClient() {
        MainWindow vista = ControladorMainWindow.getMainWindow();
        PanelClient panel = ControladorClientBuscar.getPanelClient();
        FrameUtils.showPanel(vista, panel);
        panel.jtxtDniClienteAgregar.requestFocus();
        FrameUtils.addEnterEvent(panel.jtxtDniClienteAgregar, () -> {
            String dni = panel.jtxtDniClienteAgregar.getText();
            ControladorClientBuscar.searchByDni(dni);
        });
        panel.jtxtNombreClienteAgregar.setEnabled(false);
        panel.jtxtNombreClienteAgregar.setBackground(new Color(240, 240, 240));
        if (!panelIsRendered) {
            FrameUtils.addOnClickEvent(panel.jbtnAniadir, ControladorClientBuscar::agregar);
            FrameUtils.addEnterEvent(panel.jtxtTelefonoClienteAdd, ControladorClientBuscar::agregar);
            FrameUtils.addOnClickEvent(panel.jbtnCancelar, ControladorClientBuscar::showPanelClientBuscar);
            panelIsRendered = true;
        }

        Response<Plan> response = CRUDPlan.getInstance().getAll();
        if (response.isSuccess()) {
            List<Plan> listaPlanes = response.getDataList();
            panel.jcbxPlanRegistro.removeAllItems();
            for (int i = 0; i < listaPlanes.size(); i++) {
                panel.jcbxPlanRegistro.addItem(listaPlanes.get(i).getName());
            }
        } else {
            Messages.show(response.getMessage());
        }
    }

    public static PanelClient getPanelClient() {
        if (panelClient == null) {
            panelClient = new PanelClient();
        }
        return panelClient;
    }

    public static void showPanelClientBuscar() {
        MainWindow vista = ControladorMainWindow.getMainWindow();
        PanelClientBuscar panelBuscar = ControladorClientBuscar.getPanelBuscar();
        FrameUtils.showPanel(vista, panelBuscar);
    }

    public static void searchByDni(String dni) {
        PanelClient panel = ControladorClientBuscar.getPanelClient();
        panel.jblLoading.setText("Buscando datos...");
        String token = EnvVariables.getInstance().get("TOKEN_RENIEC");
        Map<String, String> headers = Map.of("Authorization",
                String.format("Bearer %s", token));
        CompletableFuture<String> getResponseFuture = HttpUtils
                .makeGetRequest(String.format("https://api.apis.net.pe/v2/reniec/dni?numero=%s", dni), headers);

        Gson gson = new Gson();
        getResponseFuture.thenAccept(response -> {
            ResponseByCliente res = gson.fromJson(response, ResponseByCliente.class);
            panel.jblLoading.setText("");
            panel.jtxtNombreClienteAgregar.setText(
                    String.format("%s %s %s", res.getNombres(), res.getApellidoPaterno(), res.getApellidoMaterno()));
        }).join();
    }

    public static void agregar() {
        PanelClient panel = ControladorClientBuscar.getPanelClient();
        Cliente cli = new Cliente();
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
            if (!response.isSuccess()) {
                Messages.show("Error: No se pudo encontrar el plan con el nombre " + selectedPlanName);
                return;
            }
            Plan plan = response.getData();
            if (plan == null) {
                Messages.show("Error: El plan recuperado es nulo.");
                return;
            }

            cli.setDni(Integer.parseInt(panel.jtxtDniClienteAgregar.getText()));
            cli.setCreated_At(new Date());

            Date fechaInicio = cli.getCreated_At();
            Date fechaFinal = DateUtils.addDays(fechaInicio, plan.getDurationDays());
            cli.setSubscription_until(fechaFinal);

            cli.setFullName(panel.jtxtNombreClienteAgregar.getText());
            cli.setEmail(panel.jtxtDireccionCorreoAdd.getText());
            cli.setDireccion(panel.jtxtDireccionClienteAdd.getText());
            cli.setPhone(Integer.parseInt(panel.jtxtTelefonoClienteAdd.getText()));

            Response<Cliente> res = CRUDCliente.getInstance().create(cli);
            if (res.isSuccess()) {
                Cliente clienteCreado = res.getData();

                // Crear el Payment asociado
                Payment payment = new Payment(new Date(), generateTransactionCode(), clienteCreado, plan);
                Response<Payment> paymentResponse = CRUDPayment.getInstance().create(payment);

                if (paymentResponse.isSuccess()) {
                    JTextField[] inputs = {panel.jtxtDniClienteAgregar, panel.jtxtNombreClienteAgregar,
                        panel.jtxtDireccionClienteAdd,
                        panel.jtxtDireccionCorreoAdd, panel.jtxtTelefonoClienteAdd};
                    FrameUtils.clearInputs(inputs);
                    Messages.show("Cliente y pago creados con exito");
                    String pdfPath = "payment.pdf";
                    String messageEmail = String.format("Gracias por tu dinero %s", payment.getCliente().getFullName());
                    try {
                        Utils.generatePaymentPDF(payment, pdfPath);
                        Utils.sendMailWithPdf(
                                messageEmail,
                                payment.getCliente().getEmail(), "gracias por tu dinero",
                                pdfPath);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                } else {
                    Messages.show("Error al crear el pago: " + paymentResponse.getMessage());
                }
            } else {
                Messages.show(response.getMessage());
            }
        }
    }

    public static CongelarPlan getWindowCongelar() {
        if (windowCongelar == null) {
            windowCongelar = new CongelarPlan();
        }
        return windowCongelar;
    }

    public static void showWindowCongelar() {
        PanelClientBuscar panel = ControladorClientBuscar.getPanelBuscar();
        String dniStr = panel.jtxtDniCliente.getText();

        if (dniStr.isEmpty()) {
            Messages.show("Por favor, ingrese un DNI válido");
            return;
        }

        CongelarPlan vista = ControladorClientBuscar.getWindowCongelar();
        FrameUtils.showWindow(vista, "Congelar Membresía");

        try {
            int dniCliente = Integer.parseInt(dniStr);
            Response<Cliente> response = CRUDCliente.getInstance().read(dniCliente);
            if (response.isSuccess()) {
                Cliente cliente = response.getData();
                vista.jtxtDniCliente.setText(String.valueOf(cliente.getDni()));

                vista.jbtnCongelar.addActionListener(e -> congelarPlan());

            } else {
                Messages.show("Error al obtener el cliente: " + response.getMessage());
            }
        } catch (NumberFormatException exception) {
            Messages.show("Error, el DNI debe ser un número válido");
        }
    }

    public static void congelarPlan() {
        CongelarPlan vista = ControladorClientBuscar.getWindowCongelar();
        String dniStr = vista.jtxtDniCliente.getText();
        String diasCongelacionStr = vista.jtxtCongelarDays.getText();

        if (dniStr.isEmpty() || diasCongelacionStr.isEmpty()) {
            Messages.show("Por favor, llene todos los campos");
            return;
        }

        if (!StringUtils.isInteger(diasCongelacionStr)) {
            Messages.show("Por favor, ingrese un número válido para los días de congelación");
            return;
        }

        int diasCongelacion = Integer.parseInt(diasCongelacionStr);
        if (diasCongelacion >= 15) {
            Messages.show("El número de días de congelación debe ser menor a 15");
            return;
        }

        try {
            int dniCliente = Integer.parseInt(dniStr);

            Response<Cliente> response = CRUDCliente.getInstance().read(dniCliente);
            if (response.isSuccess()) {
                Cliente cliente = response.getData();
                Date currentEndDate = cliente.getSubscription_until();
                Date newEndDate = DateUtils.addDays(currentEndDate, diasCongelacion);
                cliente.setSubscription_until(newEndDate);
                cliente.setIsFrozen(Cliente.IsFrozen.SI);

                // Actualizar en la base de datos
                Response<Cliente> updateResponse = CRUDCliente.getInstance().update(cliente);
                if (updateResponse.isSuccess()) {
                    Messages.show("Plan congelado exitosamente");
                    vista.dispose();
                } else {
                    Messages.show("Error al actualizar el cliente: " + updateResponse.getMessage());
                }
            } else {
                Messages.show("Cliente no encontrado: " + response.getMessage());
            }
        } catch (NumberFormatException exception) {
            Messages.show("Error, el DNI debe ser un número válido");
        }
    }
}
