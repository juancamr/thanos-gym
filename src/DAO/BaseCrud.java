package DAO;

import config.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseCrud {
    Statement st;
    Connection connection;
    ResultSet rs;
    PreparedStatement ps;

    public BaseCrud () {
        try {
            connection = DbConnection.getConnection();
            st = connection.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
