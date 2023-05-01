import  ie.atu.dbTables.dbBooks;
import ie.atu.dbTables.dbMusic;
import ie.atu.jdbc.dbConnection;
import org.junit.jupiter.api.*;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MusicTest {
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
    public void testAddItem(){
        dbMusic testMusic = new dbMusic(connection,"testTrackName", "testGenre", "testArtist", "9998", false);
        testMusic.addItem();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM music WHERE publication = 9998")){

            while(resultSet.next()){
                String track = resultSet.getString("track");
                assertEquals("testTrackName", track);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    public void testCheckoutMusic(){
        dbMusic testMusic = new dbMusic(connection, "testTrackName", "testGenre","testArtist", "9999", true );
        testMusic.checkout("publication", "9999");

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM music WHERE publication = 9999")){

            while(resultSet.next()){
                String rented = resultSet.getString("rented");
                assertEquals("1", rented);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    public void testDeleteItem() throws SQLException{
        dbMusic testMusic = new dbMusic(connection, "testTrackName", "TestGenre", "TestArtist", "9999",true);

        testMusic.deleteItem("publication", "9999");

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM music WHERE publication = 9999")){

            while (resultSet.next()){
                String track = resultSet.getString("track");
                assertNull(track);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        connection.close();
    }
}


