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
        try {
            ps = connection.prepareStatement(Querys.plan.getByName);
            ps.setString(1, plan.getName());
            rs = ps.executeQuery();
            boolean[] conditions = new boolean[]{!rs.next()};
            String error = String.format("El plan %s ya existe", plan.getName());
            return baseCreateWithConditions(plan, Querys.plan.create, conditions, error);
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }

    public Response<Plan> getById(int id) {
        return baseGetById(Querys.plan.get, id);
    }

    public Response<Plan> getByName(String planName) {
        return baseGetByString(Querys.plan.get, planName);
    }

    public Response<Plan> getAll() {
        return baseGetAll(Querys.plan.getAll);
    }

    public Response<Plan> delete(int id) {
        return baseDeleteById(Querys.plan.delete, id);
    }

    public Response<Plan> update(Plan plan) {
        return baseUpdate(Querys.plan.update, plan);
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
    public Plan sendObject(String consulta, Plan data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, data.getName());
        ps.setDouble(2, data.getPrice());
        ps.setInt(3, data.getDurationDays());
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
