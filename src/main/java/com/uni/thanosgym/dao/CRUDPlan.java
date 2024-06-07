package com.uni.thanosgym.dao;

import java.util.ArrayList;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.model.Plan;

import java.sql.PreparedStatement;
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

    /**
     * Creates a new plan in the database.
     *
     * This method first checks if a plan with the same name already exists.
     * If it does not exist, it inserts the new plan into the database and
     * retrieves the generated ID for the newly created plan.
     *
     * @param plan The plan to be created, with name, price, and duration days set.
     * @return A Response object containing the status of the operation, a message,
     *         and the created plan with its ID set if the creation was successful.
     */
    public Response<Plan> create(Plan plan) {
        String con = "SELECT * FROM plan WHERE name='<name>'";
        con = con.replace("<name>", plan.getName());
        try {
            rs = st.executeQuery(con);
            if (!rs.next()) {
                String consulta = "INSERT INTO plan(name, price, duration_days) VALUES(?, ?, ?)";
                try {
                    ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setString(1, plan.getName());
                    ps.setDouble(2, plan.getPrice());
                    ps.setInt(3, plan.getDurationDays());
                    ps.executeUpdate();
                    ResultSet res2 = ps.getGeneratedKeys();
                    if (res2.next()) {
                        int generatedId = res2.getInt(1);
                        plan.setId(generatedId);
                    }
                    ps.close();
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

    /**
     * Retrieves a plan from the database by its ID.
     *
     * This method queries the database for a plan with the specified ID.
     * If found, it returns a Response object containing the plan details.
     * If not found, it returns a Response indicating the plan was not found.
     *
     * @param id The ID of the plan to retrieve.
     * @return A Response object containing the status of the operation, a message,
     *         and the retrieved plan if the operation was successful.
     */
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

    /**
     * Retrieves a plan from the database by its name.
     *
     * This method queries the database for a plan with the specified name.
     * If found, it returns a Response object containing the plan details.
     * If not found, it returns a Response indicating the plan was not found.
     *
     * @param planName The name of the plan to retrieve.
     * @return A Response object containing the status of the operation, a message,
     *         and the retrieved plan if the operation was successful.
     */
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

    /**
     * Retrieves all plans from the database.
     *
     * This method queries the database for all plans and returns a list of Plan
     * objects.
     * If successful, it returns a Response object containing the list of plans.
     * If an error occurs, it returns a Response indicating the failure.
     *
     * @return A Response object containing the status of the operation and a list
     *         of all plans if successful.
     */
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

    public Response<Plan> update(Plan plan) {
        String consulta = "UPDATE plan SET name=?, price=?, duration_days=? WHERE plan_id=?";
        try {
            ps = connection.prepareStatement(consulta);
            ps.setString(1, plan.getName());
            ps.setDouble(2, plan.getPrice());
            ps.setInt(3, plan.getDurationDays());
            ps.setInt(4, plan.getId());
            ps.executeUpdate();
            return new Response<Plan>(true, "Plan actualizado con exito");
        } catch (Exception e) {
            System.out.println(e);
            return new Response<Plan>(false, "Algo salio mal al actualizar el plan");
        }
    }

}
