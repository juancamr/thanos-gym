package com.uni.thanosgym.config;
import java.sql.*;
import com.uni.thanosgym.utils.EnvVariables;

public class DbConnection {
    private static Connection connection; 

    private DbConnection() {}
    
    public static void connectToDatabase () {
        try {
            String host = EnvVariables.getInstance().get("DB_HOST");
            String user = EnvVariables.getInstance().get("DB_USER");
            String password = EnvVariables.getInstance().get("DB_PASSWORD");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + host, user, password);
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
