/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.uni.thanosgym;

import com.uni.thanosgym.config.DbConnection;
import com.uni.thanosgym.config.Startup;

/**
 *
 * @author juancamr
 */
public class Thanosgym {

    public static void main(String[] args) {
        DbConnection.connectToDatabase();
        Startup.initWindow();
    }

}
