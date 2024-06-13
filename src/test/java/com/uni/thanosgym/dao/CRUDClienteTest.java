package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.DateUtils;
import com.uni.thanosgym.model.Cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class CRUDClienteTest {

    CRUDCliente crudCliente = CRUDCliente.getInstance();
    CRUDPlan crudPlan = CRUDPlan.getInstance();

    @Test
    public void mainTest() {
        // create plan
        Plan plan = new Plan("Test Plan for client", 100, 30);
        Response<Plan> resPlan = crudPlan.create(plan);
        assertEquals(true, resPlan.isSuccess());
        plan.setId(resPlan.getId());

        // create client
        Cliente cliente = new Cliente(plan, 75201393, new Date(), DateUtils.addDays(new Date(), 30), "Juan carlos",
                "test@test.com", "santoheu", 986327221);
        Response<Cliente> resClient = crudCliente.create(cliente);
        assertEquals(true, resClient.isSuccess());

        // delete client
        Response<Cliente> res3 = crudCliente.delete(resClient.getId());
        assertEquals(true, res3.isSuccess());

        //delete plan
        Response<Plan> response3 = crudPlan.delete(resPlan.getId());
        assertEquals(true, response3.isSuccess());
    }
}
