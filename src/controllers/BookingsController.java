package controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;
import main.Booking;
import main.Main;
import main.Table;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingsController extends Controller{
    @FXML
    private DatePicker textfieldNewBookingDateChosen;
    @FXML
    private ComboBox textfieldNewBookingTableChosen;
    @FXML
    private Button btnNewBookingPane, btnViewBookingsPane, btnNewBookingAdd;
    @FXML
    private AnchorPane paneNewBookingForm, paneBookingView;
    @FXML
    private TextField textfieldNewBookingCustomerName,
            textfieldNewBookingSeatsRequested,
            textfieldNewBookingDuration,
            textfieldNewBookingTime;
    @FXML
    private TableView<Booking> bookingsTableView;
    @FXML
    private TableColumn<Booking, String> tableColumnCustomerName;
    @FXML
    private TableColumn<Booking, Integer> tableColumnCustomerAmnt, tableColumnBookingDuration;
    @FXML
    private TableColumn<Booking, Table> tableColumnTable;
    @FXML
    private TableColumn<Booking, LocalDate> tableColumnDate;
    @FXML
    private TableColumn<Booking, LocalTime> tableColumnTime;

    @FXML
    private void initialize(){
        refreshTableView(bookingsTableView, Main.database.getMenuItems());

        tableColumnCustomerName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCustomerName()));
        tableColumnCustomerName.setCellFactory(TextFieldTableCell.forTableColumn());

        tableColumnTable.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getTable()));
        tableColumnTable.setCellFactory(ComboBoxTableCell.forTableColumn()); //TODO: Add items to the combobox
    }

    @FXML
    private void handleButtonAction(ActionEvent actionEvent) {
    }

    @FXML
    private void addNewBooking(ActionEvent actionEvent) {
    }

    @FXML
    private void tableViewContextMenuRequested(ContextMenuEvent contextMenuEvent) {
    }

    @FXML
    private void editSaveCustomerName(TableColumn.CellEditEvent cellEditEvent) {
    }
}
