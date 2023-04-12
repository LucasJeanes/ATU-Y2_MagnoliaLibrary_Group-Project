package ie.atu.dbClasses;

import java.io.IOException;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;
import ie.atu.jdbc.TestdbConnection;
import ie.atu.jdbc.dbConnection;

import java.sql.*;
import java.sql.Connection;
import java.util.Properties;

//"UPDATE ? SET ? = ? WHERE ? = ?"
public class dbUpdate {
        public static void main(String[] args) throws IOException, SQLException {
        String TableName = "Books";
        String SetColumnName = "name";
        String NewName = "DewansBookLASTONEPLS";
        String refColumn = "book_id";
        int IDNumber = 16;

        System.out.println("Loading application properties");
        Properties properties = new Properties();
        properties.load(TestdbConnection.class.getClassLoader().getResourceAsStream("application.properties"));
        System.out.println("Connecting to the database");
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);

        try
        {
            String updateSQL = "UPDATE Books SET name = ? WHERE " + refColumn + " = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);

            //updateStatement.setString(1,TableName);

            //updateStatement.setString(1,SetColumnName);
            updateStatement.setString(1,NewName);
            updateStatement.setInt(2,IDNumber);

            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

            connection.close();
            updateStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
