package com.uni.thanosgym.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.model.Cliente;

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

    public Response<Cliente> getById(int id) {
        return baseGetById(Querys.cliente.getById, id);
    }

    public Response<Cliente> read(int dni) {
        return baseGetById(Querys.cliente.getByDni, dni);
    }

    public Response<Cliente> readAll() {
        return baseGetAll(Querys.cliente.getAll);
    }

    public Response<Cliente> update(Cliente cliente) {
        try {
            sendObject(Querys.plan.update, cliente);
            ps.setInt(9, cliente.getId());
            ps.executeUpdate();
            ps.close();
            return new Response<Cliente>(true);
        } catch (Exception e) {
            System.out.println(e);
            return new Response<Cliente>(false);
        }
    }

    public Response<Cliente> delete(int id) {
        return baseDeleteById(Querys.cliente.delete, id);
    }

    @Override
    public Cliente generateObject(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getInt(Cliente.idField),
                rs.getInt(Cliente.dniField),
                rs.getDate(Cliente.createdAtField),
                rs.getDate(Cliente.subscriptionUntilField),
                rs.getString(Cliente.fullNameField),
                rs.getString(Cliente.emailField),
                rs.getString(Cliente.addressField),
                rs.getInt(Cliente.phoneField));
    }

    @Override
    public void sendObject(String consulta, Cliente data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, data.getDni());
        ps.setString(2, StringUtils.parseDate(data.getCreated_At()));
        ps.setString(3, StringUtils.parseDate(data.getSubscription_until()));
        ps.setString(4, data.getFullName());
        ps.setString(5, data.getEmail());
        ps.setString(6, data.getDireccion());
        ps.setInt(7, data.getPhone());
    }
}
