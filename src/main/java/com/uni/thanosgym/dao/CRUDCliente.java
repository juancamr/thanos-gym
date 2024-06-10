package com.uni.thanosgym.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.model.Cliente;
import com.uni.thanosgym.model.Plan;

public class CRUDCliente extends BaseCrud<Cliente> {

    private static CRUDCliente crudCliente;

    private CRUDCliente() {
    }

    public static CRUDCliente getInstance() {
        if (crudCliente == null) {
            crudCliente = new CRUDCliente();
        }
        return crudCliente;
    }

    public Response<Cliente> create(Cliente cliente) {
        try {
            ps = connection.prepareStatement(Querys.cliente.getByEmail);
            ps.setString(1, cliente.getEmail());
            rs = ps.executeQuery();
            boolean[] conditions = new boolean[] { !rs.next() };
            String error = String.format("El cliente con email %s ya existe", cliente.getEmail());
            return baseCreateWithConditions(cliente, Querys.cliente.create, conditions, error);
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }

    public Response<Cliente> read(int dni) {
        return baseGetById(Querys.cliente.getByDni, dni);
    }

    public Response<Cliente> readAll() {
        return baseGetAll(Querys.cliente.getAll);
    }

    public Response<Cliente> update(Cliente cliente) {
        return baseUpdate(Querys.cliente.update, cliente);
    }

    public Response<Cliente> delete(int clienteId) {
        return baseDeleteById(Querys.cliente.delete, clienteId);
    }

    @Override
    public Cliente generateObject(ResultSet rs) throws SQLException {
        Response<Plan> response = CRUDPlan.getInstance().getById(rs.getInt(Plan.idField));
        return new Cliente(
                rs.getInt(Cliente.idField),
                response.getData(),
                rs.getInt(Cliente.dniField),
                rs.getDate(Cliente.createdAtField),
                rs.getDate(Cliente.subscriptionSinceField),
                rs.getString(Cliente.fullNameField),
                rs.getString(Cliente.emailField),
                rs.getString(Cliente.addressField),
                rs.getInt(Cliente.phoneField));
    }

    @Override
    public Cliente sendObject(String consulta, Cliente data) throws SQLException {
        ps = connection.prepareStatement(consulta);
        ps.setInt(1, data.getPlan().getId());
        ps.setInt(2, data.getDni());
        ps.setString(3, StringUtils.parseDate(data.getCreated_At()));
        ps.setString(4, StringUtils.parseDate(data.getSubscription_since()));
        ps.setString(5, data.getFullName());
        ps.setString(6, data.getEmail());
        ps.setString(7, data.getDireccion());
        ps.setInt(8, data.getPhone());
        ps.executeUpdate();
        ps.close();
        return data;
    }
}
