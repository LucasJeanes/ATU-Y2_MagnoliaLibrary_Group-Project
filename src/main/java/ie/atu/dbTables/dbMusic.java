package ie.atu.dbTables;

import ie.atu.jdbc.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class dbMusic {

    //For Inserts
    String insertTable = "";
    String insertTrack = "";
    String insertGenre = "";
    String insertArtist = "";
    String refPublic = "1996";
    String refRented = "0";

    //For Updates
    String TableName = "Books";
    String SetColumnName = "name";
    String NewName = "";
    String refColumn = "book_id";
    int IDNumber = 16;

    //For Deletes
    public static String deleteTable = "Boob";
    public static String deleteColumn = "Boob2";
    public static String refID = "Boob4";


    private String track;
    private String genre;
    private String artist;
    private int publication;
    private boolean rented;

    public dbMusic() {

    }

    public dbMusic(String track, String genre, String artist, int publication, boolean rented) {
        this.track = track;
        this.genre = genre;
        this.artist = artist;
        this.publication = publication;
        this.rented = rented;
    }

    //For Adding new music
    public void addMusic(){
        try {

            // Insert a new record into the "users" table
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO ? VALUES (?, ?, ?, ?)");
            insertStatement.setString(1,insertTable);
            insertStatement.setString(2,insertTrack);
            insertStatement.setString(3,insertGenre);
            insertStatement.setString(4,insertArtist);
            insertStatement.setString(5,refPublic);
            insertStatement.setString(6,refRented);
            int row = insertStatement.executeUpdate();
            int inserted = (insertStatement.executeUpdate());
            System.out.println("The following has successfully been inserted: " + inserted);
        } catch (SQLException ex) {

            System.out.println("Record insert failed.");
            ex.printStackTrace();
        }
    }

    //For Updating Existing Data
    public void updateMusic(){
        try
        {
            String updateSQL = "UPDATE ? SET name = ? WHERE ? = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);

            updateStatement.setString(1,TableName);
            updateStatement.setString(2,SetColumnName);
            updateStatement.setString(3,NewName);
            updateStatement.setString(4,refColumn);
            updateStatement.setInt(5,IDNumber);

            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

            //connection.close();
            updateStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //For Deleting an Existing Data
    public void deleteMusic(){
        try (Statement statement = connection.createStatement()) {
            PreparedStatement deleteStatement = connection.prepareStatement ("DELETE FROM ? WHERE ? = ?");
            deleteStatement.setString(1,deleteTable);
            deleteStatement.setString(2,deleteColumn);
            deleteStatement.setString(3,refID);

            int rowsDeleted = (statement.executeUpdate(deleteStatement.toString()));
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            System.out.println("Rows failed delete");
            e.printStackTrace();
        }
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getPublication() {
        return publication;
    }

    public void setPublication(int publication) {
        this.publication = publication;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}


