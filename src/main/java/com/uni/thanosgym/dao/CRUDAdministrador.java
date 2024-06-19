package com.uni.thanosgym.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.uni.thanosgym.utils.Querys;
import com.uni.thanosgym.utils.StringUtils;
import java.sql.SQLException;
import java.util.Date;

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

    public Response<Administrador> create(Administrador admin, Administrador masterAdmin) {
        int quantity = getQuantity();
        if (quantity != 0) {
            Response<Administrador> res = verify(masterAdmin.getUsername(), masterAdmin.getPassword(), true);
            if (!res.isSuccess()) {
                return new Response<>(false,
                        "El administrador master no existe o la contraseña es incorrecta");
            }
        } else {
            admin.setRol(Administrador.Rol.MASTER);
        }
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

    public Response<Administrador> getAll() {
        return baseGetAll(Querys.admin.getAll);
    }

    public Response<Administrador> verify(String username, String password, boolean isForMaster) {
        try {
            String query = isForMaster ? Querys.admin.verifyMaster : Querys.admin.verify;
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                Administrador admin = generateObject(rs);
                if (!isForMaster)
                    admin.setLastSignin(new Date());
                update(admin);
                return new Response<Administrador>(true, admin);
            } else {
                return new Response<Administrador>(false, "El administrador no existe");
            }
        } catch (SQLException e) {
            System.out.println(e);
            return new Response<Administrador>(false, "Error al verificar el administrador");
        }
    }

    public Response<Administrador> update(Administrador admin) {
        try {
            sendObject(Querys.admin.update, admin);
            ps.setInt(8, admin.getId());
            ps.executeUpdate();
            ps.close();
            return new Response<Administrador>(true, "Datos actualizados con exito");
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }

    public int getQuantity() {
        try {
            String query = "SELECT COUNT(*) FROM admin";
            rs = st.executeQuery(query);
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Response<Administrador> getById(int id) {
        return baseGetById(Querys.admin.getById, id);
    }

    public Response<Administrador> deleteOnlyForTesting(int id) {
        return baseDeleteById(Querys.admin.delete, id);
    }

    @Override
    public Administrador generateObject(ResultSet rs) throws SQLException {
        boolean isForMaster = rs.getString(8).equals(Administrador.Rol.MASTER.toString());
        return new Administrador(
            rs.getInt(1),
            rs.getDate(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            rs.getString(6),
            rs.getString(7),
            isForMaster ? Administrador.Rol.MASTER : Administrador.Rol.EMPLEADO,
            rs.getString(9),
            rs.getDate(10)
        );
    }

    @Override
    public void sendObject(String consulta, Administrador admin) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, StringUtils.parseDate(admin.getCreated_At()));
        ps.setString(2, admin.getFullName());
        ps.setString(3, admin.getEmail());
        ps.setString(4, admin.getPhone());
        ps.setString(5, admin.getUsername());
        ps.setString(6, admin.getPassword());
        ps.setString(7, admin.getRol().toString());
        ps.setString(8, admin.getPhotoUrl());
        ps.setString(9, StringUtils.parseDate(admin.getLastSignin()));
    }
}
