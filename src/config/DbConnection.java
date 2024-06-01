package config;
import java.sql.*;

public class DbConnection {
    public static Connection connection;
    public static Statement st;
    public static PreparedStatement ps;
    public static ResultSet rs;
    
    public DbConnection() {
    }
    
    public static void connectToDatabase () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://172.17.0.2:3306/thanosgym", "root", "dev");
            st = connection.createStatement();
            System.out.println("Connected to database!");
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("No se pudo realizar la conexion a la base de datos");
            System.exit(0);
        }
    }
    
    public Connection getConnection() {
        if (connection == null)
            connectToDatabase();
        return connection;
    }
    
    public Statement getStatement() {
        if (st == null) 
            connectToDatabase();
        return st;
    }
}
