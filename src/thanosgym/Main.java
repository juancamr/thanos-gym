package thanosgym;

import config.DbConnection;
import config.Startup;
import java.util.ArrayList;
import model.Administrador;
import model.*;

public class Main {

    public static ArrayList<Plan> listaPlanes;

    public static Administrador admin;

    public static void main(String[] args) {
        DbConnection.connectToDatabase();
        Startup.initWindow();
    }

}
