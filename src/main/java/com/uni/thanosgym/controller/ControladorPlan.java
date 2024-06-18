package com.uni.thanosgym.controller;

import javax.swing.JLabel;
import javax.swing.JPanel;
import com.uni.thanosgym.dao.CRUDPlan;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import com.uni.thanosgym.utils.Auth;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.utils.UserPreferences;
import com.uni.thanosgym.view.PanelPlan;
import com.uni.thanosgym.view.MainWindow;
import com.uni.thanosgym.view.AddPlan;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

public class ControladorPlan {

    public static List<Plan> listaPlanes;

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
        PanelPlan panel = new PanelPlan();
        MainWindow vista = ControladorMainWindow.getMainWindow();
        List<Plan> listaPlanes = getListaPlanes();
        panel.jlblNombreAdministrador.setText(UserPreferences.getData().getFullName());
        ControladorPlan.createPanelList(listaPlanes, panel.planesListPanel);

        FrameUtils.showPanel(vista, panel);
        FrameUtils.addOnClickEvent(panel.jbtnAgregarPlan, ControladorPlan::showAgregarPlanWindow);
        FrameUtils.addOnClickEvent(panel.jbtnCerrarSesion, () -> {
            vista.dispose();
            Auth.logOut();
        });
    }

    /**
     * Mostrar la ventana para agregar un nuevo plan
     *
     * @param panel Ventana de agregar plan
     */
    public static void showAgregarPlanWindow() {
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
        plan.setIndicador("F");
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
            Plan plan = new Plan(nombre, Double.valueOf(precio), Integer.valueOf(duracion), "V");
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
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        if (dataList.isEmpty()) {
            JLabel emptyLabel = new JLabel();
            emptyLabel.setText("No hay planes registrados");
            emptyLabel.setBounds(0, 0, 100, 50);
            mainPanel.add(emptyLabel);
            return;
        }

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
