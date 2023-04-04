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

//      This is an example from the online guide I linked to insert data into a table that it creates.
//      LINK TO THE GUIDE >>>>>> https://learn.microsoft.com/en-us/azure/azure-sql/database/connect-query-java?view=azuresql

		Todo todo = new Todo(1L, "configuration", "congratulations, you have set up JDBC correctly!", true);
        insertData(todo, connection);

        /*
        todo = readData(connection);
        todo.setDetails("congratulations, you have updated data!");
        updateData(todo, connection);
        deleteData(todo, connection);
*/


        log.info("Closing database connection");
        connection.close();


    }
    private static void insertData(Todo todo, Connection connection) throws SQLException {
        log.info("Insert data");
        PreparedStatement insertStatement = connection
                .prepareStatement("INSERT INTO todo (id, description, details, done) VALUES (?, ?, ?, ?);");

        insertStatement.setLong(1, todo.getId());
        insertStatement.setString(2, todo.getDescription());
        insertStatement.setString(3, todo.getDetails());
        insertStatement.setBoolean(4, todo.isDone());
        insertStatement.executeUpdate();
    }

}