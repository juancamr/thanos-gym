package config;
import java.sql.*;

public class DbConnection {
    public static Connection connection;

    private DbConnection() {}
    
    public static void connectToDatabase () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thanosgym", "root", "root"); //*** dev 
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
