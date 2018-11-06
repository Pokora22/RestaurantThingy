package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import main.Main;
import main.Table;

import java.time.Duration;

public class TablesController extends Controller{
    @FXML
    private TableColumn<Table, Integer> tableColumnID, tableColumnSeats;
    @FXML
    private AnchorPane paneNewTableForm, paneTableView;
    @FXML
    private Button btnNewTablePane, btnViewTablesPane;
    @FXML
    private TableView<Table> tablesTableView;
    @FXML
    private TextField textfieldNewTableID, textfieldNewTableNoSeats;
    //TODO: View tables (edit as well later) + adding the tables (with checks)

    @FXML
    private void initialize(){
        refreshTableView(tablesTableView, Main.database.getTables());
        TableColumn<Table, Integer> columnTableID = new TableColumn<>("Table ID coded");
        TableColumn<Table, Integer> columnTableSeats = new TableColumn<>("Table seats coded");

        tableColumnID.setCellValueFactory(cellData ->
        new SimpleIntegerProperty(cellData.getValue().getTableID()).asObject());
        tableColumnSeats.setCellValueFactory(cellData ->
        new SimpleIntegerProperty(cellData.getValue().getNumOfSeats()).asObject());


        columnTableID.setCellValueFactory(cellData ->{
            SimpleObjectProperty<Integer> property = new SimpleObjectProperty<>();
            property.setValue(cellData.getValue().getTableID());
            return property;
        });


        columnTableSeats.setCellValueFactory(cellData ->{
            SimpleObjectProperty<Integer> property = new SimpleObjectProperty<>();
            property.set(cellData.getValue().getNumOfSeats());
            return property;
        });

        //tablesTableView.getColumns().clear();
        tablesTableView.getColumns().addAll(columnTableID, columnTableSeats);
        System.out.println(columnTableID.isEditable());
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
        refreshTableView(tablesTableView, Main.database.getTables());

        textfieldNewTableID.clear();
        textfieldNewTableNoSeats.clear();
    }

    @FXML
    private void editSave(TableColumn.CellEditEvent<Table, Integer> cellEditEvent) {
        Table table = tablesTableView.getSelectionModel().getSelectedItem(); //unsafe casting ?
        System.out.println(table);
    }
}

