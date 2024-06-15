package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;
import com.uni.thanosgym.model.Payment;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.model.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDPayment extends BaseCrud<Payment> {

    public static CRUDPayment crudPayment;

    private CRUDPayment() {
    }

    public static CRUDPayment getInstance() {
        if (crudPayment == null) {
            crudPayment = new CRUDPayment();
        }
        return crudPayment;
    }

    public Response<Payment> create(Payment payment) {
        try {
            // Verificar si el cliente ya existe por email
            Cliente cliente = payment.getCliente();
            PreparedStatement psCliente = connection.prepareStatement(Querys.cliente.getByEmail);
            psCliente.setString(1, cliente.getEmail());
            ResultSet rsCliente = psCliente.executeQuery();

            if (rsCliente.next()) {
                // Cliente ya existe, obtener su ID
                cliente.setId(rsCliente.getInt(Cliente.idField));
            } else {
                // Cliente no existe, crearlo
                CRUDCliente crudCliente = CRUDCliente.getInstance();
                Response<Cliente> responseCliente = crudCliente.create(cliente);
                if (!responseCliente.isSuccess()) {
                    return new Response<>(false, "No se pudo crear el cliente: " + responseCliente.getMessage());
                }
                cliente = responseCliente.getData();
            }

            // Crear el pago con el cliente existente o nuevo
            payment.setCliente(cliente);

            // Llamar a baseCreate para insertar el pago en la base de datos
            return baseCreate(payment, Querys.payment.create);
        } catch (SQLException e) {
            return new Response<>(false, "Error: " + e.getMessage());
        }
    }  

    public Response<Payment> getById(int id) {
        return baseGetById(Querys.payment.get, id);
    }

    public Response<Payment> delete(int id) {
        return baseDeleteById(Querys.payment.delete, id);
    }

    public Response<Payment> getByCliente(int clientId) {
        try {
            ps = connection.prepareStatement(Querys.payment.getByCliente);
            ps.setInt(1, clientId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Payment payment = generateObject(rs);
                return new Response<>(true, "Pago encontrado", payment);
            } else {
                return new Response<>(false, "No se encontró el pago");
            }
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }


    @Override
    public Payment generateObject(ResultSet rs) throws SQLException {
        Response<Cliente> resCliente = CRUDCliente.getInstance().getById(rs.getInt(4));
        Response<Plan> resPlan = CRUDPlan.getInstance().getById(rs.getInt(5));
        return new Payment(rs.getInt(1), rs.getDate(2), rs.getInt(3), resCliente.getData(),
                resPlan.getData());
    }

    @Override
    public void sendObject(String consulta, Payment data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, StringUtils.parseDate(data.getCreatedAt()));
        ps.setInt(2, data.getCliente().getId());
        ps.setInt(3, data.getPlan().getId());
        ps.setInt(4, data.getTransactionCode());
    }

}
