package com.uni.thanosgym.dao;

import java.util.Date;

import com.uni.thanosgym.model.Contrato;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.DateUtils;
import com.uni.thanosgym.model.Admin;
import com.uni.thanosgym.model.Client;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CRUDContratoTest {

    CRUDContrato crudContrato = CRUDContrato.getInstance();
    CRUDCliente crudCliente = CRUDCliente.getInstance();
    CRUDAdministrador crudAdministrador = CRUDAdministrador.getInstance();
    CRUDPlan crudPlan = CRUDPlan.getInstance();

    @Test
    public void mainTest() {
        // create client
        Client cliente = new Client.Builder()
                .setFullName("Juan carlos")
                .setDni("75423447")
                .setEmail("test@test.com")
                .setPhone("986327221")
                .setPhotoUrl("photo_url")
                .setDireccion("asontehunsa")
                .build();
        cliente.setId(crudCliente.create(cliente).getId());

        // create plan
        Plan plan = new Plan.Builder()
                .setName("test plan for contrato")
                .setPrice(100)
                .setDurationDays(1)
                .build();
        plan.setId(crudPlan.create(plan).getId());

        Admin adminForVerify = new Admin.Builder().build();
        if (crudAdministrador.getQuantity() != 0) {
            adminForVerify = crudAdministrador.getAdminMasterOnlyForTesting().getData();
        }
        // create administrador
        Admin admin = new Admin.Builder()
                .setFullName("Admin test")
                .setEmail("test.testcontrato@gmail")
                .setPhone("986327221")
                .setUsername("testusernamecontrato")
                .setPassword("testusernamecontrato")
                .setRol(Admin.Rol.MASTER)
                .setPhotoUrl("photo")
                .build();
        admin.setId(crudAdministrador.create(admin, adminForVerify).getId());

        // create contrato
        Contrato contrato = new Contrato.Builder()
                .setCliente(cliente)
                .setPlan(plan)
                .setAdmin(admin)
                .setTransactionCode("123456")
                .setSubscriptionUntil(DateUtils.addDays(new Date(), plan.getDurationDays()))
                .build();
        Response<Contrato> resContrato = crudContrato.create(contrato);
        assertTrue(resContrato.isSuccess());

        // delete payment
        Response<Contrato> resContrato2 = crudContrato.delete(resContrato.getId());
        assertTrue(resContrato2.isSuccess());

        // delete admin
        crudAdministrador.deleteOnlyForTesting(admin.getId());

        // delete plan
        crudPlan.deleteOnlyForTesting(plan.getId());

        // delete client
        crudCliente.deleteOnlyForTesting(cliente.getId());
    }
}
