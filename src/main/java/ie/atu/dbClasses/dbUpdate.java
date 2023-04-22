package ie.atu.dbClasses;

import java.io.IOException;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;
import ie.atu.jdbc.dbConnection;

import java.sql.*;
import java.sql.Connection;
import java.util.Properties;

//"UPDATE ? SET ? = ? WHERE ? = ?"
public class dbUpdate {
        public static void main(String[] args) throws IOException, SQLException {
        String tableName = "Books";
        String columnToChange = "publication";
        int newInfo = 69420;
        String refColumn = "book_id";
        int refID = 16;

        System.out.println("Loading application properties");
        Properties properties = new Properties();
        properties.load(dbConnection.class.getClassLoader().getResourceAsStream("application.properties"));
        System.out.println("Connecting to the database");
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);


        try
        {
            String updateSQL = "UPDATE " + tableName + " SET " + columnToChange + " = " + newInfo + " WHERE " + refColumn + " = " + refID;
            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);

            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

            updateStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
