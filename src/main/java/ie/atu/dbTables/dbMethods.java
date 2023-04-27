package ie.atu.dbTables;

public interface dbMethods {
    void addItem();
    void editItem(String columnToChange, String newInfo, String refColumn, String refID);
    void deleteItem(String refColumn,String refID);
}
