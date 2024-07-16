package com.uni.thanosgym.utils;

import com.juancamr.route.Router;
import com.uni.thanosgym.dao.CRUDAdministrador;
import com.uni.thanosgym.model.Admin;
import com.uni.thanosgym.model.Response;

public class Auth {

    public static boolean isAdminLoggedIn() {
        Admin admin = UserPreferences.getData();
        boolean adminPersistence = admin.isSesionPersistence();
        Response<Admin> response = CRUDAdministrador.getInstance().getById(admin.getId());
        return adminPersistence && response.isSuccess();
    }

    public static void signIn(Admin admin) {
        UserPreferences.setData(admin);
        Router.getInstance().go("dashboard");
    }

    public static void logOut() {
        UserPreferences.clearData();
        Router.getInstance().go("auth/login");
    }

    public static void verifySession() {
        if (Auth.isAdminLoggedIn()) {
            Router.getInstance().go("dashboard");
        } else {
            Router.getInstance().go("auth/login");
        }
    }

}
