package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
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
