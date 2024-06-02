package thanosgym;

import config.DbConnection;
import config.Startup;
import io.github.cdimascio.dotenv.Dotenv;
import model.Administrador;

public class Main {

    public static Dotenv dotenv;
    public static Administrador admin;

    public static void main(String[] args) {
        dotenv = Dotenv.load();
        DbConnection.connectToDatabase();
        Startup.initWindow();
    }

}
