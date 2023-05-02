package ie.atu.dbTables;

import java.sql.*;

public class dbMusic implements dbMethods{

    public String musicColumns = "track, genre, artist, publication, rented";
    private String track;
    private String genre;
    private String artist;
    private String publication;
    private boolean rented;
    private Connection connection;

    public dbMusic(Connection connection, String track, String genre, String artist, String publication, boolean rented) {
        this.connection = connection;
        this.track = track;
        this.genre = genre;
        this.artist = artist;
        this.publication = publication;
        this.rented = rented;
    }

    public dbMusic() {

    }

    public dbMusic(Connection connection) {
        this.connection = connection;
    }



    @Override
    //For Adding new music into Database
    public void addItem(){

        String selectSQL = "INSERT INTO Music (track,genre,artist,publication,rented) VALUES (?,?,?,?,?)";
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
    @Override
    //For Updating Existing Music Data
    public void editItem(String columnToChange, String newInfo, String refColumn, String refID){

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
    @Override
    //For Deleting an Existing Data
    public void deleteItem(String refColumn, String refID){

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
        String updateSQL = "UPDATE music SET rented = 1 WHERE " + refColumn + " = " + refID;

        try {
            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);
            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows Updated: " + rowsUpdated);
        }catch (SQLException ee){
            System.out.println("[ERROR] Music Checkout Failed. ");
            ee.printStackTrace();
        }
    }
    @Override
    public void isAvailable(String refColumn,String refID) {
        String availabilityCheckSQL = "SELECT * FROM music WHERE " + refColumn + " = " + refID;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(availabilityCheckSQL)) {

            while (resultSet.next()) {
                String musicID = resultSet.getString("id");
                String track = resultSet.getString("track");
                String genre = resultSet.getString("genre");
                String artist = resultSet.getString("artist");
                String publication = resultSet.getString("publication");
                String rented = resultSet.getString("rented");
                if(rented.equals("1")) {
                    rented = "Rented";
                } else {
                    rented = "In Stock";
                }
                System.out.println(musicID + ": " + track + " | " + genre + " | " + artist + " | " + publication + " | " + rented);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void isAvailable() {
        String availabilityCheckSQL = "SELECT * FROM music WHERE rented = 0";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(availabilityCheckSQL)) {

            while (resultSet.next()) {
                String musicID = resultSet.getString("id");
                String track = resultSet.getString("track");
                String genre = resultSet.getString("genre");
                String artist = resultSet.getString("artist");
                String publication = resultSet.getString("publication");
                String rented = resultSet.getString("rented");
                if(rented.equals("1")){
                    rented = "Rented";
                } else {
                    rented = "In Stock";
                }
                System.out.println(musicID + ": " + track + " | " + genre + " | " + artist + " | " + publication + " | " + rented);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void purchaseItem(String refColumn, String refID) {
        
    }

    public void toRent() { //Show all available items
        String availabilityCheckSQL = "SELECT * FROM music WHERE rented = 1";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(availabilityCheckSQL)) {

            while (resultSet.next()) {
                String musicID = resultSet.getString("id");
                String track = resultSet.getString("track");
                String genre = resultSet.getString("genre");
                String artist = resultSet.getString("artist");
                String publication = resultSet.getString("publication");
                String rented = resultSet.getString("rented");
                if(rented.equals("1")) {
                    rented = "Rented";
                } else {
                    rented = "In Stock";
                }
                System.out.println(musicID + ": " + track + " | " + genre + " | " + artist + " | " + publication + " | " + rented);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAll() { //Show all available items
        String availabilityCheckSQL = "SELECT * FROM music";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(availabilityCheckSQL)) {

            while (resultSet.next()) {
                String musicID = resultSet.getString("id");
                String track = resultSet.getString("track");
                String genre = resultSet.getString("genre");
                String artist = resultSet.getString("artist");
                String publication = resultSet.getString("publication");
                String rented = resultSet.getString("rented");
                if(rented.equals("1")) {
                    rented = "Rented";
                } else {
                    rented = "In Stock";
                }
                System.out.println(musicID + ": " + track + " | " + genre + " | " + artist + " | " + publication + " | " + rented);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /*@Override
    public void purchaseItem(String refColumn, String refID) {

    }*/

    public String getMusicColumns() {
        return musicColumns;
    }

    public void setMusicColumns(String musicColumns) {
        this.musicColumns = musicColumns;
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


