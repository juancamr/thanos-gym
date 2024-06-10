package com.uni.thanosgym.config;

import com.uni.thanosgym.controller.ControladorLogin;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.view.PanelLogin;
import com.uni.thanosgym.view.WindowSession;

public class Startup {
    public static void initWindow() {
        WindowSession vista = new WindowSession();
        new ControladorLogin(vista, new PanelLogin());
        FrameUtils.showWindow(vista, "Welcome");
        System.out.println("App started!");
    }
}
