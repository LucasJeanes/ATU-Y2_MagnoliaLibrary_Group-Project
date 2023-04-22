import ie.atu.dbTables.dbBooks;
import ie.atu.jdbc.dbConnection;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BooksTest {
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
    public void testAddBook() {

        dbBooks testBook = new dbBooks(connection,"testName", "testAuthor", "9999", false);
        testBook.addBook();
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Books WHERE publication = 9999")) {

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
    public void testCheckoutBook() {
        dbBooks testBook = new dbBooks(connection,"testName", "testAuthor", "9999", false);
        testBook.checkout("publication","9999");

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Books WHERE publication = 9999")) {

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
    public void testDeleteBook() throws SQLException {
        dbBooks testBook = new dbBooks(connection,"testName", "testAuthor", "9999", false);

        testBook.deleteBook("publication","9999");

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Books WHERE publication = 9999")) {

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