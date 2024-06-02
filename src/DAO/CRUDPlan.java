package DAO;

import controller.plan.ControladorAgregarPlan;
import java.util.ArrayList;
import model.Response;
import model.Plan;
import thanosgym.Main;

public class CRUDPlan extends BaseCrud {

    public static CRUDPlan crudPlan;

    public CRUDPlan() {
    }

    public static CRUDPlan getInstance() {
        if (crudPlan == null) {
            crudPlan = new CRUDPlan();
        }
        return crudPlan;
    }

    public void actualizarListaPlanes() {
        ControladorAgregarPlan.listaPlanes = new ArrayList<>();
        try {
            rs = st.executeQuery("select * from plan");
            while (rs.next()) {
                Plan pla = new Plan();
                pla.setPlanId(rs.getInt(1));
                pla.setName(rs.getString(2));
                pla.setPrice(rs.getInt(3));
                pla.setDurationDays(rs.getInt(4));
                ControladorAgregarPlan.listaPlanes.add(pla);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error, no se pudo actualizar la lista planes " + e);
        }
    }

    public Response<Plan> create(Plan plan) {
        String con = "SELECT * FROM plan WHERE name='<name>'";
        con = con.replace("<name>", plan.getName());
        try {
            rs = st.executeQuery(con);
            if (!rs.next()) {
                String consulta = "INSERT INTO plan(name, price, duration_days) VALUES(?, ?, ?)";
                try {
                    ps = connection.prepareStatement(consulta);
                    ps.setString(1, plan.getName());
                    ps.setDouble(2, plan.getPrice());
                    ps.setInt(3, plan.getDurationDays());
                    ps.executeUpdate();
                    ps.close();
                    return new Response(true, "Se creo el plan con exito");
                } catch (Exception e) {
                    System.out.println(e);
                    return new Response(false, "Algo salio mal al crear un nuevo plan");
                }
            } else {
                return new Response(false, "El plan " + plan.getName() + " ya existe");
            }
        } catch (Exception e) {
            System.out.println(e);
            return new Response(false, "Algo salio mal");
        }
    }

    public Response<Plan> read(String planName) {
        String consulta = "SELECT * FROM plan WHERE name = ?";
        try {
            ps = connection.prepareStatement(consulta);
            ps.setString(1, planName);
            rs = ps.executeQuery();
            if (rs.next()) {
                Plan plan = new Plan(
                        rs.getInt("plan_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("duration_days")
                );
                return new Response<>(true, "Plan encontrado", plan);
            } else {
                return new Response<>(false, "Plan no encontrado");
            }
        } catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "Algo salió mal");
        }
    }

}
