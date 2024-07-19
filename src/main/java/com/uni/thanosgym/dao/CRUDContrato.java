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
import java.util.Date;
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
        return baseCreate(contrato, Querys.contrato.create);
    }

    public Response<Contrato> obtenerUltimosTresContratos() {
        try {
            ps = connection.prepareStatement(Querys.contrato.obtenerUltimosTresContratos);
            rs = ps.executeQuery();
            List<Contrato> contratos = new ArrayList<>();
            while (rs.next()) {
                Contrato contrato = generateObject(rs);
                contratos.add(contrato);
            }
            return new Response<Contrato>(true, contratos);
        } catch (Exception e) {
            System.out.println(e);
            return new Response<Contrato>(false);
        }
    }

    public Response<Contrato> getById(int id) {
        return baseGetById(Querys.getByIdTemplate(Contrato.tableName), id);
    }

    public Response<Contrato> delete(int id) {
        return baseDeleteById(Querys.deleteTemplate(Contrato.tableName), id);
    }

    public Response<Contrato> congelarODescongelar(Contrato contrato) {
        try {
            ps = connection.prepareStatement(Querys.contrato.updateCongelar);
            ps.setString(1, StringUtils.parseDate(contrato.getSubscriptionUntil()));
            ps.setBoolean(2, contrato.isFrozen());
            ps.setInt(3, contrato.getFreezeCount());
            ps.setString(4, StringUtils.parseDate(contrato.getSubscriptionUntil()));
            ps.setString(5, StringUtils.parseDate(contrato.getLastFreezeDate()));
            ps.setInt(6, contrato.getId());
            ps.executeUpdate();
            ps.close();
            return new Response<Contrato>(true);
        } catch (SQLException e) {
            System.out.println(e);
            return new Response<Contrato>(false, "Error al congelar el contrato");
        }
    }

    public Response<Contrato> getByClienteId(int clientId) {
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
