package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import main.Main;
import main.MenuItem;

public class MenuController extends Controller{
    @FXML
    private Button btnNewMenuItemPane, btnViewMenuItemsPane, btnNewMenuItemAdd;
    @FXML
    private AnchorPane paneNewMenuItemForm, paneMenuItemView;
    @FXML
    private TextField textfieldNewMenuItemName, textfieldNewMenuItemPrice;
    @FXML
    private TableView menuItemsTableView;
    @FXML
    private TableColumn tableColumnName, tableColumnPrice;

    @FXML
    private void handleButtonAction(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnNewMenuItemPane) paneNewMenuItemForm.toFront();
        else if (actionEvent.getSource() == btnViewMenuItemsPane) {
            paneMenuItemView.toFront();

        }

    }

    @FXML
    private void addNewMenuItem(ActionEvent actionEvent) {
        String name;
        double price;

        name = textfieldNewMenuItemName.getText();
        if(name.isEmpty()) {
            showHint("Name cannot be empty", textfieldNewMenuItemName);
            return;
        }

        try{
            price = Double.valueOf(textfieldNewMenuItemPrice.getText());
        }
        catch(Exception e){
            showHint("Field needs to be a number", textfieldNewMenuItemPrice);
            return;
        }

        Main.database.getMenuItems().add(new MenuItem(name, price));
        textfieldNewMenuItemName.clear();
        textfieldNewMenuItemPrice.clear();
    }

    @FXML
    private void tableViewContextMenuRequested(ContextMenuEvent contextMenuEvent) {
    }
    @FXML
    private void editSaveName(TableColumn.CellEditEvent cellEditEvent) {
    }
    @FXML
    private void editSavePrice(TableColumn.CellEditEvent cellEditEvent) {
    }
}
