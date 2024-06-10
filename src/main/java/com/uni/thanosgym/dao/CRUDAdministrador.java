package com.uni.thanosgym.dao;

import java.sql.PreparedStatement;
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
            ps = connection.prepareStatement(Querys.Admin.getByUsername);
            ps.setString(1, admin.getUsername());
            rs = ps.executeQuery();
            boolean adminWithThatUsernameNotExist = !rs.next();
            boolean[] conditions = new boolean[] { adminWithThatUsernameNotExist };
            return baseCreateWithConditions(admin, Querys.Admin.create, conditions,
                    "El nombre de usuario se encuentra en uso", (ps) -> {
                        return sendObject(ps, Querys.Admin.create, admin);
                    });
        } catch (Exception e) {
            return new Response<Administrador>(false, "Something went wrong");
        }
    }

    public Response<Administrador> delete(int id) {
        return baseDeleteById(id, "DELETE from admin where admin_id = ?");
    }

    public Response<Administrador> verify(String username, String password) {
        try {
            ps = connection.prepareStatement(Querys.Admin.verify);
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

    @Override
    public Administrador generateObject(ResultSet rs) throws SQLException {
        return new Administrador.Builder()
                .setFullName(rs.getString(1)).build();
    }

    @Override
    public Administrador sendObject(PreparedStatement ps, String consulta, Administrador data) throws SQLException {
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
