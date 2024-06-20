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
        // create
        Plan plan = new Plan.Builder()
                .setName("Test plan")
                .setPrice(100)
                .setDurationDays(1)
                .build();

        Response<Plan> response = crudPlan.create(plan);
        assertTrue(response.isSuccess());

        // update
        int id = response.getId();
        String nameEdited = "Test plan edited";
        Plan planEdited = new Plan.Builder()
                .setName("Test plan edited")
                .setPrice(100)
                .setDurationDays(1)
                .build();
        Response<Plan> response2 = crudPlan.update(planEdited);
        assertTrue(response2.isSuccess());
        assertEquals(nameEdited, crudPlan.getById(id).getData().getName());

        // delete
        Response<Plan> response3 = crudPlan.deleteOnlyForTesting(id);
        assertTrue(response3.isSuccess());
    }
}
