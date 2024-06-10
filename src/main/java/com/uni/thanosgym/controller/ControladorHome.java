package com.uni.thanosgym.controller;

import com.uni.thanosgym.config.Startup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.uni.thanosgym.dao.CRUDPlan;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import com.uni.thanosgym.preferences.UserPreference;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.view.HomePanel;
import com.uni.thanosgym.view.MainWindow;
import com.uni.thanosgym.view.AddPlan;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class ControladorHome {
    public static HomePanel panel;
    public static MainWindow vista;

    /**
     * Mostrar la ventana para agregar un nuevo plan
     *
     * @param v   Ventana principal donde se renderizara el panel
     * @param pan Panel principal
     */
    public static void showHomePanel(MainWindow v, HomePanel pan) {
        panel = pan;
        vista = v;

        panel.jlblNombreAdministrador.setText(UserPreference.getAdminData().getFullName());
        Response<Plan> response = CRUDPlan.getInstance().getAll();
        if (response.isSuccess()) {
            ControladorHome.createPanelList(response.getDataList(), panel.planesListPanel);
        } else
            Messages.show(response.getMessage());

        FrameUtils.showPanel(vista, panel);
        FrameUtils.addOnClickEvent(panel.jbtnAgregarPlan, () -> showAgregarPlanWindow(new AddPlan()));
        FrameUtils.addOnClickEvent(panel.jbtnCerrarSesion, () -> {
            vista.dispose();
            UserPreference.deleteData();
            Startup.initWindow();
        });

    }

    /**
     * Mostrar la ventana para agregar un nuevo plan
     *
     * @param vistaPlan Ventana de agregar plan
     */
    public static void showAgregarPlanWindow(AddPlan vistaPlan) {
        FrameUtils.showWindow(vistaPlan, "Crear un nuevo plan");
        vistaPlan.jtxtNombrePlan.requestFocus();
        FrameUtils.addEnterEvent(vistaPlan.jtxtDuracion, () -> insertData(vistaPlan, true, 0));
        FrameUtils.addOnClickEvent(vistaPlan.jbtnAction, () -> insertData(vistaPlan, true, 0));
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
        FrameUtils.addEnterEvent(vistaPlan.jtxtDuracion, () -> insertData(vistaPlan, false, plan.getId()));
        FrameUtils.addOnClickEvent(vistaPlan.jbtnAction, () -> insertData(vistaPlan, false, plan.getId()));
    }

    /**
     * Eliminar un plan
     *
     * @param plan Plan a eliminar
     */
    public static void deletePlan(Plan plan) {
        boolean allowed = Messages.confirm("Estas seguro que desea eliminar el plan " + plan.getName(),
                "Eliminar plan");
        if (!allowed)
            return;
        Response<Plan> response = CRUDPlan.getInstance().delete(plan.getId());
        if (!response.isSuccess())
            Messages.show(response.getMessage());
        showHomePanel(vista, new HomePanel());
    }

    /**
     * Funcion para mostrar la ventana para actualizar o crear un plan
     *
     * @param vistaPlan Ventana de agregar plan
     * @param isForAdd  Si es verdadero es para crear un plan
     * @param planId    Usado para actualizar un plan
     */
    public static void insertData(AddPlan vistaPlan, boolean isForAdd, int planId) {
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
            if (isForAdd) {
                response = CRUDPlan.getInstance()
                        .create(new Plan(nombre, Double.valueOf(precio), Integer.valueOf(duracion)));
            } else {
                response = CRUDPlan.getInstance()
                        .update(new Plan(planId, nombre, Double.valueOf(precio), Integer.valueOf(duracion)));
            }
            if (response.isSuccess()) {
                Messages.show(response.getMessage());
                vistaPlan.dispose();
                showHomePanel(vista, new HomePanel());
            } else
                Messages.show(response.getMessage());
        }
    }

    /**
     * Renderizar lista de paneles dentro de un main panel
     *
     * @param dataList  Lista de planes
     * @param mainPanel Panel padre
     */
    public static void createPanelList(List<Plan> dataList, JPanel mainPanel) {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        for (Plan plan : dataList) {
            JPanel panel = new JPanel();
            int alto = 100;
            int ancho = 100;

            panel.setLayout(new GridLayout(5, 1));
            panel.setPreferredSize(new Dimension(ancho, alto));
            panel.setMaximumSize(new Dimension(ancho, alto));
            panel.setMinimumSize(new Dimension(ancho, alto));

            JLabel name = new JLabel(plan.getName());
            JLabel precio = new JLabel(String.valueOf(plan.getPrice()));
            JLabel duracion = new JLabel(String.valueOf(plan.getDurationDays()));
            JButton editButton = new JButton("Editar");
            JButton deleteButton = new JButton("Eliminar");

            FrameUtils.addOnClickEvent(editButton, () -> showEditarPlanWindow(new AddPlan(), plan));
            FrameUtils.addOnClickEvent(deleteButton, () -> deletePlan(plan));

            panel.add(name);
            panel.add(precio);
            panel.add(duracion);
            panel.add(editButton);
            panel.add(deleteButton);

            mainPanel.add(panel, BorderLayout.CENTER);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
    }

}
