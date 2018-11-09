package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;
import main.Main;
import main.Table;

import static main.Main.database;

public class TablesController extends Controller{
    @FXML
    private TableColumn<Table, Integer> tableColumnID, tableColumnSeats;
    @FXML
    private AnchorPane paneNewTableForm, paneTableView, paneEditTableForm;
    @FXML
    private Button btnNewTablePane, btnViewTablesPane, btnSaveTableEdit, btnCancelTableEdit;
    @FXML
    private TableView<Table> tablesTableView;
    @FXML
    private TextField textfieldNewTableID, textfieldNewTableNoSeats, textfieldEditTableID, textfieldEditTableNoSeats;

    @FXML
    private void initialize(){
        refreshTableView(tablesTableView, database.getTables());

        tableColumnID.setCellValueFactory(cellData ->
        new SimpleIntegerProperty(cellData.getValue().getTableID()).asObject());

        tableColumnSeats.setCellValueFactory(cellData ->
        new SimpleIntegerProperty(cellData.getValue().getNumOfSeats()).asObject());
    }

    @FXML
    private void handleButtonAction(ActionEvent actionEvent){
        if(actionEvent.getSource() == btnNewTablePane) paneNewTableForm.toFront();
        else if (actionEvent.getSource() == btnViewTablesPane) {
            paneTableView.toFront();
        }
    }

    @FXML
    private void addNewTable(ActionEvent actionEvent) {
        int tableID, tableSeats;

        try {
            tableID = Integer.parseInt(textfieldNewTableID.getText());
        }
        catch (Exception e){
            showHint("Field needs to be a whole number.", textfieldNewTableID);
            return;
        }
        try {
            tableSeats = Integer.parseInt(textfieldNewTableNoSeats.getText());
        }
        catch (Exception e){
            showHint("Field needs to be a whole number.", textfieldNewTableNoSeats);
            return;
        }

        database.getTables().add(new Table(tableID, tableSeats));
        refreshTableView(tablesTableView, database.getTables());

        textfieldNewTableID.clear();
        textfieldNewTableNoSeats.clear();
    }

    @FXML
    private void tableViewContextMenuRequested(ContextMenuEvent contextMenuEvent) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItemDelete = new MenuItem("Delete entry");
        MenuItem menuItemEdit = new MenuItem("Edit entry");
        contextMenu.getItems().addAll(menuItemDelete, menuItemEdit);

        menuItemDelete.setOnAction(event -> {
            if (database.getTables().remove(tableSelection())) refreshTableView(tablesTableView, database.getTables());
        });
        menuItemEdit.setOnAction(event -> moveToEdit());

        contextMenu.show(tablesTableView, mouseX, mouseY);
    }

    @FXML
    private void moveToEdit(){
        paneEditTableForm.toFront();
        textfieldEditTableID.setText(String.valueOf(tableSelection().getTableID()));
        textfieldEditTableNoSeats.setText(String.valueOf(tableSelection().getNumOfSeats()));
    }

    private Table tableSelection(){
        return tablesTableView.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void saveTableEdit(ActionEvent actionEvent) {
        Table table = tableSelection();
        int newId, newSeats;
        try{
            newId = Integer.parseInt(textfieldEditTableID.getText());
        }
        catch (Exception e){
            showHint("Field must be a number.", textfieldEditTableID);
            return;
        }

        try{
            newSeats = Integer.parseInt(textfieldEditTableNoSeats.getText());
        }
        catch (Exception e){
            showHint("Field must be a number.", textfieldEditTableNoSeats);
            return;
        }

        table.setTableID(newId);
        table.setNumOfSeats(newSeats);

        refreshTableView(tablesTableView, database.getTables());
        paneTableView.toFront();
    }

    @FXML
    private void cancelTableEdit(ActionEvent actionEvent) {
        paneTableView.toFront();
    }
}

