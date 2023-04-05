package ie.atu.dbClasses;

import java.sql.PreparedStatement;
import ie.atu.jdbc.dbConnection;
import java.sql.*;
import java.sql.Connection;

//import java.sql.*;

public class dbInsert {
    public static String insertTable = "Boob";
    public static String insertColumn = "Boob2";
    public static String insertNewValue = "Boob3";
    public static String refID = "Boob4";
    public static String refValue = "Boob5";

    public static void main(String[] args) {
        String selectSQL = "INSERT INTO ?" + "VALUES ?";


        try (Connection connection = dbConnection.getConnection();
             Statement statement = connection.createStatement()) {
            PreparedStatement insertStatement = connection.prepareStatement(selectSQL);
            insertStatement.setString(1,insertTable);
            insertStatement.setString(2,insertColumn);
            insertStatement.setString(3,insertNewValue);
            insertStatement.setString(4,refID);
            insertStatement.setString(5,refValue);
            int inserted = (statement.executeUpdate(insertStatement.toString()));
            System.out.println("The following has successfully been inserted: " + inserted);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
