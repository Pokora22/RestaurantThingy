package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import main.Booking;
import main.CustomArrayList;
import main.CustomLinkedList;
import main.Table;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static main.Main.*;

public class BookingsController extends Controller{
    @FXML
    private DatePicker datePickerNewBookingDateChoice, datePickerEditBookingDateChoice;
    @FXML
    private ComboBox<Table> comboBoxNewTableChoice, comboBoxEditTableChoice;
    @FXML
    private ComboBox<Integer> comboBoxNewBookingTime, comboBoxEditBookingTime, comboBoxSeatsRequestedEditBooking, comboBoxSeatsRequestedNewBooking,
            comboBoxNewBookingDuration, comboBoxEditBookingDuration;
    @FXML
    private Button btnNewBookingPane, btnViewBookingsPane;
    @FXML
    private AnchorPane paneNewBookingForm, paneBookingView, paneEditBookingForm;
    @FXML
    private TextField textfieldNewBookingCustomerName, textfieldEditBookingCustomerName;
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
        comboBoxSeatsRequestedNewBooking.getItems().addAll(seatsAvailable());
        comboBoxSeatsRequestedEditBooking.getItems().addAll(comboBoxSeatsRequestedNewBooking.getItems());
        comboBoxNewBookingDuration.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        comboBoxNewBookingDuration.getSelectionModel().selectFirst();
        comboBoxEditBookingDuration.getItems().addAll(comboBoxNewBookingDuration.getItems());
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

    private CustomLinkedList<Integer> seatsAvailable() {
        CustomLinkedList<Integer> seats = new CustomLinkedList<>();
        int max = 0;
        for(Table t: database.getTables()) if (t.getNumOfSeats() > max) max = t.getNumOfSeats();
        for(int i = 0; i <= max; i++) seats.add(i);
        return seats;
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
        Table table = comboBoxNewTableChoice.getValue();
        String customerName = textfieldNewBookingCustomerName.getText();
        int customerAmnt = comboBoxSeatsRequestedNewBooking.getValue();
        int duration = comboBoxNewBookingDuration.getValue();
        LocalDate startDate = datePickerNewBookingDateChoice.getValue();
        LocalTime startTime = LocalTime.ofSecondOfDay(comboBoxNewBookingTime.getValue() * 60 * 60);

        database.getBookings().add(new Booking(table, customerAmnt, customerName, startDate, startTime, duration));

        setFormDefault();
        refreshTableView(bookingsTableView, database.getBookings());
    }

    @FXML
    private void tableViewContextMenuRequested(ContextMenuEvent contextMenuEvent) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItemDelete = new MenuItem("Delete entry");
        MenuItem menuItemEdit = new MenuItem("Edit entry");
        SeparatorMenuItem menuSeparator = new SeparatorMenuItem();
        MenuItem menuItemOrder = new MenuItem("Edit Order");
        contextMenu.getItems().addAll(menuItemDelete, menuItemEdit, menuSeparator, menuItemOrder);

        menuItemOrder.setOnAction(event -> {
            Stage sourceStage = (Stage) ((Node)contextMenuEvent.getSource()).getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/order.fxml"));
            Parent root;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            OrderController controller = loader.getController();
            controller.setSourceScene(sourceStage.getScene());
            controller.setBooking(tableSelection());
            sourceStage.setTitle(tableSelection().toString());
            sourceStage.setScene(new Scene(root));
        });

        menuItemDelete.setOnAction(event -> {
            if (database.getBookings().remove(tableSelection())) refreshTableView(bookingsTableView, database.getBookings());
        });
        menuItemEdit.setOnAction(event -> moveToEdit());

