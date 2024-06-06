package com.uni.thanosgym.dao;

import java.util.ArrayList;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.model.Plan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CRUDPlan extends BaseCrud {

    public static CRUDPlan crudPlan;

    private CRUDPlan() {
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
                    ResultSet res2 = ps.getGeneratedKeys();
                    if (res2.next()) {
                        int generatedId = rs.getInt(1); 
                        plan.setId(generatedId); 
                    }
                    System.out.println(plan);
                    return new Response<Plan>(true, "Se creo el plan con exito", plan);
                } catch (Exception e) {
                    System.out.println(e);
                    return new Response<Plan>(false, "Algo salio mal al crear un nuevo plan");
                }
            } else {
                return new Response<Plan>(false, "El plan " + plan.getName() + " ya existe");
            }
        } catch (Exception e) {
            System.out.println(e);
            return new Response<Plan>(false, "Algo salio mal");
        }
    }

    public Response<Plan> getById(int id) {
        String consulta = "SELECT * FROM plan WHERE plan_id=";
        try {
            rs = st.executeQuery(consulta + id);
            if (rs.next()) {
                Plan plan = new Plan(
                        rs.getInt("plan_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("duration_days"));
                return new Response<>(true, "Plan encontrado", plan);
            } else {
                return new Response<>(false, "Plan no encontrado");
            }
        } catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "Algo salió mal");
        }
    }

    public Response<Plan> getByName(String planName) {
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
                        rs.getInt("duration_days"));
                return new Response<>(true, "Plan encontrado", plan);
            } else {
                return new Response<>(false, "Plan no encontrado");
            }
        } catch (Exception e) {
            System.out.println(e);
            return new Response<>(false, "Algo salió mal");
        }
    }

    public Response<Plan> getAll() {
        try {
            rs = st.executeQuery("select * from plan");
            List<Plan> listaPlanes = new ArrayList<Plan>();
            while (rs.next()) {
                Plan pla = new Plan();
                pla.setId(rs.getInt(1));
                pla.setName(rs.getString(2));
                pla.setPrice(rs.getInt(3));
                pla.setDurationDays(rs.getInt(4));
                listaPlanes.add(pla);
            }
            rs.close();
            return new Response<Plan>(true, listaPlanes);
        } catch (SQLException e) {
            System.out.println("Error, no se pudo actualizar la lista planes " + e);
            return new Response<Plan>(false);
        }
    }

    public Response<Plan> delete(int id) {
        String consulta = "DELETE FROM plan WHERE plan_id = ?";
        try {
            ps = connection.prepareStatement(consulta);
            ps.setInt(1, id);
            ps.executeUpdate();
            return new Response<Plan>(true, "Plan eliminado con exito");
        } catch (Exception e) {
            System.out.println(e);
            return new Response<Plan>(false, "Algo salio mal al eliminar el plan");
        }
    }

    public Response<Plan> deleteByName(String name) {
        String consulta = "DELETE FROM plan WHERE name = ?";
        try {
            ps = connection.prepareStatement(consulta);
            ps.setString(1, name);
            ps.executeUpdate();
            return new Response<Plan>(true, "Plan eliminado con exito");
        } catch (Exception e) {
            System.out.println(e);
            return new Response<Plan>(false, "Algo salio mal al eliminar el plan");
        }
    }

}
