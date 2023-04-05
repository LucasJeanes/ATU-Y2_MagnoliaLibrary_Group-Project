package ie.atu.jdbc;

import java.sql.*;
import java.util.*;

public class TestdbConnection {

//  THIS 'properties' function retrieves login details & URL for database connection
    public static void main(String[] args) throws Exception {
        System.out.println("Loading application properties");
        Properties properties = new Properties();
        properties.load(TestdbConnection.class.getClassLoader().getResourceAsStream("application.properties"));

        System.out.println("Connecting to the database");

//THIS LINE OF CODE TO CONNECT TO DATABASE!!!!!
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

        System.out.println("Database connection test: " + connection.getCatalog());

        System.out.println("Closing database connection");
        connection.close();
    }
}