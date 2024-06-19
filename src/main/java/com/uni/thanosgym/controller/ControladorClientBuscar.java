package com.uni.thanosgym.controller;

import com.uni.thanosgym.dao.CRUDCliente;
import com.uni.thanosgym.dao.CRUDPayment;
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
import com.google.gson.Gson;
import com.uni.thanosgym.utils.EnvVariables;
import com.uni.thanosgym.utils.HttpUtils;
import com.uni.thanosgym.utils.ResponseByReniec;
import com.uni.thanosgym.utils.Utils;

import javax.swing.*;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ControladorClientBuscar {

    public static PanelClientBuscar panelBuscar;
    public static PanelClient panelClient;
    public static boolean panelAgregarIsRendered = false;
    public static boolean panelBuscarIsRendered = false;
    public static List<Cliente> listaClientes = new ArrayList<>();
    public static boolean isFull = false;
    private static Cliente clientTarget = null;

    public static void showPanelBuscar() {
        if (ControladorPlan.getListaPlanes().isEmpty()) {
            Messages.show("No hay planes registrados, por favor registre un plan primero");
            return;
        }
        MainWindow vista = ControladorMainWindow.getMainWindow();
        PanelClientBuscar panel = getPanelBuscar();
        if (!panelBuscarIsRendered) {
            FrameUtils.addOnClickEvent(panel.jbtnEditar, ControladorClientBuscar::editMode);
            FrameUtils.addOnClickEvent(panel.jbtnBoletas, ControladorWindowClients::showWindow);
            FrameUtils.addOnClickEvent(panel.jbtnRenovar, ControladorClientBuscar::renovar);
            FrameUtils.addOnClickEvent(panel.jbtnRegistrar, ControladorClientBuscar::showPanelClientAgregar);
            FrameUtils.addOnClickEvent(panel.jbtnCongelar, ControladorClientBuscar::congelar);
            FrameUtils.addOnClickEvent(panel.jbtnBuscarCliente, ControladorClientBuscar::buscarPorDni);

            isClientTargeted(false);
            isClientEditable(false);

            panelBuscarIsRendered = true;
        }
        FrameUtils.showPanel(vista, panel);
    }

    private static void buscarPorDni() {
        isClientEditable(false);
        PanelClientBuscar panel = ControladorClientBuscar.getPanelBuscar();
        String dniString = panel.jtxtDniCliente.getText();
        FrameUtils.removeAllEvents(panel.jbtnEditar);
        FrameUtils.addOnClickEvent(panel.jbtnEditar, ControladorClientBuscar::editMode);
        panel.jbtnEditar.setText("Editar");

        // validaciones
        if (dniString.isEmpty()) {
            Messages.show("Por favor, digite un DNI");
            return;
        }
        if (!StringUtils.isValidDni(dniString)) {
            Messages.show("El DNI debe ser un número de 8 dígitos");
            return;
        }

        // busqueda ahorrativa xd
        int dniCliente = Integer.parseInt(dniString);
        if (listaClientes.isEmpty()) {
            Response<Cliente> resCliente = CRUDCliente.getInstance().getByDni(dniCliente);
            if (!resCliente.isSuccess()) {
                Messages.show("El cliente no está registrado");
                limpiarCamposBusqueda();
                return;
            }
            clientTarget = resCliente.getData();
            isClientTargeted(true);
            listaClientes.add(clientTarget);
        } else {
            Cliente clienteFound = listaClientes.stream().filter(c -> c.getDni() == dniCliente).findFirst()
                    .orElse(null);
            if (clienteFound != null) {
                isClientTargeted(true);
                clientTarget = clienteFound;
            } else {
                Response<Cliente> resCliente = CRUDCliente.getInstance().getByDni(dniCliente);
                if (!resCliente.isSuccess()) {
                    Messages.show("El cliente no está registrado");
                    limpiarCamposBusqueda();
                    return;
                }
                clientTarget = resCliente.getData();
                isClientTargeted(true);
                listaClientes.add(clientTarget);
            }
        }

        panel.jtxtNombreCliente.setText(clientTarget.getFullName());
        panel.jtxtDireccionCliente.setText(clientTarget.getDireccion());
        panel.jtxtTelefonoCliente.setText(String.valueOf(clientTarget.getPhone()));

        if (clientTarget.getIsFrozen() == Cliente.Frozen.SI) {
            panel.jPanelEstado.setBackground(Color.CYAN);
            Messages.show("El plan esta congelado");
            return;
        }
        Response<Payment> paymentResponse = CRUDPayment.getInstance().getByCliente(clientTarget.getId());
        if (!paymentResponse.isSuccess()) {
            Messages.show("No se pudo encontrar el pago");
            return;
        }

        if (new Date().after(clientTarget.getSubscription_until())) {
            panel.jPanelEstado.setBackground(Color.RED);
            panel.jtxtPlanActual.setText("Plan vencido");
        } else {
            panel.jPanelEstado.setBackground(Color.GREEN);
            Payment lastPayment = paymentResponse.getDataList().getLast();
            panel.jtxtPlanActual.setText(lastPayment.getPlan().getName());
        }

    }

    public static void editMode() {
        PanelClientBuscar panel = ControladorClientBuscar.getPanelBuscar();
        panel.jbtnEditar.setText("Guardar");
        FrameUtils.removeAllEvents(panel.jbtnEditar);
        FrameUtils.addOnClickEvent(panel.jbtnEditar, ControladorClientBuscar::actualizar);
        isClientEditable(true);
    }

    private static void isClientTargeted(boolean flag) {
        PanelClientBuscar panel = ControladorClientBuscar.getPanelBuscar();
        panel.jbtnEditar.setVisible(flag);
        panel.jbtnCongelar.setVisible(flag);
        panel.jbtnRenovar.setVisible(flag);
    }

    private static void isClientEditable(boolean flag) {
        PanelClientBuscar panel = ControladorClientBuscar.getPanelBuscar();
        panel.jtxtNombreCliente.setEnabled(flag);
        panel.jtxtDireccionCliente.setEnabled(flag);
        panel.jtxtTelefonoCliente.setEnabled(flag);
        panel.jtxtNombreCliente.requestFocus();
    }

    private static void actualizar() {
        PanelClientBuscar panel = ControladorClientBuscar.getPanelBuscar();

        String phone = panel.jtxtTelefonoCliente.getText();
        String nombres = panel.jtxtNombreCliente.getText();
        String direccion = panel.jtxtDireccionCliente.getText();
        if (!StringUtils.isValidPhone(phone)) {
            Messages.show("Telefono con formato incorrecto");
            return;
        }

        if (clientTarget.getFullName().equals(nombres) && clientTarget.getDireccion().equals(direccion)
                && clientTarget.getPhone() == Integer.parseInt(phone)) {
            Messages.show("No se ha realizado ningun cambio");
            isClientEditable(false);
            FrameUtils.removeAllEvents(panel.jbtnEditar);
            FrameUtils.addOnClickEvent(panel.jbtnEditar, ControladorClientBuscar::editMode);
            panel.jbtnEditar.setText("Editar");
            return;
        }
        int idx = listaClientes.indexOf(clientTarget);
        if (!nombres.equals(clientTarget.getFullName())) {
            clientTarget.setFullName(panel.jtxtNombreCliente.getText());
        }
        if (!direccion.equals(clientTarget.getDireccion())) {
            clientTarget.setDireccion(panel.jtxtDireccionCliente.getText());
        }
        if (!phone.equals(String.valueOf(clientTarget.getPhone()))) {
            clientTarget.setPhone(Integer.parseInt(phone));
        }
        Response<Cliente> response = CRUDCliente.getInstance().update(clientTarget);
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return;
        }
        listaClientes.set(idx, clientTarget);
        FrameUtils.removeAllEvents(panel.jbtnEditar);
        FrameUtils.addOnClickEvent(panel.jbtnEditar, ControladorClientBuscar::editMode);
        panel.jbtnEditar.setText("Editar");
        isClientEditable(false);
        Messages.show("Cliente actualizado con exito");
    }

    private static int generateTransactionCode() {
        return (int) (Math.random() * 1000000);
    }

    public static void showPanelClientAgregar() {
        MainWindow vista = ControladorMainWindow.getMainWindow();
        PanelClient panel = ControladorClientBuscar.getPanelClientAgregar();
        FrameUtils.showPanel(vista, panel);
        panel.jtxtDniClienteAgregar.requestFocus();
        FrameUtils.addEnterEvent(panel.jtxtDniClienteAgregar, () -> {
            String dni = panel.jtxtDniClienteAgregar.getText();
            ControladorClientBuscar.searchByDni(dni);
        });
        panel.jtxtNombreClienteAgregar.setEnabled(false);
        panel.jtxtNombreClienteAgregar.setBackground(new Color(240, 240, 240));
        if (!panelAgregarIsRendered) {
            FrameUtils.addOnClickEvent(panel.jbtnAniadir, ControladorClientBuscar::agregar);
            FrameUtils.addEnterEvent(panel.jtxtTelefonoClienteAdd, ControladorClientBuscar::agregar);
            FrameUtils.addOnClickEvent(panel.jbtnCancelar, ControladorClientBuscar::showPanelClientBuscar);
            panelAgregarIsRendered = true;
        }
        fillComboPlanes(ControladorPlan.getListaPlanes(), panel.jcbxPlanRegistro);
    }

    private static void fillComboPlanes(List<Plan> listaPlanes, JComboBox<ComboItem> jcbxPlanRegistro) {
        for (Plan plan : listaPlanes) {
            jcbxPlanRegistro.addItem(new ComboItem(plan.getId(), plan.getName()));
        }
    }

    public static void showPanelClientBuscar() {
        MainWindow vista = ControladorMainWindow.getMainWindow();
        PanelClientBuscar panelBuscar = ControladorClientBuscar.getPanelBuscar();
        FrameUtils.showPanel(vista, panelBuscar);
    }

    public static void searchByDni(String dni) {
        PanelClient panel = ControladorClientBuscar.getPanelClientAgregar();
        panel.jblLoading.setText("Buscando datos...");
        String token = EnvVariables.getInstance().get("TOKEN_RENIEC");
        Map<String, String> headers = Map.of("Authorization",
                String.format("Bearer %s", token));
        CompletableFuture<String> getResponseFuture = HttpUtils
                .makeGetRequest(String.format("https://api.apis.net.pe/v2/reniec/dni?numero=%s", dni), headers);

        Gson gson = new Gson();
        getResponseFuture.thenAccept(response -> {
            ResponseByReniec res = gson.fromJson(response, ResponseByReniec.class);
            panel.jblLoading.setText("");
            panel.jtxtNombreClienteAgregar.setText(
                    String.format("%s %s %s", res.getNombres(), res.getApellidoPaterno(), res.getApellidoMaterno()));
        }).join();
    }

    public static void agregar() {
        PanelClient panel = ControladorClientBuscar.getPanelClientAgregar();

        String dni = panel.jtxtDniClienteAgregar.getText();
        String phone = panel.jtxtTelefonoClienteAdd.getText();
        String nombre = panel.jtxtNombreClienteAgregar.getText();

        if (dni.isEmpty() || nombre.isEmpty() || phone.isEmpty()) {
            Messages.show("Por favor, llene todos los campos");
            return;
        }
        if (!StringUtils.isValidDni(dni)) {
            Messages.show("El DNI debe ser un número de 8 dígitos");
            FrameUtils.clearInputs(panel.jtxtDniClienteAgregar);
            return;
        }
        if (!StringUtils.isValidPhone(phone)) {
            Messages.show("Telefono con formato incorrecto");
            FrameUtils.clearInputs(panel.jtxtTelefonoClienteAdd);
            return;
        }
        ComboItem item = (ComboItem) panel.jcbxPlanRegistro.getSelectedItem();
        Plan plan = ControladorPlan.getListaPlanes().stream().filter(p -> p.getId() == item.getId()).findFirst()
                .orElse(null);
        Cliente cli = new Cliente();
        cli.setDni(Integer.parseInt(dni));
        cli.setCreated_At(new Date());
        Date fechaFinal = DateUtils.addDays(cli.getCreated_At(), plan.getDurationDays());
        cli.setSubscription_until(fechaFinal);

        cli.setFullName(panel.jtxtNombreClienteAgregar.getText());
        cli.setEmail(panel.jtxtDireccionCorreoAdd.getText());
        cli.setDireccion(panel.jtxtDireccionClienteAdd.getText());
        cli.setPhone(Integer.parseInt(panel.jtxtTelefonoClienteAdd.getText()));

        Response<Cliente> res = CRUDCliente.getInstance().create(cli);
        if (!res.isSuccess()) {
            Messages.show("Error al crear el cliente: " + res.getMessage());
            return;
        }
        Cliente clienteCreado = res.getData();
        listaClientes.add(clienteCreado);

        // Crear el Payment asociado
        Payment payment = new Payment(new Date(), generateTransactionCode(), clienteCreado, plan);
        Response<Payment> paymentResponse = CRUDPayment.getInstance().create(payment);

        if (!paymentResponse.isSuccess()) {
            Messages.show("Error al crear el pago: " + paymentResponse.getMessage());
            return;
        }
        JTextField[] inputs = { panel.jtxtDniClienteAgregar, panel.jtxtNombreClienteAgregar,
                panel.jtxtDireccionClienteAdd,
                panel.jtxtDireccionCorreoAdd, panel.jtxtTelefonoClienteAdd };
        FrameUtils.clearInputs(inputs);
        Messages.show("Cliente y pago creados con exito");
        String pdfPath = "payment.pdf";
        String messageEmail = String.format("Gracias por tu dinero %s", payment.getCliente().getFullName());
        Utils.generatePaymentPDF(payment, pdfPath);
        Utils.sendMailWithPdf(
                messageEmail,
                payment.getCliente().getEmail(), "gracias por tu dinero",
                pdfPath);
    }

    public static void renovar() {
        JFrame frame= new JFrame();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setBackground(new Color(255, 255, 255));
        JComboBox<ComboItem> jcbxPlanes = new JComboBox<>();
        jcbxPlanes.setBounds(50, 50, 300, 30);
        fillComboPlanes(ControladorPlan.getListaPlanes(), jcbxPlanes);
        frame.add(jcbxPlanes);

        FrameUtils.addOnClickEvent(jcbxPlanes, () -> {
            ComboItem item = (ComboItem) jcbxPlanes.getSelectedItem();
            Plan plan = ControladorPlan.getListaPlanes().stream().filter(p -> p.getId() == item.getId()).findFirst()
                    .orElse(null);
            Date fechaFinal = DateUtils.addDays(clientTarget.getSubscription_until(), plan.getDurationDays());
            int idx = listaClientes.indexOf(clientTarget);
            clientTarget.setSubscription_until(fechaFinal);
            Response<Cliente> response = CRUDCliente.getInstance().update(clientTarget);
            if (!response.isSuccess()) {
                Messages.show("Error al renovar la membresía: " + response.getMessage());
                return;
            }
            listaClientes.set(idx, clientTarget);
            Messages.show("Membresía renovada con exito");
            frame.dispose();
        });
        FrameUtils.showWindow(frame, "Selecciona un plan");
    }

    public static void congelar() {
        // validaciones
        String answer = Messages.input("Indica cuantos días deseas congelar la membresía");
        if (answer.isEmpty()) {
            Messages.show("Por favor ingrese un numero");
            return;
        }
        if (!StringUtils.isInteger(answer)) {
            Messages.show("Por favor ingrese un numero valido");
            return;
        }

        // agregar dias para el congelamiento de membresia
        int days = Integer.parseInt(answer);

        if (days > 15) {
            Messages.show("No puedes congelar la membresía por más de 15 días");
            return;
        }
        Date currentSubscriptionUntil = clientTarget.getSubscription_until();
        clientTarget.setSubscription_until(DateUtils.addDays(currentSubscriptionUntil, days));

        // verificar si en realidad deseas congelar la membresia
        String warningMessage = String.format("¿Estás seguro de congelar la membresía de %s por %d días?",
                clientTarget.getFullName(), days);
        boolean sure = Messages.confirm(warningMessage, "Congelar membresía");
        if (!sure)
            return;

        // actualizar el cliente en la base de datos
        clientTarget.setIsFrozen(Cliente.Frozen.SI);
        Response<Cliente> response = CRUDCliente.getInstance().update(clientTarget);
        if (!response.isSuccess()) {
            Messages.show("Error al congelar la membresía: " + response.getMessage());
            return;
        }

        // actualizar el cliente en la lista local
        int idx = listaClientes.indexOf(clientTarget);
        ControladorClientBuscar.listaClientes.set(idx, clientTarget);
        Messages.show("Membresia congelada con exito por " + days + " días");
    }

    public static PanelClient getPanelClientAgregar() {
        if (panelClient == null) {
            panelClient = new PanelClient();
        }
        return panelClient;
    }

    public static PanelClientBuscar getPanelBuscar() {
        if (panelBuscar == null) {
            panelBuscar = new PanelClientBuscar();
        }
        return panelBuscar;
    }

    public static void limpiarCamposBusqueda() {
        clientTarget = null;
        panelBuscar.jbtnCongelar.setVisible(false);
        panelBuscar.jbtnEditar.setVisible(false);
        panelBuscar.jbtnRenovar.setVisible(false);
        panelBuscar.jPanelEstado.setBackground(Color.WHITE);
        PanelClientBuscar panel = ControladorClientBuscar.getPanelBuscar();
        JTextField[] inputs = { panel.jtxtDniCliente, panel.jtxtNombreCliente, panel.jtxtDireccionCliente,
                panel.jtxtTelefonoCliente, panel.jtxtPlanActual };
        FrameUtils.clearInputs(inputs);
    }

    public static class ComboItem {
        private int id;
        private String name;

        public ComboItem(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
