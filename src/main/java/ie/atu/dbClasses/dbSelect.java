package ie.atu.dbClasses;

import ie.atu.jdbc.dbConnection;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class dbSelect {
    public static void main(String[] args) throws IOException, SQLException {
        System.out.println("Loading application properties");
        Properties properties = new Properties();
        properties.load(dbConnection.class.getClassLoader().getResourceAsStream("application.properties"));
        System.out.println("Connecting to the database");
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);

        String selectSQL = "SELECT name, author, publication, rented FROM Books";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectSQL)) {

            while (resultSet.next()) {
                String selectName = resultSet.getString("name");
                String selectAuthor = resultSet.getString("author");
                int selectPublication = resultSet.getInt("publication");
                String selectRented  = resultSet.getString("rented");

                System.out.println("Name: " + selectName + "\nAuthor: " + selectAuthor + "\nPublication: " + selectPublication + "\nRented: " + selectRented + "\n");
            }
        } catch (SQLException t) {
            t.printStackTrace();
        }

        connection.close();
    }
}