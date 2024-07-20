package com.uni.thanosgym.controllers;

import com.uni.thanosgym.utils.DateUtils;
import com.uni.thanosgym.utils.EnvVariables;
import com.uni.thanosgym.utils.HttpUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.ResponseByReniec;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import javax.swing.JButton;
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

    public static void buscarReniec(String dni, JTextField nombreInput, JButton buscarReniec) {
        if (dni.isEmpty()) {
            Messages.show("Ingrese un DNI");
            return;
        }
        if (!StringUtils.isValidDni(dni)) {
            Messages.show("Ingrese un DNI valido");
            return;
        }
        // String token = EnvVariables.getInstance().get("TOKEN_RENIEC");
        String token = "apis-token-8973.-ycNRmJOAHUAbtJAr30rzJPKx9v3unU1";
        Map<String, String> headers = Map.of("Authorization",
                String.format("Bearer %s", token));
        CompletableFuture<String> getResponseFuture = HttpUtils
                .makeGetRequest(String.format("https://api.apis.net.pe/v2/reniec/dni?numero=%s", dni), headers);

        Utils.mostrarPantallaDeCarga(null, () -> {
            getResponseFuture.thenAccept(response -> {
                ResponseByReniec res = gson.fromJson(response, ResponseByReniec.class);
                if (res.getNombres() == null) {
                    Messages.show("No se encontro el DNI");
                    return;
                }
                nombreInput.setText(
                        String.format("%s %s %s", res.getNombres(), res.getApellidoPaterno(),
                                res.getApellidoMaterno()));
            }).join();
        });
    }

}
