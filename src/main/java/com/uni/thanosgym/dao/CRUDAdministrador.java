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
                if (!isForMaster) admin.setLastSignin(new Date());
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
        boolean isForMaster = rs.getString(7).equals(Administrador.Rol.MASTER.toString());
        return new Administrador.Builder()
                .setId(rs.getInt(1))
                .setFullName(rs.getString(2))
                .setEmail(rs.getString(3))
                .setPhone(rs.getInt(4))
                .setUsername(rs.getString(5))
                .setPassword(rs.getString(6))
                .setRol(isForMaster ? Administrador.Rol.MASTER : Administrador.Rol.EMPLEADO)
                .setLastSignin(rs.getDate(8))
                .build();
    }

    @Override
    public void sendObject(String consulta, Administrador data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, data.getFullName());
        ps.setString(2, data.getUsername());
        ps.setString(3, data.getPassword());
        ps.setString(4, data.getEmail());
        ps.setInt(5, data.getPhone());
        ps.setString(6, data.getRol().toString());
        ps.setString(7, StringUtils.parseDate(data.getLastSignin()));
    }
}
