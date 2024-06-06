package com.uni.thanosgym.controller;
import com.uni.thanosgym.config.Startup;
import com.uni.thanosgym.dao.CRUDPlan;
import com.uni.thanosgym.model.Plan;
import com.uni.thanosgym.model.Response;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.uni.thanosgym.preferences.UserPreference;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.view.HomePanel;
import com.uni.thanosgym.view.MainWindow;
import com.uni.thanosgym.view.AddPlan;
import com.uni.thanosgym.view.AddCliente;

public class ControladorHome implements ActionListener {
    HomePanel panel;
    MainWindow vista;
    
    public ControladorHome(MainWindow v, HomePanel pan) {
        panel = pan;
        vista = v;
        
        panel.jbtnCerrarSesion.addActionListener(this);
        panel.jlblNombreAdministrador.setText(UserPreference.getAdminData().getFullName());
        panel.jbtnAgregarPlan.addActionListener(this);
        panel.jbtnAgregarClient.addActionListener(this);
        Response<Plan> response = CRUDPlan.getInstance().getAll();
        if (response.isSuccess()) {
             System.out.println(response.getDataList());
             FrameUtils.createPanelList(response.getDataList(), panel.planesListPanel);
        } else Messages.show(response.getMessage());
        FrameUtils.showPanel(vista, panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panel.jbtnCerrarSesion) {
            vista.dispose();
            UserPreference.deleteData();
            Startup.initWindow();
        }
        
        if (e.getSource() == panel.jbtnAgregarPlan) {
            new ControladorAgregarPlan(new AddPlan());
        } 
        
        if (e.getSource() == panel.jbtnAgregarClient) {
            new ControladorClient(new AddCliente());
        }
    }
    
}
