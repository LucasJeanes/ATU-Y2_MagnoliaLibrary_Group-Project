package ie.atu.dbTables;

import java.sql.*;

public class dbStationary implements dbMethods{


    private String name;
    private String description;
    private int price;
    private int user_discount;
    private int stock;
    private Connection connection;


    public dbStationary(Connection connection, String name, String description, int price, int user_discount, int stock) {
        this.connection = connection;
        this.name = name;
        this.description = description;
        this.price = price;
        this.user_discount = user_discount;
        this.stock = stock;

    }

    public dbStationary(Connection connection) {
    }

    @Override
    public void deleteItem(String refColumn,String refID) {
        String deleteSQL ="DELETE FROM stationary WHERE " + refColumn + " = " + refID;
        try {
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL);

            int rowsDeleted = (deleteStatement.executeUpdate());
            System.out.println("Rows deleted: " + rowsDeleted);
        } catch (SQLException e) {
            System.out.println("Rows failed delete");
            e.printStackTrace();
        }
    }

    @Override
    public void addItem(){
        String selectSQL = "INSERT INTO stationary (name,description,price,user_discount,stock) VALUES (?, ?, ?, ?, ?)";
        try {
            // Insert a new record into the "users" table

            PreparedStatement insertStatement = connection.prepareStatement(selectSQL);

            insertStatement.setString(1,name);
            insertStatement.setString(2,description);
            insertStatement.setInt(3,price);
            insertStatement.setInt(4,user_discount);
            insertStatement.setInt(5,stock);


            int inserted = (insertStatement.executeUpdate());
            System.out.println("The following has successfully been inserted: " + inserted);
        } catch (SQLException e) {

            System.out.println("Record insert failed.");
            e.printStackTrace();
        }
    }

    @Override
    public void editItem(String columnToChange, String newInfo, String refColumn, String refID) {
        String updateSQL = "UPDATE stationary SET name = ? WHERE " + name + " = ?";
        try
        {

            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);
            updateStatement.setString(1,name);

            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

            connection.close();
            updateStatement.close();
        } catch (SQLException e) {
            System.out.println("Record insert failed.");
            e.printStackTrace();
        }
    }
    public void purchaseItem(String refColumn,String refID){
        String purchaseSQL = "UPDATE stationary SET stock = stock - 1 WHERE"+ name + "= ?";
        try{
        PreparedStatement updateStatement = connection.prepareStatement(purchaseSQL);
        updateStatement.setString(1,name);

        int rowsUpdated = updateStatement.executeUpdate();
        System.out.println("Rows updated: " + rowsUpdated);

        connection.close();
        updateStatement.close();
    } catch (SQLException e) {
            System.out.println("Record insert failed.");
        e.printStackTrace();
    }
    }

    @Override
    public void checkout(String refColumn, String refID) {

    }

    @Override
    public void toRent() {

    }

    @Override
    public void isAvailable(String refColumn,String refID) {
        String availabilityCheckSQL = "SELECT * FROM Stationary WHERE (stock = 0 AND " + refColumn + " = " + refID + ")";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(availabilityCheckSQL)) {

            while (resultSet.next()) {
                String stationaryID = resultSet.getString("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String price = resultSet.getString("price");
                String stock = resultSet.getString("stock");
                System.out.println(stationaryID + ": " + name + " | " + description + " | " + price + " | " + stock + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  /*  @Override
    public void checkout(String refColumn, String refID) {

    }*/

    @Override
    public void isAvailable() {
        String availabilityCheckSQL = "SELECT * FROM Stationary WHERE stock = 0";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(availabilityCheckSQL)) {

            while (resultSet.next()) {
                String stationaryID = resultSet.getString("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String price = resultSet.getString("price");
                String stock = resultSet.getString("stock");
                System.out.println(stationaryID + ": " + name + " | " + description + " | " + price + " | " + stock + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
