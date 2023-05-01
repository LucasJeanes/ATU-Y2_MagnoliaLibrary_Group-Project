package ie.atu.JavaClient;

import ie.atu.dbTables.dbBooks;
import ie.atu.dbTables.dbMusic;
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

        String userInput = "0";
        while(!userInput.equals("q")) {
            System.out.println("\n\nWelcome to the Magnolia Library Terminal.");
            System.out.println("Please select from the following options: ");
            System.out.println("---[MENU]---\n1. Books\n2. Stationary\n3. Music\n4. Computers\n\nEnter[1 , 2 , 3 , 4]");
            userInput = scanner.next();

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
                String refID;
                String refColumn;
                int stringSize;
                System.out.println("\nPlease select from the following options: ");
                System.out.println("---[BOOKS]---\n1. Add new book to database\n2. Edit existing books\n3. Checkout book\n4. Check available books\n5. Delete book\n6. Back to selection\n\nEnter[1 , 2 , 3 , 4 , 5]");
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
                        refID = scanner.next();
                        stringSize = refID.length();
                        if(stringSize <= 2) {
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
                        System.out.print("Input the name or ID no. of the book to check out: ");
                        refID = scanner.next();
                        stringSize = refID.length();
                        if(stringSize <= 2) {
                            refColumn = "id";
                        } else {
                            refColumn = "name";
                        }
                        dbBooks checkoutBook = new dbBooks(connection);
                        checkoutBook.checkout(refColumn,refID);
                        break;
                    case "4": //Check available books
                        System.out.println("\nPlease select from the following: ");
                        System.out.println("1. All books in database\n2. Specific book search");
                        dbBooks availableBooks = new dbBooks(connection);
                        String rentedInput = scanner.next();
                        switch (rentedInput) {
                            case "1":
                                System.out.println("Please select from the following: ");
                                System.out.println("1. Show available books\n2. Show rented books\n3. Show All");
                                rentedInput = scanner.next();
                                if(rentedInput.equals("1")) {
                                    availableBooks.isAvailable();
                                } else if(rentedInput.equals("2")) {
                                    availableBooks.isRented();
                                } else {
                                    availableBooks.showAll();
                                }
                                break;
                            case "2":
                                System.out.print("Input the name or ID no. of the book to view: ");
                                refID = scanner.next();
                                stringSize = refID.length();
                                if(stringSize <= 2) {
                                    refColumn = "id";
                                } else {
                                    refColumn = "name";
                                }
                                availableBooks.isAvailable(refColumn,refID);
                                break;
                            default:
                                System.out.println("Invalid Selection.");
                                System.out.println("\nPlease select from the following: ");
                                System.out.println("1. All books in database\n2. Specific book search");
                                break;
                        }
                        break;

                    case "5":   //DELETE BOOK FROM DB
                        System.out.println("Input the name or ID no. of the book to DELETE from database.");
                        System.out.println("NOTE: THIS CANNOT BE UNDONE");
                        dbBooks removeBook = new dbBooks(connection);
                        refID = scanner.next();
                        stringSize = refID.length();
                        if(stringSize <= 2) {
                            refColumn = "id";
                        } else {
                            refColumn = "name";
                        }
                        removeBook.deleteItem(refColumn,refID);
                        break;
                    case "6": //Return to main menu
                        libraryMenu = 0;
                        break;
                    default:
                        System.out.println("\nInvalid entry.\nPlease select from the following options: ");
                        System.out.println("---[BOOKS]---\n1. Add new book to database\n2. Edit existing books\n3. Checkout book\n4. Check available books\n5. Delete book\n\nEnter[1 , 2 , 3 , 4 , 5]");
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
                String refID;
                String refColumn;
                int stringSize;
                System.out.println("\nPlease select from the following options: ");
                System.out.println("---[MUSIC]---\n1. Add new music to database\n2. Edit existing music\n3. Checkout music\n4. Check available music\n5. Delete music\n6. Back to Selection\n\nEnter[1 , 2 , 3 , 4, 5, 6]");
                String musicInput = scanner.next();
                switch (musicInput) {
                    case "1": //Add new Music
                        System.out.println("To add new music to the database, please input the following: ");
                        System.out.print("Track name: ");
                        String track = scanner.next();
                        System.out.print("\nGenre: ");
                        String genre = scanner.next();
                        System.out.print("\nArtist: ");
                        String artist = scanner.next();
                        System.out.print("\nPublication date: ");
                        String publication = scanner.next();
                        dbMusic newMusic = new dbMusic(connection, track, genre, artist, publication, false);
                        newMusic.addItem();
                        break;
                    case "2": //Edit Existing Data
                        System.out.println("To edit an existing Music in the database, please input the following: ");
                        System.out.print("Input either name of the music track or music ID you want to edit: ");
                        refID = scanner.next();
                        stringSize = refID.length();
                        if (stringSize <= 2){
                            refColumn = "id";
                        } else {
                            refColumn = "name";
                        }
                        System.out.print("\nInformation to edit(track name, genre, artist, publication): ");
                        String columnToChange = scanner.next();
                        System.out.print("\nInput the updated information: ");
                        String newInfo = scanner.next();
                        dbMusic updateMusic = new dbMusic(connection);
                        updateMusic.editItem(columnToChange,newInfo,refColumn,refID);
                        break;
                    case "3": //Checkout Music
                        System.out.print("Input the track name or ID no. of the music to check out: ");
                        refID = scanner.next();
                        stringSize = refID.length();
                        if(stringSize <= 2) {
                            refColumn = "id";
                        } else {
                            refColumn = "track";
                        }
                        dbMusic checkoutMusic = new dbMusic(connection);
                        checkoutMusic.checkout(refColumn,refID);
                        break;
                    case "4": //Check Available Music
                        System.out.println("\nPlease select from the following: ");
                        System.out.println("1. All Music Tracks in database\n2. Specific Music search");
                        dbMusic availableMusic = new dbMusic(connection);
                        String rentedInput = scanner.next();
                        switch (rentedInput) {
                            case "1":
                                System.out.println("Please select from the following: ");
                                System.out.println("1. Show available Music\n2. Show rented Music\n3. Show All");
                                rentedInput = scanner.next();
                                if(rentedInput.equals("1")) {
                                    availableMusic.isAvailable();
                                } else if(rentedInput.equals("2")) {
                                    availableMusic.toRent();
                                } else {
                                    availableMusic.showAll();
                                }
                                break;
                            case "2":
                                System.out.print("Input the track name or ID no. of the music to view: ");
                                refID = scanner.next();
                                stringSize = refID.length();
                                if(stringSize <= 2) {
                                    refColumn = "id";
                                } else {
                                    refColumn = "name";
                                }
                                availableMusic.isAvailable(refColumn,refID);
                                break;
                            default:
                                System.out.println("Invalid Selection.");
                                System.out.println("\nPlease select from the following: ");
                                System.out.println("1. All Music in database\n2. Specific Music Track search");
                                break;
                        }
                        break;

                    case "5": //Delete Music from database
                        System.out.println("Input the track name or ID no. of the music to DELETE from database.");
                        System.out.println("NOTE: THIS CANNOT BE UNDONE!");
                        dbMusic removeMusic = new dbMusic(connection);
                        refID = scanner.next();
                        stringSize = refID.length();
                        if(stringSize <= 2) {
                            refColumn = "id";
                        } else {
                            refColumn = "track";
                        }
                        removeMusic.deleteItem(refColumn,refID);
                        break;
                    case "6": //return to main menu
                        libraryMenu = 0;
                        break;
                    default:
                        System.out.println("\nInvalid entry.\nPlease select from the following options: ");
                        System.out.println("---[MUSIC]---\n1. Add new music to database\n2. Edit existing music\n3. Checkout music\n4. Check available musics\n5. Delete music\n\nEnter[1 , 2 , 3 , 4 , 5]");
                        break;
                }
            }




            //COMPUTER MENU
            if(libraryMenu == 4) {
                System.out.println("\nPlease select from the following options: ");
                System.out.println("---[COMPUTERS]---\n1. Add new computer to database\n2. Edit existing items\n3. Book a computer\n4. Check available computers\n\nEnter[1 , 2 , 3 , 4]");
                userInput = scanner.next();
                //Add switch case for class specific methods
            }
        }
    }
}
