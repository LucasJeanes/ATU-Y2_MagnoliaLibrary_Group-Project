package ie.atu.dbTables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dbMusic {

    private String track;
    private String genre;
    private String artist;
    private String publication;
    private boolean rented;
    private Connection connection;

    public dbMusic(Connection connection) {
        this.connection = connection;
    }

    public dbMusic() {

    }

    public dbMusic(String track, String genre, String artist, String publication, boolean rented) {
        this.track = track;
        this.genre = genre;
        this.artist = artist;
        this.publication = publication;
        this.rented = rented;
    }

    //For Adding new music into Database
    public void addMusic(){

        String selectSQL = "INSERT INTO Music Values (?,?,?,?,?)";
        try {

            // Insert a new record into the "users" table
            PreparedStatement insertStatement = connection.prepareStatement(selectSQL);
            insertStatement.setString(1, track);
            insertStatement.setString(2, genre);
            insertStatement.setString(3, artist);
            insertStatement.setString(4, publication);
            insertStatement.setBoolean(5,rented);

            int inserted = (insertStatement.executeUpdate());
            System.out.println("New Music has been added: " + inserted);
            insertStatement.close();
        } catch (SQLException ex) {

            System.out.println("[ERROR] Adding music to database failed");
            ex.printStackTrace();
        }
    }

    //For Updating Existing Music Data
    public void eidtMusic(String columnToChange, String newInfo, String refColumn, String refID){

        String updateSQL = "UPDATE Music SET" + columnToChange + " = " + newInfo + " WHERE " + refColumn + " = " + refID;
        try
        {
            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);

            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
        } catch (SQLException ex) {
            System.out.println("[ERROR] Edit Music info failed. ");
            ex.printStackTrace();
        }
    }

    //For Deleting an Existing Data
    public void deleteMusic(String refColumn, String refID){

        String deleteSQL = "DELETE FROM Music WHERE " + refColumn + "=" + refID;
        try {
            PreparedStatement deleteStatement = connection.prepareStatement (deleteSQL);
            //deleteStatement.setString(2,refColumn);
            //deleteStatement.setString(3,refID);

            int rowsDeleted = (deleteStatement.executeUpdate());
            System.out.println("Music deleted: " + rowsDeleted);
        } catch (SQLException ex) {
            System.out.println("[ERROR] Deleting music failed");
            ex.printStackTrace();
        }
    }

    // Checking out Music for rent
    public void checkout(String refColumn, String refID){
        String updateSQL = "UPDATE Music SET rented = 1 WHERE" + refColumn + "=" + refID;

        try {
            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);
            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows Updated: " + rowsUpdated);
        }catch (SQLException ee){
            System.out.println("[ERROR] Music Checkout Failed. ");
            ee.printStackTrace();
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

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}


