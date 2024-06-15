package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Querys;
import com.uni.thanosgym.model.Payment;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.model.Cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDPayment extends BaseCrud<Payment> {

    public static CRUDPayment crudPayment;

    private CRUDPayment() {
    }

    public static CRUDPayment getInstance() {
        if (crudPayment == null) {
            crudPayment = new CRUDPayment();
        }
        return crudPayment;
    }

    public Response<Payment> create(Payment payment) {
        return baseCreate(payment, Querys.payment.create);
    }

    public Response<Payment> getById(int id) {
        return baseGetById(Querys.payment.get, id);
    }

    public Response<Payment> delete(int id) {
        return baseDeleteById(Querys.payment.delete, id);
    }

    @Override
    public Payment generateObject(ResultSet rs) throws SQLException {
        Response<Cliente> resCliente = CRUDCliente.getInstance().getById(rs.getInt(4));
        Response<Plan> resPlan = CRUDPlan.getInstance().getById(rs.getInt(5));
        return new Payment(rs.getInt(1), rs.getDate(2), rs.getInt(3), resCliente.getData(),
                resPlan.getData());
    }

    @Override
    public void sendObject(String consulta, Payment data) throws SQLException {
        ps = connection.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, StringUtils.parseDate(data.getCreatedAt()));
        ps.setInt(2, data.getCliente().getId());
        ps.setInt(3, data.getPlan().getId());
        ps.setInt(4, data.getTransactionCode());
    }

}
