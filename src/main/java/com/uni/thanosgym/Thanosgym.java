package com.uni.thanosgym;

import com.uni.thanosgym.config.DbConnection;
import com.uni.thanosgym.config.Startup;

public class Thanosgym {

    public static void main(String[] args) {
        DbConnection.connectToDatabase();
        Startup.initWindow();
    }

}
