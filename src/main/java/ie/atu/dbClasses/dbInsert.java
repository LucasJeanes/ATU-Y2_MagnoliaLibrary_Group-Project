package ie.atu.dbClasses;
import ie.atu.jdbc.TestdbConnection;
import ie.atu.jdbc.dbConnection;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class dbInsert {
    public static void main(String[] args) throws SQLException, IOException {
        //Connection connection = dbConnection.getConnection();
        System.out.println("Loading application properties");
        Properties properties = new Properties();
        properties.load(TestdbConnection.class.getClassLoader().getResourceAsStream("application.properties"));
        System.out.println("Connecting to the database");
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);

        String insertTable = "Boob";
        String insertColumn = "Boob2";
        String insertNewValue = "Boob3";
        String refID = "Boob4";
        String refValue = "Boob5";
        
        String selectSQL = "INSERT INTO ?" + "VALUES ?";
        
        try {

            // Insert a new record into the "users" table
            PreparedStatement insertStatement = connection.prepareStatement(selectSQL);
            insertStatement.setString(1,insertTable);
            insertStatement.setString(2,insertColumn);
            insertStatement.setString(3,insertNewValue);
            insertStatement.setString(4,refID);
            insertStatement.setString(5,refValue);
            insertStatement.executeUpdate();

            int inserted = (insertStatement.executeUpdate(insertStatement.toString()));
            System.out.println("The following has successfully been inserted: " + inserted);
        } catch (SQLException ex) {

            System.out.println("Record insert failed.");
            ex.printStackTrace();
        }
        // Close the connection
        connection.close();
    }
}
