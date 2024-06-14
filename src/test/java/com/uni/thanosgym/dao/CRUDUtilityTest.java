package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.model.Utility;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CRUDUtilityTest {

    CRUDUtility crudUtility = CRUDUtility.getInstance();

    @Test
    public void mainTest() {
        // create
        Utility utility = new Utility("Test Utility", 100, 30);
        Response<Utility> response = crudUtility.create(utility);
        assertEquals(true, response.isSuccess());

        //update
        int id = response.getId();
        String nameEdited = "Test Utility edited";
        utility.setNombre(nameEdited);
        utility.setId(id);
        Response<Utility> response2 = crudUtility.update(utility);
        assertEquals(true, response2.isSuccess());

        // delete
        Response<Utility> response3 = crudUtility.delete(id);
        assertEquals(true, response3.isSuccess());
    }
}

