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
}
