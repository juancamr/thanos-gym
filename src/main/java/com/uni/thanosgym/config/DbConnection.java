package com.uni.thanosgym.config;
import java.sql.*;

public class DbConnection {
    private static Connection connection; 

    private DbConnection() {}
    
    public static void connectToDatabase () {
        try {
            String host = "172.17.0.2";
            String database = "thanosgym";
            String user = "root";
            String password = "dev";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?serverTimezone=UTC", host, database), user, password);
            System.out.println("Connected to database!");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("No se pudo realizar la conexion a la base de datos");
            System.exit(0);
        }
    }
    
    public static Connection getConnection() {
        if (connection == null)
            connectToDatabase();
        return connection;
    }
    
}
