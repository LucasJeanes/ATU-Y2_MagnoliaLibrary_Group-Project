package ie.atu.JavaClient;

import ie.atu.jdbc.dbConnection;

import java.sql.Connection;
import java.util.Scanner;

public class MagnoliaApp {
    public static void main(String[] args) {
        Connection connection;

        dbConnection dbConnection = new dbConnection();
        Scanner scanner = new Scanner(System.in);
        /*
        try {
            connection = dbConnection.connection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        System.out.println("Welcome to the Magnolia Library Terminal.\n\nPlease select from the following options: \nEnter[1 , 2 , 3]");
        System.out.println("1. Login\n2. Create Account\n3. Sign in as Guest");

        int userInput = scanner.nextInt();

        switch (userInput) {
            case 1:
                System.out.println("You have selected: Login");
                break;
            case 2:
                System.out.println("You have selected: Create Account");
                break;
            case 3:
                System.out.println("You have selected: Sign in as Guest");
                break;
            default:
                System.out.println("Invalid Entry. Please Select from the following options: \nEnter[1 , 2 , 3]");
                System.out.println("1. Login\n2. Create Account\n3. Sign in as Guest");
        }

    }
}
