package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import main.Booking;
import main.Table;

import java.time.LocalDate;
import java.time.LocalTime;

import static main.Main.*;

public class BookingsController extends Controller{
    @FXML
    private DatePicker datePickerNewBookingDateChoice, datePickerEditBookingDateChoice;
    @FXML
    private ComboBox<Table> comboBoxNewTableChoice, comboBoxEditTableChoice;
    @FXML
    private ComboBox<Integer> comboBoxNewBookingTime, comboBoxEditBookingTime;
    @FXML
    private Button btnNewBookingPane, btnViewBookingsPane, btnNewBookingAdd;
    @FXML
    private AnchorPane paneNewBookingForm, paneBookingView, paneEditBookingForm;
    @FXML
    private TextField textfieldNewBookingCustomerName, textfieldNewBookingSeatsRequested, textfieldEditBookingDuration,
            textfieldNewBookingDuration, textfieldEditBookingCustomerName, textfieldEditBookingSeatsRequested;
    @FXML
    private TableView<Booking> bookingsTableView;
    @FXML
    private TableColumn<Booking, String> tableColumnCustomerName, tableColumnDate, tableColumnTime, tableColumnBookingEndTime;
    @FXML
    private TableColumn<Booking, Integer> tableColumnCustomerAmnt, tableColumnBookingDuration;
    @FXML
    private TableColumn<Booking, Table> tableColumnTable;

    @FXML
    private void initialize(){
        comboBoxNewBookingTime.getItems().addAll(12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22);
        comboBoxEditBookingTime.getItems().addAll(comboBoxNewBookingTime.getItems());
        comboBoxNewTableChoice.getItems().addAll(database.getTables());
        comboBoxEditTableChoice.getItems().addAll(database.getTables());
        setFormDefault();

        refreshTableView(bookingsTableView, database.getBookings());

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

        tableColumnBookingEndTime.setCellValueFactory(cellData->
                new SimpleStringProperty(cellData.getValue().getEndTime().toString()));
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
        Table table = comboBoxNewTableChoice.getSelectionModel().getSelectedItem();
        String customerName = textfieldNewBookingCustomerName.getText();
        int customerAmnt = Integer.parseInt(textfieldNewBookingSeatsRequested.getText());
        int duration = Integer.parseInt(textfieldNewBookingDuration.getText()); //TODO: All the exceptions + check why time and date turn out as epoch
        LocalDate startDate = datePickerNewBookingDateChoice.getValue();
        LocalTime startTime = LocalTime.ofSecondOfDay(comboBoxNewBookingTime.getValue() * 60 * 60);
        System.out.println(startTime);
        System.out.println(startDate);
        System.out.println(comboBoxNewBookingTime.getValue());

        database.getBookings().add(new Booking(table, customerAmnt, customerName, startDate, startTime, duration));

        setFormDefault();
        refreshTableView(bookingsTableView, database.getBookings());
    }

    @FXML
    private void tableViewContextMenuRequested(ContextMenuEvent contextMenuEvent) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItemDelete = new MenuItem("Delete entry");
        MenuItem menuItemEdit = new MenuItem("Edit entry");
        contextMenu.getItems().addAll(menuItemDelete, menuItemEdit);

        menuItemDelete.setOnAction(event -> {
            if (database.getBookings().remove(tableSelection())) refreshTableView(bookingsTableView, database.getBookings());
        });
        menuItemEdit.setOnAction(event -> moveToEdit());

        contextMenu.show(bookingsTableView, mouseX, mouseY);
    }

    @FXML
    private void editSaveCustomerName(TableColumn.CellEditEvent cellEditEvent) {
    }

    private Booking tableSelection(){
        return bookingsTableView.getSelectionModel().getSelectedItem();
    }

    private void setFormDefault(){
        comboBoxNewBookingTime.getSelectionModel().selectFirst();
        comboBoxNewTableChoice.getSelectionModel().selectFirst();
        datePickerNewBookingDateChoice.setValue(LocalDate.now());

        textfieldNewBookingCustomerName.clear();
        textfieldNewBookingDuration.clear();
        textfieldNewBookingSeatsRequested.clear();
    }

    @FXML
    private void saveEditBooking(ActionEvent actionEvent) {

    }

    @FXML
    private void editBookingCancel(ActionEvent actionEvent) {
        paneBookingView.toFront();
    }

    @FXML
    private void moveToEdit() {
        paneEditBookingForm.toFront();

        datePickerEditBookingDateChoice.setValue(tableSelection().getStartDate());
        comboBoxEditBookingTime.setValue(tableSelection().getStartTime().getHour());
        comboBoxEditTableChoice.setValue(tableSelection().getTable());
        textfieldEditBookingCustomerName.setText(tableSelection().getCustomerName());
        textfieldEditBookingDuration.setText(String.valueOf(tableSelection().getDuration()));
        textfieldEditBookingSeatsRequested.setText(String.valueOf(tableSelection().getCustomerAmnt()));

        /*
        comboBoxEditBookingTime.getSelectionModel().select(comboBoxEditBookingTime.getItems().indexOf(tableSelection().getStartTime().getHour()));
        comboBoxEditTableChoice.getSelectionModel().select(comboBoxEditTableChoice.getItems().indexOf(tableSelection().getTable()));
         */
    }
}
