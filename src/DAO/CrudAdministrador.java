package DAO;

import model.Administrador;
import thanosgym.Main;
import model.Response;

public class CrudAdministrador extends BaseCrud {

    CrudAdministrador crudAdministrador;

    public CrudAdministrador getInstance() {
        if (crudAdministrador == null) {
            crudAdministrador = new CrudAdministrador();
        }
        return crudAdministrador;
    }

    public Response<Administrador> register(Administrador admin) {
        String con = "SELECT * FROM administrador WHERE username='<username>'";
        con = con.replace("<username>", admin.getUsername());
        try {
            rs = st.executeQuery(con);
            if (!rs.next()) {
                String consulta = "INSERT INTO administrador(nombres, username, password) VALUES(?, ?, ?)";
                try {
                    ps = connection.prepareStatement(consulta);
                    ps.setString(1, admin.getFullName());
                    ps.setString(2, admin.getUsername());
                    ps.setString(3, admin.getPassword());
                    ps.executeUpdate();
                    ps.close();
                    return new Response(true, "Registro exitoso", admin);
                } catch (Exception e) {
                    System.out.println(e);
                    return new Response(false, "Algo salio mal al registrar al administrador");
                }
            } else return new Response(false, "El nombre de usuario se encuentra en uso");
        } catch (Exception e) {
            System.out.println(e);
            return new Response(false, "Algo salio mal");
        }
    }

    public boolean verify(String username, String password) {
        String consulta = "SELECT nombres FROM administrador WHERE username='<username>' AND password='<password>'";
        consulta = consulta.replace("<username>", username);
        consulta = consulta.replace("<password>", password);

        try {
            rs = st.executeQuery(consulta);
            if (rs.next()) {
                Administrador admin = new Administrador.Builder()
                                .setFullName(rs.getString(1)).
                                build();

                Main.admin = admin;
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
