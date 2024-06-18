package com.uni.thanosgym.dao;
import java.util.Date;

import com.uni.thanosgym.model.Payment;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.DateUtils;
import com.uni.thanosgym.model.Cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CRUDPaymentTest {

    CRUDPayment crudPayment = CRUDPayment.getInstance();
    CRUDCliente crudCliente = CRUDCliente.getInstance();
    CRUDPlan crudPlan = CRUDPlan.getInstance();

    @Test
    public void mainTest() {
        // create client
        Cliente cliente = new Cliente(75201393, new Date(), DateUtils.addDays(new Date(), 30), "Juan carlos",
                "test@test.com", "santoheu", 986327221, Cliente.Frozen.NO);
        cliente.setId(crudCliente.create(cliente).getId());

        //create plan
        Plan plan = new Plan("Test Plan", 100, 30, "V");
        plan.setId(crudPlan.create(plan).getId());

        //create payment
        Payment payment = new Payment(new Date(), 30, cliente, plan);
        Response<Payment> resPayment = crudPayment.create(payment);
        assertTrue(resPayment.isSuccess());

        // delete payment
        Response<Payment> resPayment2 = crudPayment.delete(resPayment.getId());
        assertTrue(resPayment2.isSuccess());

        //delete plan
        crudPlan.deleteOnlyForTesting(plan.getId());

        // delete client
        crudCliente.delete(cliente.getId());
    }
}
