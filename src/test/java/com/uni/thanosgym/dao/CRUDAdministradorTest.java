package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Administrador;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CRUDAdministradorTest {

    CRUDAdministrador crudAdministrador = CRUDAdministrador.getInstance();

    @Test
    public void mainTest() {
        //create
        Administrador admin = new Administrador.Builder()
        .setPhone(986327221)
        .setUsername("testusername")
        .setPassword("testusername")
        .setEmail("juancr.molero@gmail")
        .setFullName("Test user")
        .build();

//        crudAdministrador.create(admin);
//        assertEquals(true, response.isSuccess());
//
//        //update
//        int id = response.getId();
//        String nameEdited = "Test plan edited";
//        plan.setName(nameEdited);
//        plan.setId(id);
//        Response<Plan> response2 = crudPlan.update(plan);
//        assertEquals(true, response2.isSuccess());
//        assertEquals(nameEdited, crudPlan.getById(id).getData().getName());
//
//        //delete
//        Response<Plan> response3 = crudPlan.delete(id);
//        assertEquals(true, response3.isSuccess());
    }
}

