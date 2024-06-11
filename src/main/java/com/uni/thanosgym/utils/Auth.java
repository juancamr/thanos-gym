package com.uni.thanosgym.utils;

import com.uni.thanosgym.controller.ControladorHome;
import com.uni.thanosgym.controller.ControladorLogin;
import com.uni.thanosgym.controller.ControladorMainWindow;
import com.uni.thanosgym.model.Administrador;
import com.uni.thanosgym.view.WindowSession;
import com.uni.thanosgym.view.HomePanel;
import com.uni.thanosgym.view.MainWindow;
import com.uni.thanosgym.view.PanelLogin;

public class Auth {

    public static boolean isAdminLoggedIn() {
        return !UserPreferences.getData().getFullName().isEmpty();
    }

    public static void signIn(Administrador admin) {
        UserPreferences.setData(admin);
        MainWindow view = new MainWindow();
        new ControladorMainWindow(view);
        ControladorHome.showHomePanel(view, new HomePanel());
    }

    public static void logOut() {
        UserPreferences.clearData();
        new ControladorLogin(new WindowSession(), new PanelLogin());
    }

    public static void verifySession() {
        if (Auth.isAdminLoggedIn()) {
            MainWindow view = new MainWindow();
            new ControladorMainWindow(view);
            ControladorHome.showHomePanel(view, new HomePanel());
        } else {
            new ControladorLogin(new WindowSession(), new PanelLogin());
        }
    }

}
