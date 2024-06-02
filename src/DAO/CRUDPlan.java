package DAO;

import model.Response;
import model.Plan;

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

    public String getPlanName(int planId) {
        String planName = null;
        String query = "SELECT name FROM plan WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, planId);
            rs = ps.executeQuery();
            if (rs.next()) {
                planName = rs.getString("name");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return planName;
    }

    public int getPlanId(String planName) {
        int planId = -1; // if plan not fougnd
        String query = "SELECT id FROM plan WHERE name = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, planName);
            rs = ps.executeQuery();
            if (rs.next()) {
                planId = rs.getInt("id");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return planId;
    }

}
