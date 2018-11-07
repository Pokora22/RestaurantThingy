package controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
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
    private TableView<MenuItem> menuItemsTableView;
    @FXML
    private TableColumn<MenuItem, String> tableColumnName;
    @FXML
    private TableColumn<MenuItem, Double> tableColumnPrice;

    @FXML
    private void initialize(){
        refreshTableView(menuItemsTableView, Main.database.getMenuItems());

        tableColumnName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getName()));
        tableColumnName.setCellFactory(TextFieldTableCell.forTableColumn());

        tableColumnPrice.setCellValueFactory(cellData ->
                new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        tableColumnPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter(){
            @Override
            public Double fromString(String value) {
                try {
                    return super.fromString(value);
                } catch (Exception e) {
                    showHint("Field needs to be a positive number.", tableColumnPrice.getStyleableNode());
                    return -1.0;
                }
            }
        }));
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

        Main.database.getMenuItems().add(new MenuItem(name, price));
        refreshTableView(menuItemsTableView, Main.database.getMenuItems());

        textfieldNewMenuItemName.clear();
        textfieldNewMenuItemPrice.clear();
    }

    @FXML
    private void tableViewContextMenuRequested(ContextMenuEvent contextMenuEvent) {
        super.tableViewContextMenuRequested(menuItemsTableView, Main.database.getMenuItems());
        //TODO: Add event action to delete
    }

    @FXML
    private void editSaveName(TableColumn.CellEditEvent<MenuItem, String> cellEditEvent) {
        MenuItem item = menuItemsTableView.getSelectionModel().getSelectedItem();
        item.setName(cellEditEvent.getNewValue());

        refreshTableView(menuItemsTableView ,Main.database.getMenuItems());
    }

    @FXML
    private void editSavePrice(TableColumn.CellEditEvent<MenuItem, Double> cellEditEvent) {
        MenuItem item = menuItemsTableView.getSelectionModel().getSelectedItem();
        item.setPrice(cellEditEvent.getNewValue());

        refreshTableView(menuItemsTableView ,Main.database.getMenuItems());
    }
}
