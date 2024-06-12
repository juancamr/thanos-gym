package com.uni.thanosgym.utils;

import com.uni.thanosgym.controller.ControladorSession;
import com.uni.thanosgym.controller.ControladorMainWindow;
import com.uni.thanosgym.model.Administrador;

public class Auth {

    public static boolean isAdminLoggedIn() {
        return !UserPreferences.getData().getFullName().isEmpty();
    }

    public static void signIn(Administrador admin) {
        UserPreferences.setData(admin);
        ControladorMainWindow.initMainWindow();
    }

    public static void logOut() {
        UserPreferences.clearData();
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
