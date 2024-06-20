package com.uni.thanosgym.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.uni.thanosgym.model.Admin;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;
import com.uni.thanosgym.utils.UserPreferences;
import com.uni.thanosgym.model.Utility;

public class CRUDUtilidad extends BaseCrud<Utility> {
    public static CRUDUtilidad crudUtility;

    public static CRUDUtilidad getInstance() {
        if (crudUtility == null) {
            crudUtility = new CRUDUtilidad();
        }
        return crudUtility;
    }

    public Response<Utility> create(Utility utility) {
        return baseCreate(utility, Querys.utility.create);
    }

    public Response<Utility> getAll() {
        return baseGetAll(Querys.getAllTemplate(Utility.tableName));
    }

    public Response<Utility> getById(int id) {
        return baseGetById(Querys.getByIdTemplate(Utility.tableName), id);
    }

    public Response<Utility> delete(int id) {
        return baseDeleteById(Querys.deleteTemplate(Utility.tableName), id);
    }

    public Response<Utility> update(Utility utility) {
        try {
            sendObject(Querys.utility.update, utility);
            ps.setInt(4, utility.getId());
            ps.executeUpdate();
            ps.close();
            return new Response<Utility>(true, "Datos actualizados con exito");
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }

    @Override
    public Utility generateObject(ResultSet rs) throws SQLException {
        Response<Admin> resAdmin = CRUDAdministrador.getInstance().getById(rs.getInt(Utility.adminIdField));
        return new Utility.Builder()
                .setId(rs.getInt(Utility.idField))
                .setNombre(rs.getString(Utility.nombreField))
                .setPeso(rs.getInt(Utility.pesoField))
                .setAdmin(resAdmin.getData())
                .setCantidad(rs.getInt(Utility.cantidadField))
                .setPhotoUrl(rs.getString(Utility.photoUrlField))
                .build();
    }

    @Override
    public void sendObject(String consulta, Utility data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, data.getNombre());
        ps.setInt(2, data.getPeso());
        ps.setInt(3, data.getCantidad());
        ps.setInt(4, data.getAdmin().getId());
    }
}
