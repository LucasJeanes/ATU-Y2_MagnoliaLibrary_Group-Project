package ie.atu.dbClasses;

import java.sql.PreparedStatement;

import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;
import ie.atu.jdbc.dbConnection;

import java.sql.*;
import java.sql.Connection;

public class dbUpdate {
    public static String updateTable = "Boob";
    public static String updateColumn = "nipple";
    public static String newValue = "smallerNipple";
    public static String refID = "Chest";
    public static String refValue = "legal";


    public static void main(String[] args) {



        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()) {
            PreparedStatement updateStatement = connection.prepareStatement ("UPDATE ? SET ? = ? WHERE ? = ?");
            updateStatement.setString(1,updateTable);
            updateStatement.setString(2,updateColumn);
            updateStatement.setString(3,newValue);
            updateStatement.setString(4,refID);
            updateStatement.setString(5,refValue);
            int rowsUpdated = (statement.executeUpdate(updateStatement.toString()));
            System.out.println("Rows updated: " + rowsUpdated);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
