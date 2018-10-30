package controllers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Controller {
    private Scene sourceScene;

    public void setSourceScene(Scene sourceScene) {
        this.sourceScene = sourceScene;
    }

    public void switchToSourceScene(ActionEvent actionEvent){
        Stage sourceStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        sourceStage.setTitle("Restaurant Management");
        sourceStage.setScene(sourceScene);
    }
}
