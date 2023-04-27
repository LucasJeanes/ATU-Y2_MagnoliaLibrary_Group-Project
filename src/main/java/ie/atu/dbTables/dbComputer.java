package ie.atu.dbTables;

import com.microsoft.sqlserver.jdbc.ISQLServerConnection;

import java.sql.*;

public class dbComputer implements dbMethods{
    public String ComputerColoumn = "name, brand, details, memory, price, rented";
    private String name;
    private String brand;
    private String details;
    private String memory;
    private String price;
    private boolean rented;
    private Connection connection;

    public dbComputer(Connection connection) {
        this.connection = connection;
    }

    public dbComputer(Connection connection, String name, String brand, String details, String memory, String price, boolean rented) {

        this.name = name;
        this.brand = brand;
        this.details = details;
        this.memory = memory;
        this.price = price;
        this.rented = rented;
    }

    /////Inserting new Value
    @Override
    public void addItem(){

        String addComp = "INSERT INTO Computer VALUES (?,?,?,?,?,?)";

        try {
            ////String selectSQL = "INSERT INTO ? VALUES (?, ?, ?, ?)";
            // Insert a new record into the "users" table
            PreparedStatement insertStatement = connection.prepareStatement(addComp);
            //insertStatement.setString(1,insertTable);
            insertStatement.setString(1,name);
            insertStatement.setString(2,brand);
            insertStatement.setString(3,details);
            insertStatement.setString(4,memory);
            insertStatement.setString(5,price);
            insertStatement.setString(6, String.valueOf(rented));
            //int row = insertStatement.executeUpdate();

            int inserted = (insertStatement.executeUpdate());
            System.out.println("The following has successfully been inserted: " + inserted);
        } catch (SQLException ex) {

            System.out.println("Record insert failed.");
            ex.printStackTrace();
        }

    }
    ///////Deleting columns/rows
    @Override
    public void deleteItem(String refColumn,String refID){
        String deleteComp = "DELETE FROM Computer WHERE " + refColumn + " = " + refID;

        try{
            PreparedStatement deleteStatement = connection.prepareStatement (deleteComp);
            //deleteStatement.setString(1,deleteTable);
            //deleteStatement.setString(2,deleteColumn);

            //deleteStatement.setString(3,refID);

            int rowsDeleted = (deleteStatement.executeUpdate(deleteStatement.toString()));
            System.out.println("Computer removed: " + rowsDeleted);
        } catch (SQLException e){
            System.out.println("Rows failed delete");
            e.printStackTrace();
        }

    }
    @Override
    /////////Updating tables/////
    public void editItem(String columnToChange, String newInfo, String refColumn, String refID){
        String updateComp = "UPDATE Computer SET " + columnToChange + " = " + newInfo + " WHERE " + refColumn + " = " + refID;

        try
        {
            //String updateSQL = "UPDATE Books SET name = ? WHERE " + refColumn + " = ?";
            //ISQLServerConnection connection = null;
            PreparedStatement updateStatement = connection.prepareStatement(updateComp);

            //updateStatement.setString(1,TableName);

            //updateStatement.setString(1,SetColumnName);
           // updateStatement.setString(1,NewName);
            //updateStatement.setInt(2,IDNumber);

            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);



        } catch (SQLException e) {
            System.out.println("Rows update failed");
            e.printStackTrace();
        }



    }
    @Override
    public void isAvailable(String refColumn,String refID) {
        String availabilityCheckSQL = "SELECT * FROM Computer WHERE (rented = 0 AND " + refColumn + " = " + refID + ")";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(availabilityCheckSQL)) {

            while (resultSet.next()) {
                String compID = resultSet.getString("id");
                String name = resultSet.getString("name");
                String brand = resultSet.getString("brand");
                String details = resultSet.getString("details");
                String price = resultSet.getString("price");
                System.out.println(compID + ": " + name + " | " + brand + " | " + details + " | " + price + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void isAvailable() {
        String availabilityCheckSQL = "SELECT * FROM Computer WHERE rented = 0";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(availabilityCheckSQL)) {

            while (resultSet.next()) {
                String compID = resultSet.getString("id");
                String name = resultSet.getString("name");
                String brand = resultSet.getString("brand");
                String details = resultSet.getString("details");
                String price = resultSet.getString("price");
                System.out.println(compID + ": " + name + " | " + brand + " | " + details + " | " + price + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



//Getters and setters
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

