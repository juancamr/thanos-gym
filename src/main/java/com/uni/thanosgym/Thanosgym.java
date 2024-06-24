package com.uni.thanosgym;

import com.uni.thanosgym.view.PanelPlan;
import com.uni.thanosgym.view.auth.LoginPanel;

public class Thanosgym {

    public static void main(String[] args) {
//        DbConnection.connectToDatabase();
//        Auth.verifySession();
        LoginPanel.showLoginPanel();
        PanelPlan.showPanel();
    }

}
