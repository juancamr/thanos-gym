package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.DateUtils;
import com.uni.thanosgym.model.Cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class CRUDClienteTest {

    CRUDCliente crudCliente = CRUDCliente.getInstance();
    CRUDPlan crudPlan = CRUDPlan.getInstance();

    @Test
    public void mainTest() {
        // create
        Cliente cliente = new Cliente(75201393, new Date(), DateUtils.addDays(new Date(), 30), "Juan carlos",
                "test@test.com", "santoheu", 986327221, Cliente.Frozen.NO);
        Response<Cliente> resClient = crudCliente.create(cliente);
        assertTrue(resClient.isSuccess());

        // delete 
        Response<Cliente> res3 = crudCliente.delete(resClient.getId());
        assertTrue(res3.isSuccess());
    }
}
