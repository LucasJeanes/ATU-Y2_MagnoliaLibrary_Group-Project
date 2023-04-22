package ie.atu.dbTables;

import ie.atu.dbClasses.dbUpdate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dbBooks {
    public String bookColumns = "name,author,publication,rented";
    private String name;
    private String author;
    private String publication;
    private boolean rented;
    private Connection connection;

    public dbBooks() {
    }
    public dbBooks(Connection connection) {

    }
    public dbBooks(Connection connection, String name, String author, String publication, boolean rented) {
        this.connection = connection;
        this.name = name;
        this.author = author;
        this.publication = publication;
        this.rented = rented;
    }
    public void editBook(String columnToChange, String newInfo, String refColumn, String refID) {
        String updateSQL = "UPDATE Books SET " + columnToChange + " = " + newInfo + " WHERE " + refColumn + " = " + refID;

        try {
            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);

            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
        } catch (SQLException ex) {
            System.out.println("[ERROR] Edit book info failed.");
            ex.printStackTrace();
        }
    }

    public void addBook() {
        String selectSQL = "INSERT INTO Books VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement insertStatement = connection.prepareStatement(selectSQL);
            insertStatement.setString(1,name);
            insertStatement.setString(2,author);
            insertStatement.setString(3,publication);
            insertStatement.setBoolean(4,rented);

            int inserted = (insertStatement.executeUpdate());
            System.out.println("New book has been added: " + inserted);
            insertStatement.close();
        } catch (SQLException ex) {

            System.out.println("[ERROR] Adding book to database failed.");
            ex.printStackTrace();
        }
    }
    public void deleteBook(String refColumn,String refID) {
        String deleteSQL = "DELETE FROM Books WHERE " + refColumn + " = " + refID;

        try {
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL);
            //deleteStatement.setString(1,refColumn);
           // deleteStatement.setString(2,refID);
            int rowsDeleted = (deleteStatement.executeUpdate());
            System.out.println("Books deleted: " + rowsDeleted);

        } catch (SQLException e) {
            System.out.println("[ERROR] Deleting book failed.");
            e.printStackTrace();
        }
    }
    public void checkout(String refColumn,String refID) { //checkout book for rent
        //String rentColumn = "rented";
        //String checkedOut = "1";
        String updateSQL = "UPDATE Books SET rented = 1 WHERE " + refColumn + " = " + refID;

        try {
            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);
            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

        } catch(SQLException ex) {
            System.out.println("[ERROR] Book checkout failed.");
            ex.printStackTrace();
        }
    }

    //GETTERS & SETTERS
    public String getBookColumns() {return bookColumns;}
    public void setBookColumns(String bookColumns) {this.bookColumns = bookColumns;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}
    public String getPublication() {return publication;}
    public void setPublication(String publication) {this.publication = publication;}
    public boolean isRented() {return rented;}
    public void setRented(boolean rented) {this.rented = rented;}
}
