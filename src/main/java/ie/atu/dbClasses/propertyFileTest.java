package ie.atu.dbClasses;
import ie.atu.jdbc.dbProperties;

public class propertyFileTest {
    public static void main(String[] args) {
        String dbPassword = dbProperties.getPassword();
        String dbUser = dbProperties.getUsername();
        String dbURL = dbProperties.getUrl();
        System.out.println("\nDatabase password: " + dbPassword);
        System.out.println("\nDatabase username: " + dbUser);
        System.out.println("\nDatabase URL: " + dbURL);
    }
}
