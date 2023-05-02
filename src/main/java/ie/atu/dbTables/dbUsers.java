package ie.atu.dbTables;

import java.sql.*;

public class dbUsers implements dbMethods {
    private String name;
    private String address;
    private String phone_num;
    private int age;
    private String password;
    private Connection connection;

    public dbUsers() {
    }

    public dbUsers(Connection connection) {
        this.connection = connection;
    }

    public dbUsers(Connection connection, String name, String address, String phone_num, int age, String password) {
        this.name = name;
        this.address = address;
        this.phone_num = phone_num;
        this.age = age;
        this.password = password;
        this.connection = connection;
    }

    @Override
    public void addItem() {
        String selectSQL = "INSERT INTO users (name, address, phone_number, age, password) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement insertStatement = connection.prepareStatement(selectSQL);
            insertStatement.setString(1,name);
            insertStatement.setString(2,address);
            insertStatement.setString(3,phone_num);
            insertStatement.setInt(4,age);
            insertStatement.setString(5, password);

            int inserted = (insertStatement.executeUpdate());
            System.out.println("New user has been added: " + inserted);
            insertStatement.close();
        } catch (SQLException ex) {
            System.out.println("[ERROR] Adding user to database failed.");
            ex.printStackTrace();
        }
    }

    @Override
    public void editItem(String columnToChange, String newInfo, String refColumn, String refID) {
        String updateSQL = "UPDATE users SET " + columnToChange + " = \"" + newInfo + "\" WHERE " + refColumn + " = \"" + refID + "\"";

        try {
            PreparedStatement updateStatement = connection.prepareStatement(updateSQL);

            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
            updateStatement.close();
        } catch (SQLException ex) {
            System.out.println("[ERROR] Edit user info failed.");
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteItem(String refColumn, String refID) {
        String deleteSQL = "DELETE FROM users WHERE " + refColumn + " = " + "\"" + refID + "\"";

        try {
            PreparedStatement deleteStatement = connection.prepareStatement(deleteSQL);
            int rowsDeleted = (deleteStatement.executeUpdate());
            System.out.println("Users deleted: " + rowsDeleted);
            deleteStatement.close();
        } catch (SQLException e) {
            System.out.println("[ERROR] Deleting user failed.");
            e.printStackTrace();
        }
    }
    public void checkRented(int userID) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id, name, rented, user_id FROM book WHERE user_id = " + userID)) {

            while (resultSet.next()) {
                String book_id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String rented = resultSet.getString("rented");
                if(rented.equals("1")) {
                    rented = "Rented";
                } else {
                    rented = "In Stock";
                }
                String user_id = resultSet.getString("user_id");
                System.out.println(book_id + ": " + name + " | " + rented + " | " + user_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void checkout(String refColumn, String refID, int userID) {

    }

    public void isAvailable(String refColumn, String refID) {
    }

    public void isAvailable() {
    }

    public void purchaseItem(String refColumn, String refID) {
    }

    public void toRent() {
    }
}
