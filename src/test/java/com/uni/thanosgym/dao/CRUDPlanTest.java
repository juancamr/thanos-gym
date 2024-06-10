package com.uni.thanosgym.dao;

import com.uni.thanosgym.model.Plan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CRUDPlanTest {

    @Test
    public void testCreate() {
        CRUDPlan crudPlan = CRUDPlan.getInstance();
        Plan pastPlan = new Plan("Test Plan", 100, 30);
        pastPlan = crudPlan.create(pastPlan).getData();
        Plan newPlan = crudPlan.getById(pastPlan.getId()).getData();

        assertEquals(pastPlan.getName(), newPlan.getName());
        crudPlan.delete(newPlan.getId());
    }

}
