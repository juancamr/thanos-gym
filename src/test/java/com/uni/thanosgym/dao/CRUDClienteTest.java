package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.model.Client;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CRUDClienteTest {

    CRUDCliente crudCliente = CRUDCliente.getInstance();
    CRUDPlan crudPlan = CRUDPlan.getInstance();

    @Test
    public void mainTest() {
        // create
        Client cliente = new Client.Builder()
                .setFullName("Client test")
                .setDni("75423447")
                .setEmail("test@test.com")
                .setPhone("986327221")
                .setPhotoUrl("photo_url")
                .setDireccion("asontehunsa")
                .build();
        Response<Client> resClient = crudCliente.create(cliente);
        assertTrue(resClient.isSuccess());

        // delete
        Response<Client> res3 = crudCliente.deleteOnlyForTesting(resClient.getId());
        assertTrue(res3.isSuccess());
    }
}
