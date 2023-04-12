package ie.atu.jdbc;

import java.sql.*;
import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

public class dbConnection {
    //later we will look at storing this type of data in a better location like a properties file
    private static String url = dbProperties.getUrl();
    private static String user = dbProperties.getUsername();
    private static String password = dbProperties.getPassword();
    private static DataSource dataSource;


    //notice the static has no name?
    //The static block does not have a method name because it is a special block of code that
    // is executed when the class is loaded into memory. It is used to initialize static variables and perform
    // any other one-time setup that the class may require.
    static {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(url);
        mysqlDataSource.setUser(user);
        mysqlDataSource.setPassword(password);
        dataSource = mysqlDataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}