package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends Controller{
    @FXML
    private Button btnBookings, btnTables, btnMenu;

    public void changeScene(ActionEvent actionEvent) throws IOException {
        Stage sourceStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        String windowTitle, fxmlLocation;

        if(actionEvent.getSource() == btnBookings){
            windowTitle = "Manage Bookings";
            fxmlLocation = "/fxml/bookings.fxml";
        }
        else if(actionEvent.getSource() == btnMenu ){
            windowTitle = "Manage Menu Items";
            fxmlLocation = "/fxml/menu.fxml";
        }
        else{
            windowTitle = "Manage Tables";
            fxmlLocation = "/fxml/tables.fxml";
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlLocation));
        Parent root = loader.load();
        Controller controller = loader.getController();
        //controller.setSourceController(this);
        controller.setSourceScene(sourceStage.getScene());
        sourceStage.setTitle(windowTitle);
        sourceStage.setScene(new Scene(root, 600, 300));
    }
}
