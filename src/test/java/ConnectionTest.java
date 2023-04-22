import ie.atu.jdbc.dbConnection;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectionTest {

    @Test
    void testConnection() throws Exception {
        Connection connection;

        dbConnection dbConnection = new dbConnection();
        connection = dbConnection.connection();
        String testDbName = connection.getCatalog();
        assertEquals("MagnoliaLibrary", testDbName);
        connection.close();
    }
}
