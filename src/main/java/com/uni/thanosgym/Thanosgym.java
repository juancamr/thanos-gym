package com.uni.thanosgym;

import com.uni.thanosgym.config.DbConnection;

import com.juancamr.route.Router;
import com.uni.thanosgym.utils.Auth;

public class Thanosgym {

    public static void main(String[] args) {
        int dimensions[] = { 1060, 690 };
        String packageRoute = "com.uni.thanosgym.view.routes";
        String packageLayouts = "com.uni.thanosgym.view.layouts";

        Router.getInstance().init(dimensions, packageRoute, packageLayouts);
        DbConnection.connectToDatabase();
        Auth.verifySession();
    }
}
