package com.uni.thanosgym.dao;
import java.util.Date;

import com.uni.thanosgym.model.Contrato;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.DateUtils;
import com.uni.thanosgym.model.Administrador;
import com.uni.thanosgym.model.Cliente;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CRUDContratoTest {

    CRUDContrato crudPayment = CRUDContrato.getInstance();
    CRUDCliente crudCliente = CRUDCliente.getInstance();
    CRUDAdministrador crudAdministrador = CRUDAdministrador.getInstance();
    CRUDPlan crudPlan = CRUDPlan.getInstance();

    @Test
    public void mainTest() {
        // create client
        Cliente cliente = new Cliente("75201393", new Date(), "Juan carlos",
                "test@test.com", "santoheu", "986327221", "photo_url");
        cliente.setId(crudCliente.create(cliente).getId());

        //create plan
        Plan plan = new Plan("Test Plan", 100, 30, "V");
        plan.setId(crudPlan.create(plan).getId());

        //create administrador
        Administrador admin = new Administrador(
                new Date(),
                "Admin test",
                "986327221",
                "test.testmaster@gmail",
                "testusername",
                "testusername",
                Administrador.Rol.MASTER,
                "photo",
                new Date());
        admin.setId(crudAdministrador.create(admin, new Administrador()).getId());

        //create payment
        Contrato contrato = new Contrato(cliente, plan, admin, "123456", false, new Date(), DateUtils.addDays(new Date(), plan.getDurationDays()));
        Response<Contrato> resPayment = crudPayment.create(contrato);
        assertTrue(resPayment.isSuccess());

        // delete payment
        Response<Contrato> resPayment2 = crudPayment.delete(resPayment.getId());
        assertTrue(resPayment2.isSuccess());

        // delete admin
        crudAdministrador.deleteOnlyForTesting(admin.getId());

        // delete plan
        crudPlan.deleteOnlyForTesting(plan.getId());

        // delete client
        crudCliente.deleteOnlyForTesting(cliente.getId());
    }
}
