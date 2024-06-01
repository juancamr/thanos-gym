package thanosgym;

import config.DbConnection;

public class Main {
    
    public static void main(String[] args) {
        DbConnection.connectToDatabase();
        System.out.println("holamundo");
    }
    
}
