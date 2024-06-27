package com.uni.thanosgym.controller;

import javax.swing.JPanel;

import com.uni.thanosgym.common.Theme;
import com.uni.thanosgym.components.ButtonComponent;
import com.uni.thanosgym.components.InputComponent;
import com.uni.thanosgym.components.Typography;
import com.uni.thanosgym.components.lib.RoundedPanel;
import com.uni.thanosgym.dao.CRUDPlan;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;
import java.awt.Color;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.view.PanelPlan;

import java.util.List;

import javax.swing.JFrame;

public class PlanController {

    public static List<Plan> listaPlanes;
    public static JFrame addPlanFrame;

    public static List<Plan> getListaPlanes() {
        if (listaPlanes == null) {
            listaPlanes = CRUDPlan.getInstance().getAll().getDataList();
        }
        return listaPlanes;
    }

    public static void showEditarPlan(Plan plan) {
        // ask for admin master
        int width = 400;
        int height = 400;
        addPlanFrame = new JFrame();
        FrameUtils.setupWindow(addPlanFrame, width, height);
        JPanel panel = new JPanel();
        panel.setLocation(0, 0);
        panel.setBackground(Color.WHITE);
        panel.setSize(width, height);
        panel.setLayout(null);

        InputComponent namePlan = new InputComponent.Builder()
                .label("Nombre del plan")
                .position(20, 20)
                .width(360)
                .type(InputComponent.Type.TEXT)
                .build();

        InputComponent precioPlan = new InputComponent.Builder()
                .label("Precio")
                .position(20, 120)
                .width(360)
                .type(InputComponent.Type.TEXT)
                .build();

        InputComponent duracionPlan = new InputComponent.Builder()
                .label("Duracion")
                .position(20, 220)
                .width(360)
                .type(InputComponent.Type.TEXT)
                .build();

        ButtonComponent button = new ButtonComponent.Builder()
                .text("Editar")
                .position(0, 280)
                .width(360)
                .type(ButtonComponent.Type.PRIMARY)
                .onClick(() -> {
                    int position = getListaPlanes().indexOf(plan);
                    insertData(namePlan, precioPlan, duracionPlan, false, plan.getId(), position);
                })
                .build();

        namePlan.setContent(plan.getName());
        precioPlan.setContent(String.valueOf(plan.getPrice()));
        duracionPlan.setContent(String.valueOf(plan.getDurationDays()));

        panel.add(namePlan);
        panel.add(precioPlan);
        panel.add(duracionPlan);
        panel.add(button);

        addPlanFrame.add(panel);
        addPlanFrame.setVisible(true);
    }

    public static void showAddPlan() {
        // ask for admin master
        int width = 400;
        int height = 400;
        addPlanFrame = new JFrame();
        FrameUtils.setupWindow(addPlanFrame, width, height);
        JPanel panel = new JPanel();
        panel.setLocation(0, 0);
        panel.setBackground(Color.WHITE);
        panel.setSize(width, height);
        panel.setLayout(null);

        InputComponent namePlan = new InputComponent.Builder()
                .label("Nombre del plan")
                .position(20, 20)
                .width(360)
                .type(InputComponent.Type.TEXT)
                .build();

        InputComponent precioPlan = new InputComponent.Builder()
                .label("Precio")
                .position(20, 120)
                .width(360)
                .type(InputComponent.Type.TEXT)
                .build();

        InputComponent duracionPlan = new InputComponent.Builder()
                .label("Duracion")
                .position(20, 220)
                .width(360)
                .type(InputComponent.Type.TEXT)
                .build();

        ButtonComponent button = new ButtonComponent.Builder()
                .text("Crear")
                .position(0, 280)
                .width(360)
                .type(ButtonComponent.Type.PRIMARY)
                .onClick(() -> {
                    insertData(namePlan, precioPlan, duracionPlan, false, 0, 0);
                })
                .build();

        panel.add(namePlan);
        panel.add(precioPlan);
        panel.add(duracionPlan);
        panel.add(button);

        addPlanFrame.add(panel);
        addPlanFrame.setVisible(true);
    }

