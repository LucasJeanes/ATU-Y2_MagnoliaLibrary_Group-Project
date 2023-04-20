package ie.atu.dbTables;

import ie.atu.dbClasses.dbUpdate;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dbBooks {
    public String bookColumns = "name,author,publication,rented";
    private String name;
    private String author;
    private String publication;
    private boolean rented;

    public dbBooks() {
    }

    public dbBooks(String name, String author, String publication, boolean rented) {
        this.name = name;
        this.author = author;
        this.publication = publication;
        this.rented = rented;
    }
    public void editBook(String columnToChange, String newInfo, String refColumn, String refID) throws SQLException {
        String updateSQL = "UPDATE Books SET " + columnToChange + " = " + newInfo + " WHERE " + refColumn + " = " + refID;
        PreparedStatement updateStatement = connection.prepareStatement(updateSQL);

        int rowsUpdated = updateStatement.executeUpdate();
        System.out.println("Rows updated: " + rowsUpdated);
    }

    public void addBook(String name, String author, String publication, boolean rented) {

    }
    public void deleteBook() {
        //DELETE BOOK FROM TABLE
    }
    public void checkout(String columnToChange,String newInfo,String refColumn,String refID) { //checkout book for rent

        String updateSQL = "UPDATE Books SET " + columnToChange + " = " + newInfo + " WHERE " + refColumn + " = " + refID;
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
