package com.uni.thanosgym;

import com.uni.thanosgym.config.DbConnection;
import com.uni.thanosgym.config.Router;
import com.uni.thanosgym.utils.Auth;

public class Thanosgym {

    public static void main(String[] args) {
        int dimensions[] = {1060, 690};
        String packageRoute = "com.uni.thanosgym.routes";

        Router.getInstance().init(dimensions, packageRoute);
        DbConnection.connectToDatabase();
        Auth.verifySession();
    }

}
