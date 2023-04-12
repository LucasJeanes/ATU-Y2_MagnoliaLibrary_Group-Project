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

        try {

            // Insert a new record into the "users" table
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Books (name, author, publication, rented) VALUES (?, ?,?,?)");
            stmt.setString(1, "templateBook");
            stmt.setString(2, "Paul");
            stmt.setString(3, "1968");
            stmt.setString(4, "0");
            stmt.executeUpdate();

            System.out.println("Insert completed successfully.");
        } catch (SQLException ex) {

            System.out.println("Record insert failed.");
            ex.printStackTrace();
        }
        // Close the connection
        connection.close();
    }
}