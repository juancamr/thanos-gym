package com.uni.thanosgym.controllers;

import com.uni.thanosgym.utils.DateUtils;
import com.uni.thanosgym.utils.EnvVariables;
import com.uni.thanosgym.utils.HttpUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.ResponseByReniec;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.swing.JTextField;

import java.io.File;

import com.google.gson.Gson;
import com.uni.thanosgym.dao.CRUDCliente;
import com.uni.thanosgym.dao.CRUDContrato;
import com.uni.thanosgym.dao.CRUDPlan;
import com.uni.thanosgym.model.Client;
import com.uni.thanosgym.model.Contrato;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.utils.Uploader;
import com.uni.thanosgym.utils.UserPreferences;
import com.uni.thanosgym.utils.Utils;
import com.uni.thanosgym.view.dialogs.ClientData;
import com.uni.thanosgym.view.routes.PanelClient.ComboItemPlan;

public class ClientController {

    private static Gson gson = new Gson();

    public static boolean buscar(String dni) {
        if (dni.isEmpty()) {
            Messages.show("Complete todos los campos");
            return false;
        }
        if (!StringUtils.isValidDni(dni)) {
            Messages.show("Ingrese un DNI valido");
            return false;
        }
        Response<Client> resCliente = CRUDCliente.getInstance().getByDni(dni);
        if (!resCliente.isSuccess()) {
            Messages.show(resCliente.getMessage());
            return false;
        }
        Response<Contrato> resContrato = CRUDContrato.getInstance().getByClienteId(resCliente.getData().getId());
        new ClientData(resContrato.getDataList().getLast());
        return true;
    }

    public static void buscarReniec(String dni, JTextField nombreInput) {
        if (dni.isEmpty()) {
            Messages.show("Ingrese un DNI");
            return;
        }
        if (!StringUtils.isValidDni(dni)) {
            Messages.show("Ingrese un DNI valido");
            return;
        }
        //String token = EnvVariables.getInstance().get("TOKEN_RENIEC");
        String token = "apis-token-8973.-ycNRmJOAHUAbtJAr30rzJPKx9v3unU1";
        Map<String, String> headers = Map.of("Authorization",
                String.format("Bearer %s", token));
        CompletableFuture<String> getResponseFuture = HttpUtils
                .makeGetRequest(String.format("https://api.apis.net.pe/v2/reniec/dni?numero=%s", dni), headers);

        getResponseFuture.thenAccept(response -> {
            ResponseByReniec res = gson.fromJson(response, ResponseByReniec.class);
            if (res.getNombres() == null) {
                Messages.show("No se encontro el DNI");
                return;
            }
            nombreInput.setText(
                    String.format("%s %s %s", res.getNombres(), res.getApellidoPaterno(), res.getApellidoMaterno()));
        }).join();
    }

    public static boolean registrar(Map<String, Object> params) {
        String dni = params.get("dni").toString();
        String nombres = params.get("nombres").toString();
        String email = params.get("email").toString();
        String telefono = params.get("telefono").toString();
        String direccion = params.get("direccion").toString();
        String codigo = params.get("codigo").toString();
        ComboItemPlan planItem = (ComboItemPlan) params.get("plan");
        File imagen = (File) params.get("photo");

        if (dni.isEmpty() || nombres.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || email.isEmpty()
                || codigo.isEmpty()) {
            Messages.show("Complete todos los campos");
            return false;
        }
        if (!StringUtils.isValidEmail(email)) {
            Messages.show("Ingrese un email valido");
            return false;
        }
        if (!StringUtils.isValidDni(dni)) {
            Messages.show("Ingrese un DNI valido");
            return false;
        }
        if (!StringUtils.isValidPhone(telefono)) {
            Messages.show("El telefono debe ser un numero de 9 digitos.");
            return false;
        }
        if (!StringUtils.isInteger(codigo)) {
            Messages.show("Ingrese un código valido");
            return false;
        }
        Client cliente = new Client.Builder()
                .setFullName(nombres)
                .setDni(dni)
                .setEmail(email)
                .setPhone(telefono)
                .setPhotoUrl("")
                .setDireccion(direccion)
                .build();

        if (imagen != null) {
            Uploader.UploaderResponse resUploader = Uploader.uploadImage(imagen);
            if (!resUploader.isSuccess()) {
                Messages.show(resUploader.getMessage());
                imagen = null;
                return false;
            }
            cliente.setPhotoUrl(resUploader.getUrl());
            imagen = null;
        }

        Response<Client> resCliente = CRUDCliente.getInstance().create(cliente);
        if (!resCliente.isSuccess()) {
            Messages.show(resCliente.getMessage());
            return false;
        }
        cliente.setId(resCliente.getId());

        Response<Plan> resPlan = CRUDPlan.getInstance().getById(planItem.getId());
        if (!resPlan.isSuccess()) {
            Messages.show(resPlan.getMessage());
            return false;
        }
        Plan plan = resPlan.getData();
        Contrato contrato = new Contrato.Builder()
                .setCliente(cliente)
                .setPlan(plan)
                .setAdmin(UserPreferences.getData())
                .setTransactionCode(codigo)
                .setSubscriptionUntil(DateUtils.addDays(new Date(), plan.getDurationDays()))
                .build();
        Response<Contrato> resContrato = CRUDContrato.getInstance().create(contrato);

        if (!resContrato.isSuccess()) {
            Messages.show(resContrato.getMessage());
            return false;
        }

        contrato.setCreatedAt(new Date());
        enviarContrato(contrato);

        return true;
    }

    public static void enviarContrato(Contrato contrato) {
        String pdfPath = "contrato.pdf";
        String messageEmail = String.format(
                "Gracias por ser parte de Thanosgym %s, te dejamos tu contrato de membresia adjuntado en este correo.",
                contrato.getCliente().getFullName());
        Utils.generarContratoPdf(contrato, pdfPath);
        Utils.sendMailWithPdf(
                contrato.getCliente().getEmail(),
                String.format("Bienvenido %s", contrato.getCliente().getFullName()),
                messageEmail,
                pdfPath);
    }
}
