package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;
import com.uni.thanosgym.model.Contrato;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.model.Admin;
import com.uni.thanosgym.model.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDContrato extends BaseCrud<Contrato> {

    public static CRUDContrato crudPayment;

    private CRUDContrato() {
    }

    public static CRUDContrato getInstance() {
        if (crudPayment == null) {
            crudPayment = new CRUDContrato();
        }
        return crudPayment;
    }

    public Response<Contrato> create(Contrato contrato) {
        try {
            Client cliente = contrato.getCliente();
            PreparedStatement psCliente = connection
                    .prepareStatement(Querys.getTemplateWithConditions(Client.tableName, Client.emailField));
            psCliente.setString(1, cliente.getEmail());
            ResultSet rsCliente = psCliente.executeQuery();

            if (rsCliente.next()) {
                cliente.setId(rsCliente.getInt(1));
            } else {
                // Cliente no existe, crearlo
                CRUDCliente crudCliente = CRUDCliente.getInstance();
                Response<Client> responseCliente = crudCliente.create(cliente);
                if (!responseCliente.isSuccess()) {
                    return new Response<>(false, "No se pudo crear el cliente: " + responseCliente.getMessage());
                }
                cliente = responseCliente.getData();
            }

            contrato.setCliente(cliente);
            return baseCreate(contrato, Querys.contrato.create);
        } catch (SQLException e) {
            return new Response<>(false, "Error: " + e.getMessage());
        }
    }

    public Response<Contrato> getById(int id) {
        return baseGetById(Querys.getByIdTemplate(Contrato.tableName), id);
    }

    public Response<Contrato> delete(int id) {
        return baseDeleteById(Querys.deleteTemplate(Contrato.tableName), id);
    }

    public Response<Contrato> getByCliente(int clientId) {
        try {
            ps = connection
                    .prepareStatement(Querys.getTemplateWithConditions(Contrato.tableName, Contrato.clientIdField));
            ps.setInt(1, clientId);
            rs = ps.executeQuery();

            List<Contrato> contratos = new ArrayList<>();
            while (rs.next()) {
                Contrato payment = generateObject(rs);
                contratos.add(payment);
            }
            return new Response<>(true, "Lista de pagos obtenida correctamente", contratos);
        } catch (SQLException e) {
            return new Response<>(false, "Error al obtener los pagos del cliente: " + e.getMessage());
        }
    }

    @Override
    public Contrato generateObject(ResultSet rs) throws SQLException {
        Response<Client> resCliente = CRUDCliente.getInstance().getById(rs.getInt(Contrato.clientIdField));
        Response<Plan> resPlan = CRUDPlan.getInstance().getById(rs.getInt(Contrato.planIdField));
        Response<Admin> resAdmin = CRUDAdministrador.getInstance().getById(rs.getInt(Contrato.adminIdField));
        return new Contrato.Builder()
                .setId(rs.getInt(Contrato.idField))
                .setCliente(resCliente.getData())
                .setPlan(resPlan.getData())
                .setAdmin(resAdmin.getData())
                .setTransactionCode(rs.getString(Contrato.transactionCodeField))
                .setCreatedAt(rs.getDate(Contrato.createdAtField))
                .setSubscriptionUntil(rs.getDate(Contrato.subscriptionUntilField))
                .setIsFrozen(rs.getBoolean(Contrato.isFrozenField))
                .setFreezeCount(rs.getInt(Contrato.freezeCountField))
                .setFreezeUntil(rs.getDate(Contrato.freezeUntilField))
                .setLastFreezeDate(rs.getDate(Contrato.lastFreezeDateField))
                .build();
    }

    @Override
    public void sendObject(String consulta, Contrato data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, data.getCliente().getId());
        ps.setInt(2, data.getPlan().getId());
        ps.setInt(3, data.getAdmin().getId());
        ps.setString(4, data.getTransactionCode());
        ps.setString(5, StringUtils.parseDate(data.getSubscriptionUntil()));
    }

}
