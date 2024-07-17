package com.uni.thanosgym.controllers;

import java.util.Map;

import com.uni.thanosgym.dao.CRUDPlan;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;

public class PlanController {

    public static boolean createPlan(Map<String, String> params) {
        String name = params.get("name");
        String duration = params.get("duration");
        String price = params.get("price");

        if (name.isEmpty() || duration.isEmpty() || price.isEmpty()) {
            Messages.show("Complete todos los campos");
            return false;
        }

        if (!StringUtils.isInteger(duration)) {
            Messages.show("La duración debe ser un número");
            return false;
        }

        if (!StringUtils.isDecimal(price)) {
            Messages.show("El precio debe ser un número");
            return false;
        }

        Plan plan = new Plan.Builder()
                .setName(name)
                .setPrice(Double.parseDouble(price))
                .setDurationDays(Integer.parseInt(duration))
                .build();

        Response<Plan> response = CRUDPlan.getInstance().create(plan);
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return false;
        }
        return true;
    }
}
