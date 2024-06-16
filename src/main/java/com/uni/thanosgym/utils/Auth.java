package com.uni.thanosgym.utils;

import com.uni.thanosgym.controller.ControladorSession;
import com.uni.thanosgym.dao.CRUDAdministrador;
import com.uni.thanosgym.controller.ControladorMainWindow;
import com.uni.thanosgym.model.Administrador;
import com.uni.thanosgym.model.Response;

public class Auth {

    public static boolean isAdminLoggedIn() {
        Administrador admin = UserPreferences.getData();
        boolean adminPersistence = admin.isSesionPersistente();
        Response<Administrador> response = CRUDAdministrador.getInstance().getById(admin.getId());
        if (adminPersistence && response.isSuccess()) {
            return true;
        } 
        return false;
    }

    public static void signIn(Administrador admin) {
        UserPreferences.setData(admin);
        ControladorMainWindow.initMainWindow();
    }

    public static void logOut() {
        UserPreferences.clearData();
        FrameUtils.showWindow(ControladorSession.getWindow(), "Iniciar sesion");
        ControladorSession.showLoginPanel();
    }

    public static void verifySession() {
        if (Auth.isAdminLoggedIn()) {
            ControladorMainWindow.initMainWindow();
        } else {
            ControladorSession.initWindow();
        }
    }

}
