package com.uni.thanosgym.utils;

import com.uni.thanosgym.dao.CRUDAdministrador;
import com.uni.thanosgym.model.Admin;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.view.VentanaPrincipal;
import com.uni.thanosgym.view.auth.VentanaSession;

public class Auth {

    public static boolean isAdminLoggedIn() {
        Admin admin = UserPreferences.getData();
        boolean adminPersistence = admin.isSesionPersistence();
        Response<Admin> response = CRUDAdministrador.getInstance().getById(admin.getId());
        if (adminPersistence && response.isSuccess()) {
            return true;
        }
        return false;
    }

    public static void signIn(Admin admin) {
        UserPreferences.setData(admin);
        VentanaSession.getWindow().dispose();
        VentanaPrincipal.panelDashboard.showPanel();
    }

    public static void logOut() {
        UserPreferences.clearData();
        VentanaPrincipal.getWindow().dispose();
        VentanaSession.loginPanel.showPanel();
    }

    public static void verifySession() {
        if (Auth.isAdminLoggedIn()) {
            VentanaPrincipal.panelDashboard.showPanel();
        } else {
            VentanaSession.loginPanel.showPanel();
        }
    }

}
