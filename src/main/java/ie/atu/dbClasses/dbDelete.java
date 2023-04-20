package ie.atu.dbClasses;

import ie.atu.jdbc.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class dbDelete {

    public static String deleteTable = "Boo";
    public static String deleteColumn = "Boo2";

    public static String refID = "Boo4";

    public static void main(String[] args) {



        try (Statement statement = connection.createStatement()) {
            PreparedStatement deleteStatement = connection.prepareStatement ("DELETE FROM ? WHERE ? = ?");
            deleteStatement.setString(1,deleteTable);
            deleteStatement.setString(2,deleteColumn);

            deleteStatement.setString(3,refID);

            int rowsDeleted = (statement.executeUpdate(deleteStatement.toString()));
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            System.out.println("Rows failed delete");
            e.printStackTrace();
        }
    }


}
