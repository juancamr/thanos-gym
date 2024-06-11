package com.uni.thanosgym;

import com.uni.thanosgym.config.DbConnection;
import com.uni.thanosgym.utils.Auth;

public class Thanosgym {

    public static void main(String[] args) {
        DbConnection.connectToDatabase();
        Auth.verifySession();
    }

}
