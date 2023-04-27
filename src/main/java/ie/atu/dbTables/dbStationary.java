package ie.atu.dbTables;

import java.sql.*;

public class dbStationary implements dbMethods{


    private String name;
    private String description;
    private int price;
    private int user_discount;
    private int stock;
    private Connection connection;

    public dbStationary(Connection connection) {
        this.connection = connection;
    }

    public dbStationary(Connection connection, String name, String description, int price, int user_discount, int stock) {

        this.name = name;
        this.description = description;
        this.price = price;
        this.user_discount = user_discount;
        this.stock = stock;
        this.connection = connection;
    }


    @Override
    public void deleteItem(String refColumn,String refID) {
        String deleteStationary = "DELETE FROM stationary WHERE " +  refColumn + " = " + refID;
        try {
            PreparedStatement deleteStatement = connection.prepareStatement (deleteStationary);
          /*  deleteStatement.setString(1, name);
            deleteStatement.setString(2, description);
            deleteStatement.setInt(3, price);
            deleteStatement.setInt(4, user_discount);
            deleteStatement.setInt(5, stock);*/
            int rowsDeleted = (deleteStatement.executeUpdate(deleteStatement.toString()));
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            System.out.println("Rows failed delete");
            e.printStackTrace();
        }
    }

    @Override
    public void addItem(){
        String addStationary = "INSERT INTO stationary VALUES (?, ?, ?, ?, ?)";
        try {
            // Insert a new record into the "users" table
            PreparedStatement insertStatement = connection.prepareStatement(addStationary);
            //insertStatement.setString(1,insertTable);
            insertStatement.setString(1,name);
            insertStatement.setString(2,description);
            insertStatement.setInt(3,price);
            insertStatement.setInt(4,user_discount);
            insertStatement.setInt(5,stock);
            //int row = insertStatement.executeUpdate();

            int inserted = (insertStatement.executeUpdate());
            System.out.println("The following has successfully been inserted: " + inserted);
        } catch (SQLException ex) {

            System.out.println("Record insert failed.");
            ex.printStackTrace();
        }
    }

    @Override
    public void editItem(String columnToChange, String newInfo, String refColumn, String refID) {
        try
        {
            String updateSQL = "UPDATE Stationary SET " + columnToChange + " = " +  newInfo + " WHERE " + refColumn +" = " + refID;
            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);

            //updateStatement.setString(1,TableName);

            //updateStatement.setString(1,SetColumnName);
            // updateStatement.setString(1,NewName);
            //updateStatement.setInt(2,IDNumber);

            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

            connection.close();
            updateStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


   // public String getStationaryColumns() {
   //     return stationaryColumns;
   // }

   // public void setStationaryColumns(String stationaryColumns) {
   //     this.stationaryColumns = stationaryColumns;
   // }

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
