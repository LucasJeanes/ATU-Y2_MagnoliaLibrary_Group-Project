package ie.atu.dbClasses;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class dbDelete {

    public static String deleteTable = "Boob";
    public static String deleteColumn = "Boob2";

    public static String refID = "Boob4";
    public static String refValue = "Boob5";

    public static void main(String[] args) {



        try (//Connect to database
             Statement statement = connection.createStatement()) {
            PreparedStatement updateStatement = connection.prepareStatement ("UPDATE ? SET ? = ? WHERE ? = ?");
            updateStatement.setString(1,deleteTable);
            updateStatement.setString(2,deleteColumn);

            updateStatement.setString(4,refID);
            updateStatement.setString(5,refValue);
            int rowsDeleted = (statement.executeUpdate(updateStatement.toString()));
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            System.out.println("Rows failed delete");
            e.printStackTrace();
        }
    }
}