        contextMenu.show(bookingsTableView, mouseX, mouseY);
    }

    private Booking tableSelection(){
        return bookingsTableView.getSelectionModel().getSelectedItem();
    }

    private void setFormDefault(){
        comboBoxNewBookingTime.getSelectionModel().selectFirst();
        comboBoxNewTableChoice.getSelectionModel().selectFirst();
        comboBoxSeatsRequestedNewBooking.getSelectionModel().selectFirst();
        comboBoxNewBookingDuration.getSelectionModel().selectFirst();
        datePickerNewBookingDateChoice.setValue(LocalDate.now());

        textfieldNewBookingCustomerName.clear();
    }

    @FXML
    private void saveEditBooking(ActionEvent actionEvent) {
        String name;
        tableSelection().setDuration(comboBoxNewBookingDuration.getValue());

        name = textfieldEditBookingCustomerName.getText();
        if(name.isEmpty()){
            showHint("Name should not be empty.", textfieldEditBookingCustomerName);
            return;
        }
        tableSelection().setCustomerName(name);
        tableSelection().setCustomerAmnt(comboBoxSeatsRequestedEditBooking.getValue());
        tableSelection().setStartDate(datePickerEditBookingDateChoice.getValue());
        tableSelection().setStartTime(LocalTime.ofSecondOfDay(comboBoxEditBookingTime.getValue() * 60 * 60));
        tableSelection().setTable(comboBoxEditTableChoice.getValue());

        refreshTableView(bookingsTableView, database.getBookings());
        paneBookingView.toFront();
    }

    @FXML
    private void editBookingCancel(ActionEvent actionEvent) {
        paneBookingView.toFront();
    }

    @FXML
    protected void clearBookings(ActionEvent actionEvent) {
        super.clearBookings(actionEvent);
        refreshTableView(bookingsTableView, database.getBookings());
    }

    @FXML
    private void moveToEdit() {
        paneEditBookingForm.toFront();

        datePickerEditBookingDateChoice.setValue(tableSelection().getStartDate());
        comboBoxEditBookingTime.setValue(tableSelection().getStartTime().getHour());
        comboBoxEditTableChoice.setValue(tableSelection().getTable());
        textfieldEditBookingCustomerName.setText(tableSelection().getCustomerName());
        comboBoxEditBookingDuration.setValue(tableSelection().getDuration());
        comboBoxSeatsRequestedEditBooking.setValue(tableSelection().getCustomerAmnt());
    }

    public void updateTablesAvailable(ActionEvent actionEvent) {
        int seatsNeeded = 0;
        LocalDate dayWanted;
        LocalTime startTime, endTime;
        CustomArrayList<Table> visibleTables = new CustomArrayList<>();
        ComboBox<Table> boxToUpdate = comboBoxNewTableChoice;

        if(actionEvent.getSource() == comboBoxNewBookingDuration ||
                actionEvent.getSource() == comboBoxSeatsRequestedNewBooking ||
                actionEvent.getSource() == comboBoxNewBookingTime ||
                actionEvent.getSource() == datePickerNewBookingDateChoice){
            seatsNeeded = comboBoxSeatsRequestedNewBooking.getValue();
            dayWanted = datePickerNewBookingDateChoice.getValue();
            startTime = LocalTime.ofSecondOfDay(comboBoxNewBookingTime.getValue() * 3600);
            endTime = startTime.plusHours(comboBoxNewBookingDuration.getValue());
            boxToUpdate = comboBoxNewTableChoice;
        }
        /*
        else{
            seatsNeeded = comboBoxSeatsRequestedEditBooking.getValue();
            dayWanted = datePickerEditBookingDateChoice.getValue();
            startTime = LocalTime.ofSecondOfDay(comboBoxEditBookingTime.getValue() * 3600);
            endTime = startTime.plusHours(comboBoxEditBookingDuration.getValue());
            boxToUpdate = comboBoxEditTableChoice;
        }
        //On Edit throws null pointers. Rushed job -> not sure why
        */

        for(Table table: database.getTables()){
            if (table.getNumOfSeats() >= seatsNeeded){ //too late to add more constraints.
                visibleTables.add(table);
            }
        }
        boxToUpdate.getItems().clear();
        boxToUpdate.getItems().addAll(visibleTables);
        boxToUpdate.getSelectionModel().selectFirst();
    }
}
