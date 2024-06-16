package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.model.Utilidad;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CRUDUtilityTest {

    CRUDUtilidad crudUtility = CRUDUtilidad.getInstance();

    @Test
    public void mainTest() {
        // create
        Utilidad utility = new Utilidad("Test Utility", 100, 30);
        Response<Utilidad> response = crudUtility.create(utility);
        assertEquals(true, response.isSuccess());

        //update
        int id = response.getId();
        String nameEdited = "Test Utility edited";
        utility.setNombre(nameEdited);
        utility.setId(id);
        Response<Utilidad> response2 = crudUtility.update(utility);
        assertEquals(true, response2.isSuccess());

        // delete
        Response<Utilidad> response3 = crudUtility.delete(id);
        assertEquals(true, response3.isSuccess());
    }
}

