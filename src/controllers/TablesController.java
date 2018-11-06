package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.converter.IntegerStringConverter;
import main.Main;
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
    //TODO: View tables (edit as well later) + adding the tables (with checks)

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
                    return 0; //allow for -1 to spot mistakes easier ?
                }
            }
        })); //thank you stackexchange

        tableColumnSeats.setCellValueFactory(cellData ->
        new SimpleIntegerProperty(cellData.getValue().getNumOfSeats()).asObject());
        tableColumnSeats.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter(){
            @Override
            public Integer fromString(String value) {
                try {
                    return super.fromString(value);
                } catch (Exception e) {
                    return 0;
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
    }

    @FXML
    private void editSaveSeats(TableColumn.CellEditEvent<Table, Integer> tableIntegerCellEditEvent) {
        Table table = tablesTableView.getSelectionModel().getSelectedItem();
        table.setNumOfSeats(tableIntegerCellEditEvent.getNewValue());
    }
}

