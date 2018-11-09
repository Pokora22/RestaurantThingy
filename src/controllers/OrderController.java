package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;

public class OrderController extends Controller{
    @FXML
    private void initialize() {

    }

    @FXML
    private void addItemToOrder(ActionEvent actionEvent) {
    }

    @FXML
    private void checkout(ActionEvent actionEvent) {
    }

    @FXML
    private void tableViewContextMenuRequested(ContextMenuEvent contextMenuEvent) {
    }

    @FXML
    private void moveToEdit(TableColumn.CellEditEvent cellEditEvent) {
    }

    /*

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
        String name;
        try {
            tableSelection().setDuration(Integer.parseInt(textfieldEditBookingDuration.getText()));
        }
        catch (Exception e){
            showHint("Duration cannot be empty.", textfieldEditBookingDuration);
            return;
        }
        try{
            tableSelection().setCustomerAmnt(Integer.parseInt(textfieldEditBookingSeatsRequested.getText()));
        }
        catch (Exception e){
            showHint("Number of customers cannot be empty.", textfieldEditBookingSeatsRequested);
            return;
        }
        name = textfieldEditBookingCustomerName.getText();
        if(name.isEmpty()){
            showHint("Name should not be empty.", textfieldEditBookingCustomerName);
            return;
        }
        tableSelection().setCustomerName(name);
        tableSelection().setStartDate(datePickerEditBookingDateChoice.getValue());
        tableSelection().setStartTime(LocalTime.ofSecondOfDay(comboBoxEditBookingTime.getValue() * 60 * 60));
        tableSelection().setTable(comboBoxEditTableChoice.getValue());

        refreshTableView(bookingsTableView, database.getBookings());
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
    }
    */
}
