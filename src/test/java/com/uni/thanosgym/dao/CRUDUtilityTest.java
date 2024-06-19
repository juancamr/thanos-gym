package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Administrador;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.model.Utility;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class CRUDUtilityTest {

    CRUDUtilidad crudUtility = CRUDUtilidad.getInstance();
    CRUDAdministrador crudAdministrador = CRUDAdministrador.getInstance();

    @Test
    public void mainTest() {

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

        // create utility
        Utility utility = new Utility(admin, "Test Utility", 100, 30, "photo");
        Response<Utility> response = crudUtility.create(utility);
        assertTrue(response.isSuccess());

        //update
        int id = response.getId();
        String nameEdited = "Test Utility edited";
        utility.setNombre(nameEdited);
        utility.setId(id);
        Response<Utility> response2 = crudUtility.update(utility);
        assertTrue(response2.isSuccess());

        // delete admin
        crudAdministrador.deleteOnlyForTesting(admin.getId());

        // delete utility
        Response<Utility> response3 = crudUtility.delete(id);
        assertTrue(response3.isSuccess());
    }
}

