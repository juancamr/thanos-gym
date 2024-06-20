package com.uni.thanosgym.controller;

import javax.swing.JLabel;
import javax.swing.JPanel;
import com.uni.thanosgym.dao.CRUDPlan;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.view.PanelPlan;
import com.uni.thanosgym.view.components.RoundedPanel;
import com.uni.thanosgym.view.MainWindow;
import com.uni.thanosgym.view.AddPlan;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class ControladorPlan {

    public static List<Plan> listaPlanes;
    public static PanelPlan panel;
    private static boolean panelIsRendered = false;

    public static List<Plan> getListaPlanes() {
        if (listaPlanes == null) {
            listaPlanes = CRUDPlan.getInstance().getAll().getDataList();
        }
        return listaPlanes;
    }

    /**
     * Mostrar la ventana para agregar un nuevo plan
     *
     * @param v   Ventana principal donde se renderizara el panel
     * @param pan Panel principal
     */
    public static void showPanel() {
        PanelPlan panel = getPanelPlan();
        MainWindow vista = ControladorMainWindow.getMainWindow();
        List<Plan> listaPlanes = getListaPlanes();
        ControladorPlan.createPanelList(listaPlanes, panel.planesListPanel);

        if (!panelIsRendered) {
            FrameUtils.addOnClickEvent(panel.jbtnAgregarPlan, ControladorPlan::showAgregarPlanWindow);
            panelIsRendered = true;
        }
        FrameUtils.showPanel(vista, panel);
    }

    /**
     * Mostrar la ventana para agregar un nuevo plan
     *
     * @param panel Ventana de agregar plan
     */
    public static void showAgregarPlanWindow() {
        if (getListaPlanes().size() >= 3) {
            Messages.show("No puedes tener más de 3 planes");
            return;
        }
        AddPlan vista = new AddPlan();
        FrameUtils.showWindow(vista, "Crear un nuevo plan");
        vista.jtxtNombrePlan.requestFocus();
        FrameUtils.addEnterEvent(vista.jtxtDuracion, () -> insertData(vista, true, 0, 0));
        FrameUtils.addOnClickEvent(vista.jbtnAction, () -> insertData(vista, true, 0, 0));
    }

    /**
     * Mostrar la ventana para editar un plan
     *
     * @param vistaPlan Ventana de agregar plan
     * @param plan      Plan sin editar
     */
    public static void showEditarPlanWindow(AddPlan vistaPlan, Plan plan) {
        FrameUtils.showWindow(vistaPlan, "Editar plan");
        vistaPlan.jtxtNombrePlan.setText(plan.getName());
        vistaPlan.jtxtPrecio.setText(String.valueOf(plan.getPrice()));
        vistaPlan.jtxtDuracion.setText(String.valueOf(plan.getDurationDays()));
        vistaPlan.jbtnAction.setText("Editar");
        vistaPlan.jtxtNombrePlan.requestFocus();
        int idx = getListaPlanes().indexOf(plan);
        FrameUtils.addEnterEvent(vistaPlan.jtxtDuracion, () -> insertData(vistaPlan, false, plan.getId(), idx));
        FrameUtils.addOnClickEvent(vistaPlan.jbtnAction, () -> insertData(vistaPlan, false, plan.getId(), idx));
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
        showPanel();
    }

    /**
     * Funcion para mostrar la ventana para actualizar o crear un plan
     *
     * @param vistaPlan Ventana de agregar plan
     * @param isForAdd  Si es verdadero es para crear un plan
     * @param planId    Usado para actualizar un plan
     */
    public static void insertData(AddPlan vistaPlan, boolean isForAdd, int planId, int positionInList) {
        String nombre = vistaPlan.jtxtNombrePlan.getText();
        String precio = vistaPlan.jtxtPrecio.getText();
        String duracion = vistaPlan.jtxtDuracion.getText();

        if (nombre.isEmpty() || precio.isEmpty() || duracion.isEmpty()) {
            Messages.show("Por favor, llene todos los campos");
        } else {
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
            if (response.isSuccess()) {
                if (isForAdd) {
                    Messages.show("Plan creado con exito");
                    plan.setId(response.getId());
                    getListaPlanes().add(plan);
                } else {
                    getListaPlanes().set(positionInList, plan);
                    Messages.show("Plan actualizado con exito");
                }

                vistaPlan.dispose();
                showPanel();
            } else {
                Messages.show(response.getMessage());
            }
        }
    }

    /**
     * Renderizar lista de paneles dentro de un main panel
     *
     * @param dataList  Lista de planes
     * @param mainPanel Panel padre
     */
    public static void createPanelList(List<Plan> dataList, JPanel mainPanel) {
        mainPanel.removeAll();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        if (dataList.isEmpty()) {
            JLabel emptyLabel = new JLabel();
            emptyLabel.setText("No hay planes registrados");
            emptyLabel.setBounds(0, 0, 100, 50);
            mainPanel.add(emptyLabel);
            return;
        }

        for (Plan plan : dataList) {
            RoundedPanel panel = new RoundedPanel(15, new Color(250, 250, 250));
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setPreferredSize(new Dimension(500, 150));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel name = new JLabel(plan.getName());
            name.setFont(new Font("Malgun Gothic", Font.BOLD, 16));
            String price = String.valueOf(plan.getPrice());
            String duracion = String.valueOf(plan.getDurationDays());
            JLabel precioLabel = new JLabel("Precio: $" + price);
            JLabel duracionLabel = new JLabel("Duración: " + duracion + " días");
            JButton editButton = new JButton("Editar");
            editButton.setBackground(new Color(254, 254, 254));
            JButton deleteButton = new JButton("Eliminar");
            deleteButton.setBackground(new Color(254, 254, 254));

            FrameUtils.addOnClickEvent(editButton, () -> showEditarPlanWindow(new AddPlan(), plan));
            FrameUtils.addOnClickEvent(deleteButton, () -> deletePlan(plan));

            panel.add(name);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
            panel.add(precioLabel);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
            panel.add(duracionLabel);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(editButton);
            panel.add(Box.createRigidArea(new Dimension(0, 5)));
            panel.add(deleteButton);

            mainPanel.add(panel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        }
    }

    public static PanelPlan getPanelPlan() {
        if (panel == null)
            panel = new PanelPlan();
        return panel;
    }

}
