package com.uni.thanosgym.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;

import java.util.ArrayList;
import java.util.List;
import com.uni.thanosgym.model.Client;

public class CRUDCliente extends BaseCrud<Client> {

    private static CRUDCliente crudCliente;

    private CRUDCliente() {
    }

    public static CRUDCliente getInstance() {
        if (crudCliente == null) {
            crudCliente = new CRUDCliente();
        }
        return crudCliente;
    }

    public Response<Client> create(Client cliente) {
        try {
            ps = connection.prepareStatement(Querys.getTemplateWithConditions(Client.tableName, Client.emailField));
            ps.setString(1, cliente.getEmail());
            rs = ps.executeQuery();
            boolean noExisteUnClienteConElMismoEmail = !rs.next();
            ps = connection.prepareStatement(Querys.getTemplateWithConditions(Client.tableName, Client.dniField));
            ps.setString(1, cliente.getDni());
            rs = ps.executeQuery();
            boolean noExisteUnClienteConElMismoDni = !rs.next();
            boolean[] conditions = new boolean[] { noExisteUnClienteConElMismoEmail, noExisteUnClienteConElMismoDni };
            String error = String.format("El cliente con email %s ya existe", cliente.getEmail());
            return baseCreateWithConditions(cliente, Querys.client.create, conditions, error);
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }

    public Response<Client> getById(int id) {
        return baseGetById(Querys.getByIdTemplate(Client.tableName), id);
    }

    public Response<Client> getAll() {
        return baseGetAll(Querys.getAllTemplate(Client.tableName));
    }

    public Response<Client> getByDni(String dni) {
        return baseGetByString(Querys.getTemplateWithConditions(Client.tableName, Client.dniField), dni);
    }

    public int obtenerClientesSuscritosHoy() {
        try {
            ps = connection.prepareStatement(Querys.client.obtenerClientesSuscritosHoy);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt("contador");
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }

    }

    public int obtenerClientesSuscritosTodoElTiempo() {
        try {
            ps = connection.prepareStatement(Querys.client.obtenerCantidadClientesSuscritos);
            rs = ps.executeQuery();
            rs.next();
            return rs.getInt("num_clientes_vigentes");
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }

    public Response<Client> update(Client cliente) {
        try {
            sendObject(Querys.client.update, cliente);
            ps.setInt(7, cliente.getId());
            ps.executeUpdate();
            ps.close();
            return new Response<Client>(true);
        } catch (Exception e) {
            System.out.println(e);
            return new Response<Client>(false);
        }
    }

    public Response<Client> deleteOnlyForTesting(int id) {
        return baseDeleteById(Querys.deleteTemplate(Client.tableName), id);
    }

    @Override
    public Client generateObject(ResultSet rs) throws SQLException {
        Client cliente = new Client.Builder()
                .setId(rs.getInt(Client.idField))
                .setCreatedAt(rs.getTimestamp(Client.createdAtField))
                .setDni(rs.getString(Client.dniField))
                .setFullName(rs.getString(Client.fullNameField))
                .setEmail(rs.getString(Client.emailField))
                .setDireccion(rs.getString(Client.addressField))
                .setPhone(rs.getString(Client.phoneField))
                .setPhotoUrl(rs.getString(Client.photoUrlField))
                .build();
        return cliente;
    }

    @Override
    public void sendObject(String consulta, Client data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, data.getDni());
        ps.setString(2, data.getFullName());
        ps.setString(3, data.getEmail());
        ps.setString(4, data.getDireccion());
        ps.setString(5, data.getPhone());
        ps.setString(6, data.getPhotoUrl());
    }
}
