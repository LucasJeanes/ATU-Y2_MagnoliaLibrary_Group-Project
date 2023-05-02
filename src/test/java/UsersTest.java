import ie.atu.dbTables.dbBooks;
import ie.atu.dbTables.dbUsers;
import ie.atu.jdbc.dbConnection;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsersTest {
    static Connection connection;

    static {
        dbConnection dbConnection = new dbConnection();
        try {
            connection = dbConnection.connection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void testAddItem() {
        dbUsers testUser = new dbUsers(connection,"admin", "Magnolia", "9999999999", 99, "admin");
        testUser.addItem();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Users WHERE name = \"testName\"")) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                assertEquals("testName",name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    public void testEditItem() {
        String columnToChange = "address";
        String newInfo = "NewAddress";
        String refColumn = "name";
        String refID = "testName";
        dbUsers testUser = new dbUsers(connection,"testName", "testAdress", "9999999999", 99, "template");
        testUser.editItem(columnToChange, newInfo, refColumn, refID);
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE name = \"testName\"")) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                assertEquals("testName",name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    public void testDeleteItem() throws SQLException {
        dbUsers testUser = new dbUsers(connection,"testName", "testAdress", "9999999999", 99, "template");

        testUser.deleteItem("name","testName");

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM book WHERE name = \"testName\"")) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                assertNull(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection.close();
    }
}
