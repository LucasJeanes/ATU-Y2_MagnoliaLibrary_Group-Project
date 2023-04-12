package ie.atu.jdbc;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class dbProperties {
    private static String PROPERTIES_FILE = "application.properties";
    private static Properties properties = new Properties();

    static {
        try (InputStream input = dbProperties.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getUrl() {
        return properties.getProperty("url");
    }

    public static String getUsername() {
        return properties.getProperty("user");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }
}