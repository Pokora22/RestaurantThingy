package controllers;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import main.Main;
import main.Table;

import java.time.Duration;

public class TablesController extends Controller{
    @FXML
    private TableColumn tableColumnID, tableColumnSeats;
    @FXML
    private AnchorPane paneNewTableForm, paneTableView;
    @FXML
    private Button btnNewTablePane, btnViewTablesPane;
    @FXML
    private TableView tablesTableView;
    @FXML
    private TextField textfieldNewTableID, textfieldNewTableNoSeats;
    //TODO: View tables (edit as well later) + adding the tables (with checks)

    @FXML
    private void initialize(){
        refreshTable();
        tableColumnID.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Table, Integer>,
                ObservableValue<Integer>>) cellData ->
                new SimpleIntegerProperty(cellData.getValue().getTableID()).asObject());
        tableColumnSeats.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Table, Integer>,
                ObservableValue<Integer>>) cellData ->
                new SimpleIntegerProperty(cellData.getValue().getNumOfSeats()).asObject());
    }

    @FXML
    private void handleButtonAction(ActionEvent actionEvent){
        if(actionEvent.getSource() == btnNewTablePane) paneNewTableForm.toFront();
        else if (actionEvent.getSource() == btnViewTablesPane) {
            paneTableView.toFront();

        }
    }

    public void addNewTable(ActionEvent actionEvent) {
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

        Main.database.getTables().add(new Table(tableID, tableSeats));
        refreshTable();

        textfieldNewTableID.clear();
        textfieldNewTableNoSeats.clear();

    }

    private void refreshTable(){
        tablesTableView.getItems().clear();
        tablesTableView.getItems().addAll(Main.database.getTables()); //Another band-aid for not using a proper observable list ...
    }

}

