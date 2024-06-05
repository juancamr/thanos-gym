
package com.uni.thanosgym.preferences;

import com.uni.thanosgym.model.Administrador;

public class UserPreference {
    private static Administrador admin;
    
    public static void setAdminData(Administrador data) {
        admin = data;
    }
    
    public static Administrador getAdminData() {
        return admin;
    }
    
    public static void deleteData() {
        admin = null;
    }
    
}
