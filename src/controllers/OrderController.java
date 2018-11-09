package controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import main.Booking;
import main.CustomLinkedList;
import main.MenuItem;

import static main.Main.database;

public class OrderController extends Controller{
    private Booking activeBooking;
    private CustomLinkedList<MenuItem> order;

    @FXML
    private Label orderForBookingLabel;
    @FXML
    private TableView<MenuItem> orderTableView;
    @FXML
    private TableColumn<MenuItem, String> tableColumnOrderItemName;
    @FXML
    private TableColumn<MenuItem, Double>  tableColumnOrderItemCost;
    @FXML
    private AnchorPane anchorPaneEditItem, anchorPaneNewItem;
    @FXML
    private ComboBox<MenuItem> comboBoxItemChoice, comboBoxItemChoiceEdit;
    @FXML
    private TextField textfieldOrderItemQuantity, textfieldOrderItemQuantityEdit;

    @FXML
    private void initialize() {
        tableColumnOrderItemName.setCellValueFactory(cellData->
                new SimpleStringProperty(cellData.getValue().getName()));
        tableColumnOrderItemCost.setCellValueFactory(cellData->
                new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());

        comboBoxItemChoice.getItems().addAll(database.getMenuItems());
        comboBoxItemChoice.getSelectionModel().selectFirst();
        comboBoxItemChoiceEdit.getItems().addAll(database.getMenuItems());

    }

    private double getQuant(MenuItem item) {
        double quant = 0;
        for(MenuItem t: order){
            if (t.equals(item)) quant++;
        }
        return quant;
    }

    @FXML
    private void addItemToOrder(ActionEvent actionEvent) {
        int quant;
        try{
            quant = Integer.parseInt(textfieldOrderItemQuantity.getText());
        }
        catch (Exception e){
            showHint("Quantity can not be empty.", textfieldOrderItemQuantity);
            return;
        }
        MenuItem toAdd = comboBoxItemChoice.getSelectionModel().getSelectedItem();
        for(int i = 0; i < quant; i++) order.add(toAdd);

        refreshTableView(orderTableView, order);
    }

    @FXML
    private void checkout(ActionEvent actionEvent) {
        double total = 0;
        for(MenuItem item: order) total += item.getPrice();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Total to be paid: " + total);
        alert.showAndWait();
    }

    @FXML
    private void moveToEdit() {
        anchorPaneEditItem.toFront();
        comboBoxItemChoiceEdit.getSelectionModel().select(tableSelection());
        textfieldOrderItemQuantityEdit.setText(String.valueOf(tableSelection().getPrice()));
    }

    @FXML
    private void saveEdit(ActionEvent actionEvent) {
        MenuItem oldItem = tableSelection();
        int quant;
        try{
            quant = Integer.parseInt(textfieldOrderItemQuantityEdit.getText());
        }
        catch (Exception e){
            showHint("Quantity cannot be empty.", textfieldOrderItemQuantityEdit);
            return;
        }
        if (quant < 0){
            showHint("Quantity cannot be negative.", textfieldOrderItemQuantityEdit);
            return;
        }

        MenuItem newItem = tableSelection();
        for(MenuItem menuItem: order) if (menuItem.equals(oldItem)) order.remove(menuItem);
        for (int i = 0; i < quant; i++) order.add(newItem);

        refreshTableView(orderTableView, order);
        anchorPaneNewItem.toFront();
    }

    @FXML
    private void cancelEdit(ActionEvent actionEvent) {
        anchorPaneNewItem.toFront();
    }

    @FXML
    private void tableViewContextMenuRequested(ContextMenuEvent contextMenuEvent) {
        ContextMenu contextMenu = new ContextMenu();
        javafx.scene.control.MenuItem menuItemDelete = new javafx.scene.control.MenuItem("Delete entry");
        javafx.scene.control.MenuItem menuItemEdit = new javafx.scene.control.MenuItem("Edit entry");
        contextMenu.getItems().addAll(menuItemDelete, menuItemEdit);

        menuItemDelete.setOnAction(event -> {
            if (order.remove(tableSelection())) refreshTableView(orderTableView, order);
        });
        menuItemEdit.setOnAction(event -> moveToEdit());

        contextMenu.show(orderTableView, mouseX, mouseY);
    }

    private MenuItem tableSelection(){
        return orderTableView.getSelectionModel().getSelectedItem();
    }

    public void setBooking(Booking booking) {
        this.activeBooking = booking;
        order = booking.getOrder();
        orderForBookingLabel.setText(booking.toString());
        refreshTableView(orderTableView, order);
    }

    /*

    @FXML


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
