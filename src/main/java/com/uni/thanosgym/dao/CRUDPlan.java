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
        return baseCreate(plan, Querys.plan.create);
    }

    public Response<Plan> getById(int id) {
        return baseGetById(Querys.getByIdTemplate(Plan.tableName), id);
    }

    public Response<Plan> getAll() {
        return baseGetAll(Querys.getAllTemplate(Plan.tableName));
    }

    public Response<Plan> deleteOnlyForTesting(int id) {
        return baseDeleteById(Querys.deleteTemplate(Plan.tableName), id);
    }

    public Response<Plan> update(Plan plan) {
        try {
            sendObject(Querys.plan.update, plan);
            ps.setInt(5, plan.getId());
            ps.executeUpdate();
            ps.close();
            return new Response<Plan>(true, "Datos actualizados con exito");
        } catch (Exception e) {
            return somethingWentWrong(e);
        }
    }

    @Override
    public Plan generateObject(ResultSet rs) throws SQLException {
        return new Plan.Builder()
                .setId(rs.getInt(Plan.idField))
                .setName(rs.getString(Plan.nameField))
                .setPrice(rs.getDouble(Plan.priceField))
                .setDurationDays(rs.getInt(Plan.durationDaysField))
                .setIsVisible(rs.getBoolean(Plan.isVisibleField))
                .build();
    }

    @Override
    /**
     * see Querys
     */
    public void sendObject(String consulta, Plan data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, data.getName());
        ps.setDouble(2, data.getPrice());
        ps.setInt(3, data.getDurationDays());
    }

}
