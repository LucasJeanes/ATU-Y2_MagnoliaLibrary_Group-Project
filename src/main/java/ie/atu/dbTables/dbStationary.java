package ie.atu.dbTables;

import java.sql.*;

public class dbStationary implements dbMethods {


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

    public dbStationary() {
    }

    public dbStationary(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void deleteItem(String refColumn, String refID) {
        String deleteSQL = "DELETE FROM stationary WHERE " + refColumn + " = " + refID;
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
    public void addItem() {
        String selectSQL = "INSERT INTO stationary (name,description,price,user_discount,stock) VALUES (?, ?, ?, ?, ?)";
        try {
            // Insert a new record into the "users" table

            PreparedStatement insertStatement = connection.prepareStatement(selectSQL);

            insertStatement.setString(1, name);
            insertStatement.setString(2, description);
            insertStatement.setInt(3, price);
            insertStatement.setInt(4, user_discount);
            insertStatement.setInt(5, stock);


            int inserted = (insertStatement.executeUpdate());
            System.out.println("The following has successfully been inserted: " + inserted);
        } catch (SQLException e) {

            System.out.println("Record insert failed.");
            e.printStackTrace();
        }
    }

    @Override
    public void editItem(String columnToChange, String newInfo, String refColumn, String refID) {
        try {
            String updateSQL = "UPDATE stationary SET " + columnToChange + " = \"" + newInfo + "\" WHERE " + refColumn + " = \"" + refID + "\"";
            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);
            // updateStatement.setString(1,name);

            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);

            connection.close();
            updateStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void isAvailable(String refColumn, String refID) {
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




    public void purchaseItem(String refColumn, String refID, int pnum) {
        //int newStock = 0;
        try {

            String purchaseSQL = "UPDATE Stationary SET stock =  ? WHERE " + refColumn + " = ?";
            PreparedStatement updateStatement = connection.prepareStatement(purchaseSQL);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT stock FROM Stationary WHERE " + refColumn + " = " + refID)) {

                if (resultSet.next()) {
                    int currentStock = resultSet.getInt("stock");
                    int newStock = currentStock - pnum;
                    assertEquals("stock", String.valueOf(currentStock));
                    updateStatement.setInt(1, newStock);
                    updateStatement.setString(2, refID);
                    // updateStatement.setString(2, refID);
                    int rowsUpdated = updateStatement.executeUpdate();
                   // int newStock = updateStatement.executeUpdate();
                    System.out.println("Rows updated: " + rowsUpdated);
                    System.out.println("New stock level: " + newStock);
                } else {
                    System.out.println("No items found with ID " + refID);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            connection.close();
            updateStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void assertEquals(String testName, String name) {
    }

    @Override
    public void checkout(String refColumn, String refID) {

    }


    public void StatshowAll() { //Show all available items
        String availabilityCheckSQL = "SELECT * FROM stationary";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(availabilityCheckSQL)) {

            while (resultSet.next()) {
                String statID = resultSet.getString("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String price = resultSet.getString("price");
                int stock = resultSet.getInt("stock");
                String stockStat = "";

                if(stock != 0) {
                    stockStat= ("in Stock");
                } else {
                    stockStat= ("out of Stock");
                }
                System.out.println(statID + ": " + name + " | " + description + " | " + price + " | " + stockStat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   @Override
    public void isStatAvailable() { //Show all available items
        String availabilityCheckSQL = "SELECT * FROM Stationary WHERE stock <> 0";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(availabilityCheckSQL)) {

            while (resultSet.next()) {
                String statID = resultSet.getString("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String price = resultSet.getString("price");
                String user_discount = resultSet.getString("user_discount");
                int stock = resultSet.getInt("stock");
                String stockStat= "";
                if(stock != 0) {
                    stockStat = "In Stock";
                } else {
                    stockStat = "Out of Stock";
                }
                System.out.println(statID + ": " + name + " | " + description + " | " + price + " | " + user_discount +" | " + stockStat);
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
