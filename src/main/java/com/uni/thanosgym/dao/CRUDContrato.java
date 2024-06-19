package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;
import com.uni.thanosgym.model.Contrato;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.model.Cliente;
import com.uni.thanosgym.utils.Querys.payment;

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

    public Response<Contrato> create(Contrato payment) {
        try {
            Cliente cliente = payment.getCliente();
            PreparedStatement psCliente = connection.prepareStatement(Querys.cliente.getByEmail);
            psCliente.setString(1, cliente.getEmail());
            ResultSet rsCliente = psCliente.executeQuery();

            if (rsCliente.next()) {
                cliente.setId(rsCliente.getInt(1));
            } else {
                // Cliente no existe, crearlo
                CRUDCliente crudCliente = CRUDCliente.getInstance();
                Response<Cliente> responseCliente = crudCliente.create(cliente);
                if (!responseCliente.isSuccess()) {
                    return new Response<>(false, "No se pudo crear el cliente: " + responseCliente.getMessage());
                }
                cliente = responseCliente.getData();
            }

            payment.setCliente(cliente);
            return baseCreate(payment, Querys.payment.create);
        } catch (SQLException e) {
            return new Response<>(false, "Error: " + e.getMessage());
        }
    }

    public Response<Contrato> getById(int id) {
        return baseGetById(Querys.payment.get, id);
    }

    public Response<Contrato> delete(int id) {
        return baseDeleteById(Querys.payment.delete, id);
    }

    public Response<Contrato> getByCliente(int clientId) {
        try {
            ps = connection.prepareStatement(payment.getByCliente);
            ps.setInt(1, clientId);
            rs = ps.executeQuery();

            List<Contrato> payments = new ArrayList<>();
            while (rs.next()) {
                Contrato payment = generateObject(rs);
                payments.add(payment);
            }
            return new Response<>(true, "Lista de pagos obtenida correctamente", payments);
        } catch (SQLException e) {
            return new Response<>(false, "Error al obtener los pagos del cliente: " + e.getMessage());
        }
    }

    @Override
    public Contrato generateObject(ResultSet rs) throws SQLException {
        Response<Cliente> resCliente = CRUDCliente.getInstance().getById(rs.getInt(3));
        Response<Plan> resPlan = CRUDPlan.getInstance().getById(rs.getInt(4));
        return new Contrato(rs.getInt(1), rs.getDate(2), rs.getInt(3), resCliente.getData(),
                resPlan.getData());
    }

    @Override
    public void sendObject(String consulta, Contrato data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, StringUtils.parseDate(data.getCreatedAt()));
        ps.setInt(2, data.getCliente().getId());
        ps.setInt(3, data.getPlan().getId());
        ps.setInt(4, data.getTransactionCode());
    }

}
