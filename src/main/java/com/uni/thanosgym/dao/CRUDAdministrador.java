package com.uni.thanosgym.dao;

import java.sql.ResultSet;
import com.uni.thanosgym.utils.Querys;
import java.sql.SQLException;
import com.uni.thanosgym.model.Administrador;
import com.uni.thanosgym.model.Response;

public class CRUDAdministrador extends BaseCrud<Administrador> {

    public static CRUDAdministrador crudAdministrador;

    private CRUDAdministrador() {
    }

    public static CRUDAdministrador getInstance() {
        if (crudAdministrador == null) {
            crudAdministrador = new CRUDAdministrador();
        }
        return crudAdministrador;
    }

    public Response<Administrador> create(Administrador admin) {
        try {
            ps = connection.prepareStatement(Querys.admin.getByUsername);
            ps.setString(1, admin.getUsername());
            rs = ps.executeQuery();
            boolean adminWithThatUsernameNotExist = !rs.next();
            boolean[] conditions = new boolean[] { adminWithThatUsernameNotExist };
            String error = "El nombre de usuario se encuentra en uso";
            return baseCreateWithConditions(admin, Querys.admin.create, conditions, error);
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }

    public Response<Administrador> verify(String username, String password) {
        try {
            ps = connection.prepareStatement(Querys.admin.verify);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Response<Administrador>(true, generateObject(rs));
            } else {
                return new Response<Administrador>(false);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return new Response<Administrador>(false);
        }
    }

    public Response<Administrador> delete(int id) {
        return baseDeleteById("DELETE from admin where admin_id = ?", id);
    }

    @Override
    public Administrador generateObject(ResultSet rs) throws SQLException {
        return new Administrador.Builder()
                .setFullName(rs.getString(1)).build();
    }

    @Override
    public Administrador sendObject(String consulta, Administrador data) throws SQLException {
        ps = connection.prepareStatement(consulta);
        ps.setString(1, data.getFullName());
        ps.setString(2, data.getUsername());
        ps.setString(3, data.getPassword());
        ps.setString(4, data.getEmail());
        ps.executeUpdate();
        ps.close();
        return data;
    }
}
