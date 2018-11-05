package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main extends Application {
    public static Database database;

    @Override
    public void start(Stage primaryStage) throws Exception{
        database = new Database();

        //Some testing - shits broken
        database.getTables().add(new Table(1, 1));
        database.getTables().add(new Table(2, 2));
        database.getTables().add(new Table(3, 3));


        database.getBookings().add(new Booking(database.getTables().get(0), 1, "name", LocalDate.now(), LocalTime.now(), 1));
        database.getBookings().add(new Booking(database.getTables().get(0), 2, "name", LocalDate.now(), LocalTime.now(), 1));
        database.getBookings().add(new Booking(database.getTables().get(0), 3, "name", LocalDate.now(), LocalTime.now(), 1));
        database.getBookings().add(new Booking(database.getTables().get(0), 4, "name", LocalDate.now(), LocalTime.now(), 1));

        database.getBookings().forEach(e-> e.setDuration(2));
        database.getTables().forEach(e-> e.setNumOfSeats(5));


        System.out.println(database.getTables().size());
        System.out.println(database.getTables());
        System.out.println(database.getBookings().size());
        System.out.println(database.getBookings());

        /////////////

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainWindow.fxml"));
        primaryStage.setTitle("Restaurant Management");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
