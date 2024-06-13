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

    @Test
    public void mainTest() {
        // create
        Plan plan = new Plan(20,"Test Plan", 100, 30);
        Cliente cliente = new Cliente(plan, 75201393, new Date(), DateUtils.addDays(new Date(), 30), "Juan carlos",
                "juan@juan.com", "santoheu", 986327221);
        Response<Cliente> response = crudCliente.create(cliente);
        assertEquals(true, response.isSuccess());

        Response<Cliente> res2 = crudCliente.delete(response.getId());
        System.out.println(response.getId());
        assertEquals(true, res2.isSuccess());
    }
}
