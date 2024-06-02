package thanosgym;

import config.DbConnection;
import config.Startup;
import model.Administrador;

public class Main {


    public static Administrador admin;

    public static void main(String[] args) {
        DbConnection.connectToDatabase();
        Startup.initWindow();
    }

}
