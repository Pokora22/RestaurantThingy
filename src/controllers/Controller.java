package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;
import main.Table;

import java.util.AbstractList;


public class Controller {
    private Scene sourceScene;
    protected double mouseX, mouseY;

    public void setSourceScene(Scene sourceScene) {
        if (sourceScene != null) {
            this.sourceScene = sourceScene;
        }
        else{
            Pane pane = new Pane();
            Label labelGoofed = new Label("Done goofed. This should point to previous scene");
            pane.getChildren().add(labelGoofed);
            this.sourceScene = new Scene(pane);
        }
    }

    @FXML
    private void switchToSourceScene(ActionEvent actionEvent){
        Stage sourceStage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        sourceStage.setTitle("Restaurant Management");
        sourceStage.setScene(sourceScene);
    }

    protected void showHint(String tooltipMsg, Node sourceNode){
        Tooltip tt = new Tooltip(tooltipMsg);
        tt.setAutoHide(true);
        tt.show(sourceNode.getScene().getWindow(),
                sourceNode.localToScreen(sourceNode.getTranslateX(),sourceNode.getTranslateY()).getX(),
                sourceNode.localToScreen(sourceNode.getTranslateY(), sourceNode.getTranslateY()).getY() + 25); //get height from the source node somehow - how?
    }

    protected void refreshTableView(TableView view, AbstractList list){
        view.getItems().clear();
        view.getItems().addAll(list); //Another band-aid for not using a proper observable list ...
    }

    @FXML
    protected void getMouseCoords(MouseEvent mouseEvent) {
        this.mouseX = mouseEvent.getScreenX();
        this.mouseY = mouseEvent.getScreenY();
    }

    protected void tableViewContextMenuRequested(TableView tableView, AbstractList list) {
        ContextMenu contextMenu = new ContextMenu();
        javafx.scene.control.MenuItem menuItemDelete = new javafx.scene.control.MenuItem("Delete entry");

        menuItemDelete.setOnAction(event -> {
            Object obj = tableView.getSelectionModel().getSelectedItem();
            if (list.remove(obj)) refreshTableView(tableView, list);
        });
        contextMenu.getItems().addAll(menuItemDelete);
        contextMenu.show(tableView, mouseX, mouseY);
    }
}
