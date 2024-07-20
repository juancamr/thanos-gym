package com.uni.thanosgym.config;
import java.sql.*;
import com.uni.thanosgym.utils.EnvVariables;

public class DbConnection {
    private static Connection connection; 

    private DbConnection() {}
    
    public static void connectToDatabase () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://juancamr.mysql.database.azure.com/thanosgym?serverTimezone=UTC", "juancamr", "09241688Jc$");
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
