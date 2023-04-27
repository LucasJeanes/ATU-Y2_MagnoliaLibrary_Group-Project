package ie.atu.jdbc;

import java.sql.*;
import java.util.*;

public class dbConnection {
    public Connection connection() throws Exception {
        //System.out.println("Loading application properties");
        Properties properties = new Properties();
        properties.load(dbConnection.class.getClassLoader().getResourceAsStream("application.properties"));
        System.out.println("Connecting to the database");
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        System.out.println("Connected to database: " + connection.getCatalog());
        return connection;
    }
}