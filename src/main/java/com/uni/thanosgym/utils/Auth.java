package com.uni.thanosgym.utils;

import com.uni.thanosgym.dao.CRUDAdministrador;
import com.uni.thanosgym.model.Admin;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.view.PanelDashboard;
import com.uni.thanosgym.view.auth.LoginPanel;

public class Auth {
    public static PanelDashboard panelDashboard;
    public static LoginPanel loginPanel;

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
        panelDashboard.showPanel();
    }

    public static void logOut() {
        UserPreferences.clearData();
        loginPanel.showPanel();
    }

    public static void verifySession() {
        if (Auth.isAdminLoggedIn()) {
            panelDashboard.showPanel();
        } else {
            loginPanel.showPanel();
        }
    }

}
