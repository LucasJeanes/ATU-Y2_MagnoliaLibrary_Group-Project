package ie.atu.JavaClient;

import ie.atu.dbTables.dbBooks;
import ie.atu.jdbc.dbConnection;

import java.sql.Connection;
import java.util.Scanner;

public class MagnoliaApp {
    public static void main(String[] args) {
        int libraryMenu = 0;
        Connection connection = null;

        dbConnection dbConnection = new dbConnection();
        Scanner scanner = new Scanner(System.in);
        try {
            connection = dbConnection.connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Welcome to the Magnolia Library Terminal.");
        System.out.println("Please select from the following options: ");
        System.out.println("---[MENU]---\n1. Books\n2. Stationary\n3. Music\n4. Computers\n\nEnter[1 , 2 , 3 , 4]");

        String userInput = scanner.next();
        while(!userInput.equals("q")) {
            switch (userInput) {
                case "1":
                    System.out.println("You have selected: Books");
                    libraryMenu = 1;
                    break;
                case "2":
                    libraryMenu = 2;
                    System.out.println("You have selected: Stationary");
                    break;
                case "3":
                    libraryMenu = 3;
                    System.out.println("You have selected: Music");
                    break;
                case "4":
                    libraryMenu = 4;
                    System.out.println("You have selected: Computers");
                default:
                    System.out.println("Invalid Entry. Please select from the following options: ");
                    System.out.println("---[MAIN MENU]---\n1. Books\n2. Stationary\n3. Music\n4. Computers\n\nEnter[1 , 2 , 3 , 4]");
                    break;
            }
            //BOOK MENU
            if(libraryMenu == 1) {
                System.out.println("\nPlease select from the following options: ");
                System.out.println("---[BOOKS]---\n1. Add new book to database\n2. Edit existing books\n3. Checkout book\n4. Check available books\n5. Delete book\n\nEnter[1 , 2 , 3 , 4 , 5]");
                String bookInput = scanner.next();
                switch(bookInput) {
                    case "1": //Add new book
                        System.out.println("To add a new book to the database, please input the following: ");
                        System.out.print("Book name: ");
                        String name = scanner.next();
                        System.out.print("\nAuthor: ");
                        String author = scanner.next();
                        System.out.print("\nPublication date: ");
                        String publication = scanner.next();
                        dbBooks newBook = new dbBooks(connection, name, author, publication, false);
                        newBook.addItem();
                        break;
                    case "2": //Edit existing data
                        System.out.println("To edit an existing book in the database, please input the following: ");
                        System.out.print("Input either name of the book or book ID you want to edit: ");
                        String refID = scanner.next();
                        int stringSize = refID.length();
                        String refColumn;
                        if(stringSize == 2) {
                            refColumn = "id";
                        } else {
                            refColumn = "name";
                        }
                        System.out.print("\nInformation to edit(name, author, publication): ");
                        String columnToChange = scanner.next();
                        System.out.print("\nInput the updated information: ");
                        String newInfo = scanner.next();
                        dbBooks updateBook = new dbBooks(connection);
                        updateBook.editItem(columnToChange,newInfo,refColumn, refID);
                        break;
                    case "3": //Checkout book

                        break;
                    case "4": //Check available books
                        System.out.println("1");
                        break;
                    case "5": //Delete book from database
                        break;
                    default:
                        break;
                }
            }
            //STATIONARY MENU
            if(libraryMenu == 2) {
                System.out.println("\nPlease select from the following options: ");
                System.out.println("---[STATIONARY]---\n1. Add new stationary to database\n2. Edit existing items\n3. Buy item\n4. Check available items\n\nEnter[1 , 2 , 3 , 4]");
                userInput = scanner.next();
            }
            //MUSIC MENU
            if(libraryMenu == 3) {
                System.out.println("\nPlease select from the following options: ");
                System.out.println("---[MUSIC]---\n1. Add new music to database\n2. Edit existing items\n3. Checkout item\n4. Check available music\n\nEnter[1 , 2 , 3 , 4]");
                userInput = scanner.next();
            }
            //COMPUTER MENU
            if(libraryMenu == 4) {
                System.out.println("\nPlease select from the following options: ");
                System.out.println("---[COMPUTERS]---\n1. Add new computer to database\n2. Edit existing items\n3. Book a computer\n4. Check available computers\n\nEnter[1 , 2 , 3 , 4]");
                userInput = scanner.next();
            }
        }
    }
}
