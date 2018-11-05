package main;

public class Database {
    private CustomArrayList<Table> tables;
    private CustomLinkedList<Booking> bookings;
    private CustomLinkedList<MenuItem> menuItems;

    public Database(){
        tables = new CustomArrayList<>();
        bookings = new CustomLinkedList<>();
        menuItems = new CustomLinkedList<>();
    }

    public CustomArrayList<Table> getTables() {
        return tables;
    }

    public CustomLinkedList<Booking> getBookings() {
        return bookings;
    }

    public CustomLinkedList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public Table tableByID(int id){
        for (Table t: getTables()) {
            if (t.getTableID() == id)
                return t;
        }
        throw new IndexOutOfBoundsException("No table by such id");
    }
}
