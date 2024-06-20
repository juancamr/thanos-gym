package com.uni.thanosgym.utils;

import java.util.prefs.Preferences;

import com.uni.thanosgym.model.Admin;

public class UserPreferences {

    public static Preferences prefs = Preferences.userNodeForPackage(UserPreferences.class);
    public static final String FULLNAME = "fullname";
    public static final String ID = "id";
    public static final String PERSISTENCE = "persistence";

    public static void setData(Admin admin) {
        prefs.put(FULLNAME, admin.getFullName());
        prefs.put(ID, String.valueOf(admin.getId()));
        prefs.put(PERSISTENCE, String.valueOf(admin.isSesionPersistence()));
    }

    public static Admin getData() {
        Admin admin = new Admin.Builder()
                .setFullName(prefs.get(FULLNAME, ""))
                .setId(Integer.parseInt(prefs.get(ID, "0")))
                .build();
        admin.setPersistencia(Boolean.parseBoolean(prefs.get(PERSISTENCE, "false")));
        return admin;
    }

    public static void clearData() {
        try {
            prefs.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
