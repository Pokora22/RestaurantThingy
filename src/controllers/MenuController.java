package controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import main.Main;
import main.MenuItem;

import static main.Main.database;

public class MenuController extends Controller{
    @FXML
    private Button btnNewMenuItemPane, btnViewMenuItemsPane, btnCancelEdit, btnSaveEdit;
    @FXML
    private AnchorPane paneNewMenuItemForm, paneMenuItemView, paneEditMenuItem;
    @FXML
    private TextField textfieldNewMenuItemName, textfieldNewMenuItemPrice, textfieldEditMenuItemPrice, textfieldEditMenuItemName;
    @FXML
    private TableView<MenuItem> menuItemsTableView;
    @FXML
    private TableColumn<MenuItem, String> tableColumnName;
    @FXML
    private TableColumn<MenuItem, Double> tableColumnPrice;

    @FXML
    private void initialize(){
        refreshTableView(menuItemsTableView, database.getMenuItems());

        tableColumnName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));

        tableColumnPrice.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
    }

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

        database.getMenuItems().add(new MenuItem(name, price));
        refreshTableView(menuItemsTableView, database.getMenuItems());

        textfieldNewMenuItemName.clear();
        textfieldNewMenuItemPrice.clear();
    }

    @FXML
    private void tableViewContextMenuRequested(ContextMenuEvent contextMenuEvent) {
        ContextMenu contextMenu = new ContextMenu();
        javafx.scene.control.MenuItem menuItemDelete = new javafx.scene.control.MenuItem("Delete entry");
        javafx.scene.control.MenuItem menuItemEdit = new javafx.scene.control.MenuItem("Edit entry");
        contextMenu.getItems().addAll(menuItemDelete, menuItemEdit);

        menuItemDelete.setOnAction(event -> {
            if (database.getMenuItems().remove(tableSelection())) refreshTableView(menuItemsTableView, database.getMenuItems());
        });
        menuItemEdit.setOnAction(event -> moveToEdit());

        contextMenu.show(menuItemsTableView, mouseX, mouseY);
    }

    @FXML
    private void moveToEdit() {
        paneEditMenuItem.toFront();
        textfieldEditMenuItemName.setText(tableSelection().getName());
        textfieldEditMenuItemPrice.setText(String.valueOf(tableSelection().getPrice()));
    }

    private MenuItem tableSelection(){
        return menuItemsTableView.getSelectionModel().getSelectedItem();
    }

    public void saveMenuItemEdit(ActionEvent actionEvent) {
        double newPrice;
        try {
            newPrice = Double.parseDouble(textfieldEditMenuItemPrice.getText());
        }
        catch (Exception e){
            showHint("Price cannot be empty.", textfieldEditMenuItemPrice);
            return;
        }

        tableSelection().setName(textfieldEditMenuItemName.getText());
        tableSelection().setPrice(newPrice);

        refreshTableView(menuItemsTableView, database.getMenuItems());
        paneMenuItemView.toFront();
    }

    public void cancelMenuItemEdit(ActionEvent actionEvent) {
        paneMenuItemView.toFront();
    }
}
