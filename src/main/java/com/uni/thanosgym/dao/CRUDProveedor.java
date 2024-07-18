package com.uni.thanosgym.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.uni.thanosgym.model.Proveedor;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;

public class CRUDProveedor extends BaseCrud<Proveedor> {

    private static CRUDProveedor crudProveedor;

    public static CRUDProveedor getInstance() {
        if (crudProveedor == null) {
            crudProveedor = new CRUDProveedor();
        }
        return crudProveedor;
    }

    public Response<Proveedor> create(Proveedor proveedor) {
        return baseCreate(proveedor, Querys.proveedor.create);
    }

    public Response<Proveedor> getById(int id) {
        return baseGetById(Querys.getByIdTemplate(Proveedor.tableName), id);
    }

    public Response<Proveedor> getAll() {
        return baseGetAll(Querys.getAllTemplate(Proveedor.tableName));
    }

    @Override
    public Proveedor generateObject(ResultSet rs) throws SQLException {
        return new Proveedor.Builder()
                .setId(rs.getInt(Proveedor.idField))
                .setNombre(rs.getString(Proveedor.nombreField))
                .setRuc(rs.getString(Proveedor.rucField))
                .setPhone(rs.getString(Proveedor.phoneField))
                .setAddress(rs.getString(Proveedor.addressField))
                .build();
    }

    @Override
    public void sendObject(String consulta, Proveedor data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, data.getNombre());
        ps.setString(2, data.getRuc());
        ps.setString(3, data.getPhone());
        ps.setString(4, data.getAddress());
    }
}
