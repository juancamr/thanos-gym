package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CRUDPlanTest {

    CRUDPlan crudPlan = CRUDPlan.getInstance();

    @Test
    public void mainTest() {
        //create
        Plan plan = new Plan("Test Plan", 100, 30, "V");
        Response<Plan> response = crudPlan.create(plan);
        assertTrue(response.isSuccess());

        //update
        int id = response.getId();
        String nameEdited = "Test plan edited";
        plan.setName(nameEdited);
        plan.setId(id);
        Response<Plan> response2 = crudPlan.update(plan);
        assertTrue(response2.isSuccess());
        assertEquals(nameEdited, crudPlan.getById(id).getData().getName());

        //delete
        Response<Plan> response3 = crudPlan.deleteOnlyForTesting(id);
        assertTrue(response3.isSuccess());
    }
}
