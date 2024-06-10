package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;
import com.uni.thanosgym.model.Plan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDPlan extends BaseCrud<Plan> {

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
        return baseCreate(plan, Querys.Plan.create, () -> {
            ps = connection.prepareStatement(Querys.Plan.getByName);
            ps.setString(1, plan.getName());
            rs = ps.executeQuery();
            boolean planWithThatNameExist = !rs.next();
            return new boolean[] { planWithThatNameExist };
        }, "El plan " + plan.getName()+ " ya existe", (ps) -> {
            return sendObject(ps, Querys.Plan.create, plan);
        });
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
        return baseReadByIdentity(id, Querys.Plan.get, (ResultSet rs) -> {
            return generateObject(rs);
        });
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
        return baseReadByIdentity(planName, Querys.Plan.get, (ResultSet rs) -> {
            return generateObject(rs);
        });
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
        return baseReadAll(Querys.Plan.getAll, (ResultSet rs) -> {
            return generateObject(rs);
        });
    }

    public Response<Plan> delete(int id) {
        return baseDeleteById(id, Querys.Plan.delete);
    }

    public Response<Plan> update(Plan plan) {
        return baseUpdate(Querys.Plan.update, plan);
    }

    @Override
    public Plan generateObject(ResultSet rs) throws SQLException {
        return new Plan(
                rs.getInt(Plan.idField),
                rs.getString(Plan.nameField),
                rs.getDouble(Plan.priceField),
                rs.getInt(Plan.durationField));
    }

    @Override
    public Plan sendObject(PreparedStatement ps, String consulta, Plan data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, data.getName());
        ps.setDouble(2, data.getPrice());
        ps.setInt(3, data.getDurationDays());
        ps.setInt(4, data.getId());
        ps.executeUpdate();
        ResultSet res2 = ps.getGeneratedKeys();
        if (res2.next()) {
            int generatedId = res2.getInt(1);
            data.setId(generatedId);
        }
        ps.close();
        return data;
    }

}
