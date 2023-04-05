package ie.atu.dbClasses;
import ie.atu.jdbc.Todo;
import java.sql.*;

public class dbInsert {

    private static void insertData(Todo todo, Connection connection) throws SQLException {
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO todo (id, description, details, done) VALUES (?, ?, ?, ?);");

        insertStatement.setLong(1, todo.getId());
        insertStatement.setString(2, todo.getDescription());
        insertStatement.setString(3, todo.getDetails());
        insertStatement.setBoolean(4, todo.isDone());
        insertStatement.executeUpdate();
    }
}
