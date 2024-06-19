package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.model.Cliente;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class CRUDClienteTest {

    CRUDCliente crudCliente = CRUDCliente.getInstance();
    CRUDPlan crudPlan = CRUDPlan.getInstance();

    @Test
    public void mainTest() {
        // create
        Cliente cliente = new Cliente("75201393", new Date(), "Juan carlos",
                "test@test.com", "santoheu", "986327221", "photo_url");
        Response<Cliente> resClient = crudCliente.create(cliente);
        assertTrue(resClient.isSuccess());

        // delete
        Response<Cliente> res3 = crudCliente.deleteOnlyForTesting(resClient.getId());
        assertTrue(res3.isSuccess());
    }
}
