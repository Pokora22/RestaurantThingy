package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;
import javafx.util.converter.IntegerStringConverter;
import main.Main;
import main.MenuItem;
import main.Table;

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
    //TODO: Maybe change edit to contextual menu ? + Deleting

    @FXML
    private void initialize(){
        refreshTableView(tablesTableView, Main.database.getTables());

        tableColumnID.setCellValueFactory(cellData ->
        new SimpleIntegerProperty(cellData.getValue().getTableID()).asObject());
        tableColumnID.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter(){
            @Override
            public Integer fromString(String value) {
                try {
                    return super.fromString(value);
                } catch (Exception e) {
                    showHint("Field needs to be a positive whole number.", tableColumnID.getStyleableNode());
                    return -1;
                }
            }
        })); //thank you stackexchange - cellfactory set to text field allows for editing in the table

        tableColumnSeats.setCellValueFactory(cellData ->
        new SimpleIntegerProperty(cellData.getValue().getNumOfSeats()).asObject());
        tableColumnSeats.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter(){
            @Override
            public Integer fromString(String value) {
                try {
                    return super.fromString(value);
                } catch (Exception e) {
                    showHint("Field needs to be a whole number (0-9).", tableColumnID.getStyleableNode());
                    return -1;
                }
            }
        }));
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
    private void editSaveID(TableColumn.CellEditEvent<Table, Integer> tableIntegerCellEditEvent) {
        Table table = tablesTableView.getSelectionModel().getSelectedItem();
        table.setTableID(tableIntegerCellEditEvent.getNewValue());

        refreshTableView(tablesTableView, Main.database.getTables());
    }

    @FXML
    private void editSaveSeats(TableColumn.CellEditEvent<Table, Integer> tableIntegerCellEditEvent) {
        Table table = tablesTableView.getSelectionModel().getSelectedItem();
        table.setNumOfSeats(tableIntegerCellEditEvent.getNewValue());

        refreshTableView(tablesTableView, Main.database.getTables());
    }

    TableView tableView = tablesTableView;

    @FXML
    private void tableViewContextMenuRequested(ContextMenuEvent contextMenuEvent) {
        super.tableViewContextMenuRequested(tablesTableView, Main.database.getTables());
    }

}

