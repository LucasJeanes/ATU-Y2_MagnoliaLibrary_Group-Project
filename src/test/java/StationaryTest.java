import ie.atu.dbTables.dbBooks;
import ie.atu.dbTables.dbStationary;
import ie.atu.jdbc.dbConnection;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StationaryTest {
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
        dbStationary testStationary = new dbStationary(connection,"testName", "testDescription", 99, 99, 999);
        testStationary.addItem();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Stationary WHERE price = 10")) {

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                assertEquals("testName", name );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    public void testDeleteItem() throws SQLException {
        dbStationary testStationary = new dbStationary(connection,"testName", "testDescription", 99, 99, 999);

        testStationary.deleteItem("stock","999");

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM stationary WHERE stock = 999")) {

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
