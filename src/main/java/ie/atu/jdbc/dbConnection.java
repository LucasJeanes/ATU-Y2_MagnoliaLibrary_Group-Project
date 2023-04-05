package ie.atu.jdbc;

import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class dbConnection {

    private static final Logger log;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log =Logger.getLogger(dbConnection.class.getName());
    }
//  THIS 'properties' function retrieves login details & URL for database connection
    public static void main(String[] args) throws Exception {
        log.info("Loading application properties");
        Properties properties = new Properties();
        properties.load(dbConnection.class.getClassLoader().getResourceAsStream("application.properties"));

        log.info("Connecting to the database");

//THIS LINE OF CODE TO CONNECT TO DATABASE!!!!!
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

        log.info("Database connection test: " + connection.getCatalog());

        log.info("Create database schema");
        Scanner scanner = new Scanner(dbConnection.class.getClassLoader().getResourceAsStream("schema.sql"));
        Statement statement = connection.createStatement();
        while (scanner.hasNextLine()) {
            statement.execute(scanner.nextLine());
        }
        log.info("Closing database connection");
        connection.close();
    }
}