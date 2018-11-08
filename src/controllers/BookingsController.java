package controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ModifiableObservableListBase;
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
import main.Database;
import main.Main;
import main.Table;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingsController extends Controller{
    @FXML
    private DatePicker textfieldNewBookingDateChoice;
    @FXML
    private ComboBox<Table> comboBoxTableChoice;
    @FXML
    private ComboBox<Integer> comboBoxNewBookingTime;
    @FXML
    private Button btnNewBookingPane, btnViewBookingsPane, btnNewBookingAdd;
    @FXML
    private AnchorPane paneNewBookingForm, paneBookingView;
    @FXML
    private TextField textfieldNewBookingCustomerName,
            textfieldNewBookingSeatsRequested,
            textfieldNewBookingDuration;
    @FXML
    private TableView<Booking> bookingsTableView;
    @FXML
    private TableColumn<Booking, String> tableColumnCustomerName, tableColumnDate, tableColumnTime;
    @FXML
    private TableColumn<Booking, Integer> tableColumnCustomerAmnt, tableColumnBookingDuration;
    @FXML
    private TableColumn<Booking, Table> tableColumnTable;

    @FXML
    private void initialize(){
        comboBoxNewBookingTime.getItems().addAll(12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22);
        comboBoxTableChoice.getItems().addAll(Main.database.getTables());

        refreshTableView(bookingsTableView, Main.database.getBookings());

        tableColumnCustomerName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCustomerName()));

        tableColumnTable.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getTable()));

        tableColumnBookingDuration.setCellValueFactory(cellData->
                new SimpleIntegerProperty(cellData.getValue().getDuration()).asObject());

        tableColumnCustomerAmnt.setCellValueFactory(cellData->
                new SimpleIntegerProperty(cellData.getValue().getCustomerAmnt()).asObject());

        tableColumnDate.setCellValueFactory(cellData->
                new SimpleStringProperty(cellData.getValue().getStartDate().toString())
                );

        tableColumnTime.setCellValueFactory(cellData->
                new SimpleStringProperty(cellData.getValue().getStartTime().toString()));

    }

    @FXML
    private void handleButtonAction(ActionEvent actionEvent) {
        if(actionEvent.getSource() == btnNewBookingPane) paneNewBookingForm.toFront();
        else if (actionEvent.getSource() == btnViewBookingsPane) {
            paneBookingView.toFront();
        }
    }

    @FXML
    private void addNewBooking(ActionEvent actionEvent) {
        Table table = comboBoxTableChoice.getSelectionModel().getSelectedItem();
        String customerName = textfieldNewBookingCustomerName.getText();
        int customerAmnt = Integer.parseInt(textfieldNewBookingSeatsRequested.getText());
        int duration = Integer.parseInt(textfieldNewBookingDuration.getText());
        LocalDate startDate = textfieldNewBookingDateChoice.getValue();
        LocalTime startTime = LocalTime.ofSecondOfDay(comboBoxNewBookingTime.getValue() * 60 * 60);

        Main.database.getBookings().add(new Booking(table, customerAmnt, customerName, startDate, startTime, duration));

    }

    @FXML
    private void tableViewContextMenuRequested(ContextMenuEvent contextMenuEvent) {
    }

    @FXML
    private void editSaveCustomerName(TableColumn.CellEditEvent cellEditEvent) {
    }
}
