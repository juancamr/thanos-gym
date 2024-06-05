package com.uni.thanosgym.dao;

import java.sql.SQLException;
import com.uni.thanosgym.model.Administrador;
import com.uni.thanosgym.model.Response;

public class CRUDAdministrador extends BaseCrud {

    public static CRUDAdministrador crudAdministrador;
    
    private CRUDAdministrador() {}

    public static CRUDAdministrador getInstance() {
        if (crudAdministrador == null) {
            crudAdministrador = new CRUDAdministrador();
        }
        return crudAdministrador;
    }

    public Response<Administrador> create(Administrador admin) {
        String con = "SELECT * FROM admin WHERE username='<username>'";
        con = con.replace("<username>", admin.getUsername());
        try {
            rs = st.executeQuery(con);
            if (!rs.next()) {
                String consulta = "INSERT INTO admin(full_name, username, password, email) VALUES(?, ?, ?, ?)";
                try {
                    ps = connection.prepareStatement(consulta);
                    ps.setString(1, admin.getFullName());
                    ps.setString(2, admin.getUsername());
                    ps.setString(3, admin.getPassword());
                    ps.setString(4, admin.getEmail());
                    ps.executeUpdate();
                    ps.close();
                    return new Response<Administrador>(true, admin);
                } catch (SQLException e) {
                    System.out.println(e);
                    return new Response<Administrador>(false, "Algo salio mal al registrar al administrador");
                }
            } else return new Response<Administrador>(false, "El nombre de usuario se encuentra en uso");
        } catch (SQLException e) {
            System.out.println(e);
            return new Response<Administrador>(false, "Algo salio mal");
        }
    }

    public Response<Administrador> verify(String username, String password) {
        String consulta = "SELECT full_name FROM admin WHERE username='<username>' AND password='<password>'";
        consulta = consulta.replace("<username>", username);
        consulta = consulta.replace("<password>", password);

        try {
            rs = st.executeQuery(consulta);
            if (rs.next()) {
                Administrador admin = new Administrador.Builder()
                                .setFullName(rs.getString(1)).
                                build();
                return new Response<Administrador>(true, admin);
            } else {
                return new Response<Administrador>(false);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return new Response<Administrador>(false);
        }
    }
}
