package ie.atu.dbTables;

import java.sql.*;

public class dbBooks implements dbMethods{
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
    @Override
    public void editItem(String columnToChange, String newInfo, String refColumn, String refID) {
        String updateSQL = "UPDATE Book SET " + columnToChange + " = " + newInfo + " WHERE " + refColumn + " = " + refID;

        try {
            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);

            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
            updateStatement.close();
        } catch (SQLException ex) {
            System.out.println("[ERROR] Edit book info failed.");
            ex.printStackTrace();
        }
    }
    @Override
    public void addItem() {
        String selectSQL = "INSERT INTO book (name, author, publication, rented) VALUES (?, ?, ?, ?)";

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
    @Override
    public void deleteItem(String refColumn,String refID) {
        String deleteSQL = "DELETE FROM book WHERE " + refColumn + " = " + refID;

        try {
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL);
            //deleteStatement.setString(1, refColumn);
            //deleteStatement.setString(2, refID);
            int rowsDeleted = (deleteStatement.executeUpdate());
            System.out.println("Books deleted: " + rowsDeleted);
            deleteStatement.close();
        } catch (SQLException e) {
            System.out.println("[ERROR] Deleting book failed.");
            e.printStackTrace();
        }
    }
    public void checkout(String refColumn,String refID) { //checkout book for rent
        //String rentColumn = "rented";
        //String checkedOut = "1";
        String updateSQL = "UPDATE book SET rented = 1 WHERE " + refColumn + " = " + refID;

        try {
            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);
            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
            updateStatement.close();
        } catch(SQLException ex) {
            System.out.println("[ERROR] Book checkout failed.");
            ex.printStackTrace();
        }
    }
    @Override
    public void isAvailable(String refColumn,String refID) {    //If there is a specific item user wants to check
        String availabilityCheckSQL = "SELECT * FROM book WHERE (rented = 0 AND " + refColumn + " = " + refID + ")";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(availabilityCheckSQL)) {

            while (resultSet.next()) {
                String bookID = resultSet.getString("book_id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                String publication = resultSet.getString("publication");
                System.out.println(bookID + ": " + name + " | " + author + " | " + publication + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void isAvailable() { //Show all available items
        String availabilityCheckSQL = "SELECT * FROM book WHERE rented = 0";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(availabilityCheckSQL)) {

            while (resultSet.next()) {
                String bookID = resultSet.getString("book_id");
                String name = resultSet.getString("name");
                String author = resultSet.getString("author");
                String publication = resultSet.getString("publication");
                System.out.println(bookID + ": " + name + " | " + author + " | " + publication + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
