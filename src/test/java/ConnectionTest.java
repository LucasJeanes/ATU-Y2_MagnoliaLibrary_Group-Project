import ie.atu.jdbc.dbConnection;
import org.junit.jupiter.api.Test;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConnectionTest {

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
    void testConnection() throws Exception {
        String testDbName = connection.getCatalog();
        assertEquals("magnolia_rebornlib", testDbName);
        connection.close();
    }
}
