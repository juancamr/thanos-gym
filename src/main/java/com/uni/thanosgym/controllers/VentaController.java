package com.uni.thanosgym.controllers;

import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.uni.thanosgym.utils.Utils;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.utils.UserPreferences;
import com.uni.thanosgym.dao.CRUDBoleta;
import com.uni.thanosgym.dao.CRUDCliente;
import com.uni.thanosgym.dao.CRUDDetalleBoleta;
import com.uni.thanosgym.dao.CRUDProducto;
import com.uni.thanosgym.model.Boleta;
import com.uni.thanosgym.model.Client;
import com.uni.thanosgym.model.DetalleBoleta;
import com.uni.thanosgym.model.Producto;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Messages;

public class VentaController {

    public static Client setCliente(String dni) {
        if (dni.isEmpty()) {
            Messages.show("Debe completar el DNI");
            return null;
        }
        if (!StringUtils.isValidDni(dni)) {
            Messages.show("El DNI no es valido");
            return null;
        }

        Response<Client> res = CRUDCliente.getInstance().getByDni(dni);
        if (!res.isSuccess()) {
            Messages.show(res.getMessage());
            return null;
        }
        Client cliente = res.getData();

        return cliente;
    }

    public static void enviarCorreo(Boleta boleta) {
        String pdfPath = "boleta.pdf";
        String messageEmail = String.format(
                "Gracias por su compra %s, te dejamos tu boleta adjuntada en este correo.",
                boleta.getCliente().getFullName());
        Utils.generateBoletaPdf(boleta, pdfPath);
        Utils.sendMailWithPdf(
                boleta.getCliente().getEmail(),
                String.format("Bienvenido %s", boleta.getCliente().getFullName()),
                messageEmail,
                pdfPath);
    }

    public static boolean crearVenta(Map<String, Object> params) {
        if (params == null) {
            return false;
        }

        return true;
    }
}
