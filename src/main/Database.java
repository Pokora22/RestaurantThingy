package main;

import java.io.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class Database {
    private CustomArrayList<Table> tables;
    private CustomLinkedList<Booking> bookings;
    private CustomLinkedList<MenuItem> menuItems;
    private XStream xStream;

    public Database(){
        tables = new CustomArrayList<>();
        bookings = new CustomLinkedList<>();
        menuItems = new CustomLinkedList<>();
        xStream = new XStream(new StaxDriver());
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

    public void loadDB(){
        try {
            loadBookings();
        } catch (IOException e) {
            e.printStackTrace();
            new File("/db/bookings.xml");
        }
        try {
            loadMenu();
        } catch (IOException e) {
            e.printStackTrace();
            new File("/db/menu.xml");
        }
        try {
            loadTables();
        } catch (IOException e) {
            e.printStackTrace();
            new File("/db/tables.xml");
        }
    }

    public void saveDB(){
        try {
            saveBookings();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            saveMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            saveTables();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTables() throws IOException {
        ObjectInputStream is = null;
        try {
            is = xStream.createObjectInputStream(new FileReader("tables.xml"));
            tables = (CustomArrayList<Table>) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        is.close();
    }

    private void saveTables() throws IOException {
        ObjectOutputStream out = xStream.createObjectOutputStream(new FileWriter("/db/tables.xml"));
        out.writeObject(tables);
        out.close();
    }

    private void loadMenu() throws IOException {
        ObjectInputStream is = null;
        try {
            is = xStream.createObjectInputStream(new FileReader("/db/menu.xml"));
            menuItems = (CustomLinkedList<MenuItem>) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        is.close();
    }

    private void saveMenu() throws IOException {
        ObjectOutputStream out = xStream.createObjectOutputStream(new FileWriter("/db/menu.xml"));
        out.writeObject(menuItems);
        out.close();
    }

    private void loadBookings() throws IOException {
        ObjectInputStream is = null;
        try {
            is = xStream.createObjectInputStream(new FileReader("/db/bookings.xml"));
            bookings = (CustomLinkedList<Booking>) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        is.close();
    }

    private void saveBookings() throws IOException {
        ObjectOutputStream out = xStream.createObjectOutputStream(new FileWriter("/db/bookings.xml"));
        out.writeObject(bookings);
        out.close();
    }
}
