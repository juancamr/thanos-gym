package com.uni.thanosgym.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;
import com.uni.thanosgym.model.Utilidad;

public class CRUDUtilidad extends BaseCrud<Utilidad> {
    public static CRUDUtilidad crudUtility;

    public static CRUDUtilidad getInstance() {
        if (crudUtility == null) {
            crudUtility = new CRUDUtilidad();
        }
        return crudUtility;
    }

    public Response<Utilidad> create(Utilidad utility) {
        return baseCreate(utility, Querys.utility.create);
    }

    public Response<Utilidad> getAll() {
        return baseGetAll(Querys.utility.getAll);
    }

    public Response<Utilidad> getById(int id) {
        return baseGetById(Querys.utility.getById, id);
    }

    public Response<Utilidad> delete(int id) {
        return baseDeleteById(Querys.utility.delete, id);
    }

    public Response<Utilidad> update(Utilidad utility) {
        try {
            sendObject(Querys.utility.update, utility);
            ps.setInt(4, utility.getId());
            ps.executeUpdate();
            ps.close();
            return new Response<Utilidad>(true, "Datos actualizados con exito");
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }

    @Override
    public Utilidad generateObject(ResultSet rs) throws SQLException {
        return new Utilidad(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
    }

    @Override
    public void sendObject(String consulta, Utilidad data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, data.getNombre());
        ps.setInt(2, data.getPeso());
        ps.setInt(3, data.getCantidad());
    }
}