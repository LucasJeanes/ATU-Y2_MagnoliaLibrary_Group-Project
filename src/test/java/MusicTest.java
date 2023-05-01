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
        dbMusic testMusic = new dbMusic(connection,"testTrackName", "testGenre", "testArtist", "2010", false);
        testMusic.addItem();
        try(Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Music WHERE publication = 2010")){

            while(resultSet.next()){
                String track = resultSet.getString("track");
                assertEquals("testTrack", track);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    @Order(2)
    public void testCheckoutMusic(){
        dbMusic testMusic = new dbMusic(connection, "testTrackName", "testGenre","testArtist", "2010", true );
        testMusic.checkout("publication", "1980");

        try(Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Music WHERE publication = 2010")){

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
        dbMusic testMusic = new dbMusic(connection, "testTrackName", "TestGenre", "TestArtist", "2010",false);

        testMusic.deleteItem("publication", "1111");

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Music WHERE publication = 2010")){

            while (resultSet.next()){
                String name = resultSet.getString("name");
                assertNull(name);
            }
        } catch (SQLException ee){
            ee.printStackTrace();
        }
        connection.close();
    }
}


