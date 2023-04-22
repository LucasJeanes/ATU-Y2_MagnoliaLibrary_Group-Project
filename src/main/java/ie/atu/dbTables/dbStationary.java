package ie.atu.dbTables;

import java.sql.*;

public class dbStationary {
    public static String deleteTable = "Boo";
    public static String deleteColumn = "Boo";
    public static String refID = "Boo";
    public static String refValue = "Boo";

    String insertTable = "Books";
    String insertName = "BookTest";
    String insertAuthor = "AuthorName";
    String refPublic = "1996";
    String refRented = "0";

    String TableName = "Books";
    String SetColumnName = "name";
    String NewName = "DewansBookLASTONEPLS";
    String refColumn = "book_id";
    int IDNumber = 16;

    public String stationaryColumns = "name,description,price,user_discount,stock";
    private String name;
    private String description;
    private int price;
    private int user_discount;
    private int stock;
    private Connection connection;

    public dbStationary(Connection connection) {
        this.connection = connection;
    }

    public dbStationary(String stationaryColumns, String name, String description, int price, int user_discount, int stock) {
        this.stationaryColumns = stationaryColumns;
        this.name = name;
        this.description = description;
        this.price = price;
        this.user_discount = user_discount;
        this.stock = stock;

    }

    public dbStationary() {

    }

    public void updateItem(String name, String description, int price, int user_discount, int stock) {
        try
        {
            String updateSQL = "UPDATE Books SET name = ? WHERE " + refColumn + " = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);

            //updateStatement.setString(1,TableName);

            //updateStatement.setString(1,SetColumnName);
            updateStatement.setString(1,NewName);
            updateStatement.setInt(2,IDNumber);

            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

            connection.close();
            updateStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem () {
        try {
            PreparedStatement deleteStatement = connection.prepareStatement ("DELETE FROM ? WHERE ? = ?");
            deleteStatement.setString(1,deleteTable);
            deleteStatement.setString(2,deleteColumn);
            deleteStatement.setString(3,refID);
            deleteStatement.setString(4,refValue);
            int rowsDeleted = (deleteStatement.executeUpdate(deleteStatement.toString()));
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            System.out.println("Rows failed delete");
            e.printStackTrace();
        }
    }

    public void addItem(){
        try {
            // Insert a new record into the "users" table
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO ? VALUES (?, ?, ?, ?)");
            //insertStatement.setString(1,insertTable);
            insertStatement.setString(1,insertName);
            insertStatement.setString(2,insertAuthor);
            insertStatement.setString(3,refPublic);
            insertStatement.setString(4,refRented);
            //int row = insertStatement.executeUpdate();

            int inserted = (insertStatement.executeUpdate());
            System.out.println("The following has successfully been inserted: " + inserted);
        } catch (SQLException ex) {

            System.out.println("Record insert failed.");
            ex.printStackTrace();
        }
    }


    public String getStationaryColumns() {
        return stationaryColumns;
    }

    public void setStationaryColumns(String stationaryColumns) {
        this.stationaryColumns = stationaryColumns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUser_discount() {
        return user_discount;
    }

    public void setUser_discount(int user_discount) {
        this.user_discount = user_discount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
