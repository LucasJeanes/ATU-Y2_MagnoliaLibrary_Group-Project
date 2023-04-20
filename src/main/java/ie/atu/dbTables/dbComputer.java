package ie.atu.dbTables;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class dbComputer {
    public String ComputerColoumn = "name, brand, details, memory, price, rented";
    public String name;
    public String brand;
    public String details;
    public String memory;
    public String price;
    private boolean rented;

    public dbComputer(String name, String brand, String details, String memory, String price, boolean rented) {

        this.name = name;
        this.brand = brand;
        this.details = details;
        this.memory = memory;
        this.price = price;
        this.rented = rented;
    }

    /////Inserting new Value

    public void addComputer(String name, String brand, String details, String memory, String price, boolean rented){
        String insertTable = "Computer";
        String insertName = "HPMeg9999";
        String insertBrand = "HP";
        String insertDetails = "intel Core i9";
        String insertMemory = "16Gig / 1TB";
        String insertPrice = "1599";
        String refRented = "0";
        ///String selectSQL = "INSERT INTO ? VALUES (?, ?, ?, ?)";

        try {

            // Insert a new record into the "users" table
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO ? VALUES (?, ?, ?, ?)";);
            //insertStatement.setString(1,insertTable);
            insertStatement.setString(1,insertName);
            insertStatement.setString(2,insertBrand);
            insertStatement.setString(3,insertDetails);
            insertStatement.setString(4,insertMemory);
            insertStatement.setString(5,insertPrice);
            insertStatement.setString(6,refRented);
            //int row = insertStatement.executeUpdate();

            int inserted = (insertStatement.executeUpdate());
            System.out.println("The following has successfully been inserted: " + inserted);
        } catch (SQLException ex) {

            System.out.println("Record insert failed.");
            ex.printStackTrace();
        }

    }
    ///////Deleting columns/rows

    public void deleteComputer(){
        String deleteTable = "Boo";
        String deleteColumn = "Boo2";
        String refID = "19";

        try{
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
    /////////Updating tables/////
    public String UpdateComputer(String name, String brand, String details, String memory, String price, boolean rented){
        String TableName = "Books";
        String SetColumnName = "name";
        String NewName = "DewansBookLASTONEPLS";
        String refColumn = "book_id";
        int IDNumber = 16;

        try
        {
            //////String updateSQL = "UPDATE Books SET name = ? WHERE " + refColumn + " = ?";
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE Books SET name = ? WHERE " + refColumn + " = ?");

            //updateStatement.setString(1,TableName);

            //updateStatement.setString(1,SetColumnName);
            updateStatement.setString(1,NewName);
            updateStatement.setInt(2,IDNumber);

            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);


            updateStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
    public void availabilityCheck(){

    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }
}

