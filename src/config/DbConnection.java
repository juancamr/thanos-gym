package config;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;
import thanosgym.Main;

public class DbConnection {
    public static Connection connection;

    private DbConnection() {}
    
    public static void connectToDatabase () {
        try {
            String host = Main.dotenv.get("DB_HOST");
            String user = Main.dotenv.get("DB_USER");
            String password = Main.dotenv.get("DB_PASSWORD");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(host, user, password);
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
