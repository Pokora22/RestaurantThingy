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


        database.getTables().add(new Table(1, 1));
        database.getTables().add(new Table(2, 2));
        database.getTables().add(new Table(3, 3));

        /*
        database.getBookings().add(new Booking(new Table(1,1),1, "Name", LocalDate.now(), LocalTime.now(), 1));
        database.getBookings().add(new Booking(new Table(2,2),2, "Name2", LocalDate.now(), LocalTime.now(), 2));

        System.out.println(database.getBookings());
        System.out.println(database.getTables());

        for (Table t: database.getTables()) {

        }

        */
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainWindow.fxml"));
        primaryStage.setTitle("Restaurant Management");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }
}
