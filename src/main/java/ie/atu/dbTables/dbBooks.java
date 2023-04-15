package ie.atu.dbTables;

public class dbBooks {
    public String bookColumns = "name,author,publication,rented";
    private String name;
    private String author;
    private String publication;
    private boolean rented;

    public dbBooks() {
    }

    public dbBooks(String name, String author, String publication, boolean rented) {
        this.name = name;
        this.author = author;
        this.publication = publication;
        this.rented = rented;
    }
    public void addBook(String name, String author, String publication, boolean rented) {
        //USE UPDATE CLASS TO ADD NEW BOOK TO TABLE
    }
    public void deleteBook() {
        //DELETE BOOK FROM TABLE
    }
    public void checkout() {
        //SET BOOK TO RENTED ( + assign to user?)
    }


    //GETTERS & SETTERS
    public String getBookColumns() {return bookColumns;}
    public void setBookColumns(String bookColumns) {this.bookColumns = bookColumns;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}
    public String getPublication() {return publication;}
    public void setPublication(String publication) {this.publication = publication;}
    public boolean isRented() {return rented;}
    public void setRented(boolean rented) {this.rented = rented;}
}
