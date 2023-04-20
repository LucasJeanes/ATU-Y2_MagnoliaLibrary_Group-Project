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

//Test insert - to be replaced with table specific info inherited from class(ie. books, music etc.)
        String insertTable = "Books";
        String insertName = "BookTest";
        String insertAuthor = "AuthorName";
        String refPublic = "1996";
        String refRented = "0";
        
        String selectSQL = "INSERT INTO ? VALUES (?, ?, ?, ?)";
        
        try {

            // Insert a new record into the "users" table
            PreparedStatement insertStatement = connection.prepareStatement(selectSQL);
            //insertStatement.setString(1,insertTable);
            insertStatement.setString(1,insertName);
            insertStatement.setString(2,insertAuthor);
            insertStatement.setString(3,refPublic);
            insertStatement.setString(4,refRented);
            //int row = insertStatement.executeUpdate();

            int inserted = (insertStatement.executeUpdate());
            System.out.println("The following has successfully been inserted: " + inserted);
        } catch (SQLException ex) {

            System.out.println("Record insert failed.");
            ex.printStackTrace();
        }
        // Close the connection
        connection.close();
    }
}
