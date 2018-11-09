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

        loadDB();
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
        if (!new File("src/db/bookings.xml").exists()) {
            try {
                saveBookings();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (!new File("src/db/menu.xml").exists()) {
            try {
                saveMenu();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        if (!new File("src/db/tables.xml").exists()) {
            try {
                saveTables();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        try {
            loadBookings();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            loadMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            loadTables();
        } catch (IOException e) {
            e.printStackTrace();
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
            is = xStream.createObjectInputStream(new FileReader("src/db/tables.xml"));
            tables = (CustomArrayList<Table>) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        is.close();
    }

    private void saveTables() throws IOException {
        ObjectOutputStream out = xStream.createObjectOutputStream(new FileWriter("src/db/tables.xml"));
        out.writeObject(tables);
        out.close();
    }

    private void loadMenu() throws IOException {
        ObjectInputStream is = null;
        try {
            is = xStream.createObjectInputStream(new FileReader("src/db/menu.xml"));
            menuItems = (CustomLinkedList<MenuItem>) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        is.close();
    }

    private void saveMenu() throws IOException {
        ObjectOutputStream out = xStream.createObjectOutputStream(new FileWriter("src/db/menu.xml"));
        out.writeObject(menuItems);
        out.close();
    }

    private void loadBookings() throws IOException {
        ObjectInputStream is = null;
        try {
            is = xStream.createObjectInputStream(new FileReader("src/db/bookings.xml"));
            bookings = (CustomLinkedList<Booking>) is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        is.close();
    }

    private void saveBookings() throws IOException {
        ObjectOutputStream out = xStream.createObjectOutputStream(new FileWriter("src/db/bookings.xml"));
        out.writeObject(bookings);
        out.close();
    }
}
