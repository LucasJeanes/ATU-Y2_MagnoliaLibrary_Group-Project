package ie.atu.dbTables;

public interface dbMethods {
    void addItem();
    void editItem(String columnToChange, String newInfo, String refColumn, String refID);
    void deleteItem(String refColumn,String refID);
    void isAvailable(String refColumn,String refID);
    void isAvailable();
    //void purchaseItem(String refColumn,String refID);

    void purchaseItem(String refColumn, String refID, int pnum);

    public void checkout(String refColumn, String refID);

    void isStatAvailable();
}
