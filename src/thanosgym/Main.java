package thanosgym;

import config.DbConnection;
import model.Administrador;
import utils.FrameUtils;
import view.auth.WindowSession;

public class Main {

    public static Administrador admin;
    
    public static void main(String[] args) {
        DbConnection.connectToDatabase();
        FrameUtils.showWindow(new WindowSession(), "Welcome");
    }
    
}
