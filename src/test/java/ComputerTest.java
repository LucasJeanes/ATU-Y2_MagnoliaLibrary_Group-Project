import ie.atu.dbTables.dbComputer;
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


import ie.atu.dbTables.dbBooks;
        import ie.atu.dbTables.dbComputer;
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


        import ie.atu.dbTables.dbBooks;
        import ie.atu.jdbc.dbConnection;
        import org.junit.jupiter.api.*;
        import static org.junit.jupiter.api.Assertions.*;
        import java.sql.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ComputerTest {
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
        dbComputer testComputer = new dbComputer(connection,"MacBookXX1", "testAuthor","great", "deeell","9999", false);
        testComputer.addItem();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Computer WHERE price = 9999")) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                assertEquals("MacBookXX1",name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    public void testCheckoutComputer() {
        dbComputer testComputer = new dbComputer(connection,"MacBookXX1", "testAuthor","great", "deeell","9999", false);
        testComputer.checkout("price","9999");

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Computer WHERE price = 9999")) {

            while (resultSet.next()) {
                String rented = resultSet.getString("rented");
                assertEquals("1",rented);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    public void testDeleteItem() throws SQLException {
        dbComputer testComputer = new dbComputer(connection,"MacBookXX1", "testAuthor","great", "deeell","9999", false);

        testComputer.deleteItem("price","9999");

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Computer WHERE price = 9999")) {

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