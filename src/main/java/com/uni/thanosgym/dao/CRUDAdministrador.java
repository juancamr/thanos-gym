package com.uni.thanosgym.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.uni.thanosgym.utils.Querys;
import java.sql.Timestamp;

import java.sql.SQLException;
import java.util.Date;

import com.uni.thanosgym.model.Admin;
import com.uni.thanosgym.model.Response;

public class CRUDAdministrador extends BaseCrud<Admin> {

    public static CRUDAdministrador crudAdministrador;

    private CRUDAdministrador() {
    }

    public static CRUDAdministrador getInstance() {
        if (crudAdministrador == null) {
            crudAdministrador = new CRUDAdministrador();
        }
        return crudAdministrador;
    }

    public Response<Admin> create(Admin admin, Admin masterAdmin) {
        int quantity = getQuantity();
        if (quantity != 0) {
            System.out.println("verificando administrador");
            Response<Admin> res = verify(masterAdmin.getUsername(), masterAdmin.getPassword(), true);
            if (!res.isSuccess()) {
                return new Response<>(false,
                        "El administrador master no existe o la contraseña es incorrecta");
            }
        } else {
            admin.setRol(Admin.Rol.MASTER);
        }
        try {
            ps = connection.prepareStatement(
                    Querys.getTemplateWithConditions(Admin.tableName, Admin.usernameField));
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

    public Response<Admin> getAll() {
        return baseGetAll(Querys.getAllTemplate(Admin.tableName));
    }

    public Response<Admin> verify(String username, String password, boolean isForMaster) {
        try {
            String queryMaster = Querys.getTemplateWithConditions(Admin.tableName,
                    new String[] { Admin.usernameField, Admin.passwordField, Admin.rolField });
            String queryVerify = Querys.getTemplateWithConditions(Admin.tableName,
                    new String[] { Admin.usernameField, Admin.passwordField });
            String query = isForMaster ? queryMaster : queryVerify;
            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            if (isForMaster) {
                ps.setString(3, Admin.Rol.MASTER.toString());
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                Admin admin = generateObject(rs);
                if (!isForMaster)
                    admin.setLastSignin(new Date());
                update(admin);
                return new Response<Admin>(true, admin);
            } else {
                return new Response<Admin>(false, "El administrador no existe");
            }
        } catch (SQLException e) {
            System.out.println(e);
            return new Response<Admin>(false, "Error al verificar el administrador");
        }
    }

    public Response<Admin> update(Admin admin) {
        try {
            sendObject(Querys.admin.update, admin);
            ps.setTimestamp(8, new Timestamp(admin.getLastSignin().getTime()));
            ps.setInt(9, admin.getId());
            ps.executeUpdate();
            ps.close();
            return new Response<Admin>(true, "Datos actualizados con exito");
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

    public Response<Admin> getById(int id) {
        return baseGetById(Querys.getByIdTemplate(Admin.tableName), id);
    }

    public Response<Admin> deleteOnlyForTesting(int id) {
        return baseDeleteById(Querys.deleteTemplate(Admin.tableName), id);
    }

    public Response<Admin> getAdminMasterOnlyForTesting() {
        return baseGetByString(Querys.getTemplateWithConditions(Admin.tableName, Admin.rolField),
                Admin.Rol.MASTER.toString());
    }

    @Override
    public Admin generateObject(ResultSet rs) throws SQLException {
        boolean isForMaster = rs.getString(Admin.rolField).equals(Admin.Rol.MASTER.toString());
        return new Admin.Builder()
                .setId(rs.getInt(Admin.idField))
                .setCreatedAt(rs.getDate(Admin.createdAtField))
                .setFullName(rs.getString(Admin.fullNameField))
                .setEmail(rs.getString(Admin.emailField))
                .setPhone(rs.getString(Admin.phoneField))
                .setUsername(rs.getString(Admin.usernameField))
                .setPassword(rs.getString(Admin.passwordField))
                .setRol(isForMaster ? Admin.Rol.MASTER : Admin.Rol.EMPLEADO)
                .setPhotoUrl(rs.getString(Admin.photoUrlField))
                .setLastSignin(rs.getDate(Admin.lastSigninField))
                .build();
    }

    @Override
    public void sendObject(String consulta, Admin admin) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, admin.getFullName());
        ps.setString(2, admin.getEmail());
        ps.setString(3, admin.getPhone());
        ps.setString(4, admin.getUsername());
        ps.setString(5, admin.getPassword());
        ps.setString(6, admin.getRol().toString());
        ps.setString(7, admin.getPhotoUrl());
    }
}