    public static void deletePlan(Plan plan) {
        boolean allowed = Messages.confirm("Estas seguro que desea eliminar el plan " + plan.getName(),
                "Eliminar plan");
        if (!allowed)
            return;
        plan.setIsVisible(false);
        Response<Plan> response = CRUDPlan.getInstance().update(plan);
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return;
        }
        getListaPlanes().remove(plan);
        PanelPlan.updateUIPlan();
    }

    /**
     * Funcion para mostrar la ventana para actualizar o crear un plan
     *
     * @param isForAdd Si es verdadero es para crear un plan
     * @param planId   Usado para actualizar un plan
     */
    public static void insertData(InputComponent nombreInput, InputComponent precioInput, InputComponent duracionInput,
            boolean isForAdd, int planId, int positionInList) {
        String nombre = nombreInput.getContent();
        String precio = precioInput.getContent();
        String duracion = duracionInput.getContent();

        if (nombre.isEmpty() || precio.isEmpty() || duracion.isEmpty()) {
            Messages.show("Por favor, llene todos los campos");
            return;
        }
        if (!StringUtils.isDecimal(precio) || !StringUtils.isDecimal(duracion)) {
            Messages.show("El precio debe ser un número entero");
            return;
        }
        Response<Plan> response;
        Plan plan = new Plan.Builder()
                .setName(nombre)
                .setPrice(Double.valueOf(precio))
                .setDurationDays(Integer.valueOf(duracion))
                .build();
        if (isForAdd) {
            Plan planWithTheSameName = getListaPlanes().stream().filter(p -> p.getName().equals(nombre)).findFirst()
                    .orElse(null);
            if (planWithTheSameName != null) {
                Messages.show("Ya existe un plan con el mismo nombre");
                return;
            }
            response = CRUDPlan.getInstance().create(plan);
        } else {
            plan.setId(planId);
            response = CRUDPlan.getInstance().update(plan);
        }
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return;
        }
        if (isForAdd) {
            Messages.show("Plan creado con exito");
            plan.setId(response.getId());
            getListaPlanes().add(plan);
        } else {
            getListaPlanes().set(positionInList, plan);
            Messages.show("Plan actualizado con exito");
        }

        addPlanFrame.dispose();
        PanelPlan.updateUIPlan();
    }

    /**
     * Renderizar lista de paneles dentro de un main panel
     *
     * @param dataList  Lista de planes
     * @param mainPanel Panel padre
     */
    public static void createPanelList(List<Plan> dataList, JPanel mainPanel) {
        mainPanel.removeAll();
        mainPanel.setLayout(null);
        int widthPanel = 300;

        for (int i = 0; i < dataList.size(); i++) {
            int margin = 10;
            Plan plan = dataList.get(i);
            RoundedPanel panel = new RoundedPanel(10);
            panel.setBackground(Theme.colors.primary);
            panel.setLayout(null);
            panel.setBounds(0, 300 * i, widthPanel, 150);

            Typography name = new Typography.Builder()
                    .text(plan.getName())
                    .type(Typography.Type.SUBHEADING)
                    .position(margin, 10)
                    .width(widthPanel - margin * 2)
                    .build();

            Typography precio = new Typography.Builder()
                    .text("Precio: %s" + plan.getPrice())
                    .type(Typography.Type.SMALL)
                    .position(margin, 20)
                    .width(widthPanel - margin * 2)
                    .build();

            Typography duracion = new Typography.Builder()
                    .text("Duracion: " + plan.getDurationDays() + " dias")
                    .type(Typography.Type.SMALL)
                    .position(margin, 40)
                    .width(widthPanel - margin * 2)
                    .build();

            ButtonComponent editButton = new ButtonComponent.Builder()
                    .text("Editar")
                    .position(margin, 80)
                    .width((widthPanel - margin * 2) / 2 - 10)
                    .type(ButtonComponent.Type.SMALL)
                    .onClick(() -> {
                        showEditarPlan(plan);
                    })
                    .build();

            ButtonComponent deleteButton = new ButtonComponent.Builder()
                    .text("Eliminar")
                    .position((widthPanel - margin * 2) / 2 + 10, 80)
                    .width((widthPanel - margin * 2) / 2 - 10)
                    .type(ButtonComponent.Type.SMALL)
                    .onClick(() -> {
                        deletePlan(plan);
                    })
                    .build();

            panel.add(name);
            panel.add(precio);
            panel.add(duracion);
            panel.add(editButton);
            panel.add(deleteButton);

            mainPanel.add(panel);
        }
    }

}
