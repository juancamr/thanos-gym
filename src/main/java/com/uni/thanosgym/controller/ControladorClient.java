package com.uni.thanosgym.controller;

import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.utils.Utils;
import com.uni.thanosgym.dao.CRUDCliente;
import com.uni.thanosgym.dao.CRUDPlan;
import java.util.Date;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.model.Cliente;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.DateUtils;
import com.uni.thanosgym.utils.EnvVariables;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.view.MainWindow;
import com.uni.thanosgym.view.PanelClient;

import java.awt.Color;
import java.util.List;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import com.uni.thanosgym.utils.HttpUtils;
import com.uni.thanosgym.utils.ResponseByCliente;
import com.google.gson.Gson;
import com.uni.thanosgym.dao.CRUDPayment;
import com.uni.thanosgym.model.Payment;
import java.util.Random;

import javax.swing.JTextField;

public class ControladorClient {

    public static PanelClient panel;
    public static boolean panelIsRendered = false;

    public static void showPanel() {
        MainWindow vista = ControladorMainWindow.getMainWindow();
        PanelClient panel = ControladorClient.getPanel();
        FrameUtils.showPanel(vista, panel);
        panel.jtxtDniClienteAgregar.requestFocus();
        FrameUtils.addEnterEvent(panel.jtxtDniClienteAgregar, () -> {
            String dni = panel.jtxtDniClienteAgregar.getText();
            ControladorClient.searchByDni(dni);
        });
        panel.jtxtNombreClienteAgregar.setEnabled(false);
        panel.jtxtNombreClienteAgregar.setBackground(new Color(240, 240, 240));
        if (!panelIsRendered) {
            FrameUtils.addOnClickEvent(panel.jbtnAgregar, ControladorClient::agregar);
            FrameUtils.addEnterEvent(panel.jtxtTelefonoClienteAdd, ControladorClient::agregar);
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

    public static void searchByDni(String dni) {
        PanelClient panel = ControladorClient.getPanel();
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

    public static PanelClient getPanel() {
        if (panel == null) {
            panel = new PanelClient();
        }
        return panel;
    }

    public static void agregar() {
        PanelClient panel = ControladorClient.getPanel();
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
                    JTextField[] inputs = { panel.jtxtDniClienteAgregar, panel.jtxtNombreClienteAgregar,
                            panel.jtxtDireccionClienteAdd,
                            panel.jtxtDireccionCorreoAdd, panel.jtxtTelefonoClienteAdd };
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

    private static int generateTransactionCode() {
        return new Random().nextInt(100000);
    }

}
