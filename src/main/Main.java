package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Database database;

    @Override
    public void start(Stage primaryStage) throws Exception{
        database = new Database();

        database.getTables().add(new Table(1, 1));
        database.getTables().add(new Table(2, 2));
        database.getTables().add(new Table(3, 3));

        System.out.println("test");
        System.out.println(database.getTables());
        System.out.println("Size: " + database.getTables().size());

        for(Table t : database.getTables()){

            System.out.println(t.toString());
        }

        CustomArrayList<Table> tables2 = new CustomArrayList<>();
        tables2.add(new Table(1, 1));
        tables2.add(new Table(2, 2));
        tables2.add(new Table(3, 3));

        System.out.println(tables2.size());
        for(Object t: tables2){
            System.out.println(t); //These are not working. Why?
        }

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainWindow.fxml"));
        primaryStage.setTitle("Restaurant Management");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();


    }

    static CustomArrayList<Table> tables;
    static CustomLinkedList<Table> linkedList;

    public static void main(String[] args) {
        launch(args);
    }

    public static Table tableByID(int id){
        for (Table t: tables) {
            if (t.getTableID() == id)
                return t;
        }
        throw new IndexOutOfBoundsException("No table by such id");
    }
}
