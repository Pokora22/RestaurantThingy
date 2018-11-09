package controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import main.CustomLinkedList;
import main.MenuItem;

import static main.Main.database;

public class OrderController extends Controller{
    private CustomLinkedList<MenuItem> order;

    @FXML
    private Label orderForBookingLabel;
    @FXML
    private TableView<MenuItem> orderTableView;
    @FXML
    private TableColumn<MenuItem, String> tableColumnOrderItemName;
    @FXML
    private TableColumn<MenuItem, Double>  tableColumnOrderItemQuantity, tableColumnOrderItemCost, tableColumnOrderItemTotal;
    @FXML
    private AnchorPane anchorPaneEditItem, anchorPaneNewItem;
    @FXML
    private ComboBox comboBoxItemChoice, comboBoxItemChoiceEdit;
    @FXML
    private TextField textfieldOrderItemQuantity, textfieldOrderItemQuantityEdit;

    @FXML
    private void initialize() {
        refreshTableView(orderTableView, order);

        tableColumnOrderItemName.setCellValueFactory(cellData->
                new SimpleStringProperty(cellData.getValue().getName()));
        tableColumnOrderItemCost.setCellValueFactory(cellData->
                new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject());
        tableColumnOrderItemQuantity.setCellValueFactory(cellData->
                new SimpleDoubleProperty(getQuant(cellData.getValue())).asObject());
        tableColumnOrderItemTotal.setCellValueFactory(cellData->
                new SimpleDoubleProperty(cellData.getValue().getPrice() * getQuant(cellData.getValue())).asObject());


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

    @FXML
    private void saveEdit(ActionEvent actionEvent) {
    }

    @FXML
    private void cancelEdit(ActionEvent actionEvent) {
        anchorPaneNewItem.toFront();
    }

    private MenuItem tableSelection(){
        return orderTableView.getSelectionModel().getSelectedItem();
    }

    public void setOrder(CustomLinkedList<MenuItem> order) {
        this.order = order;
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
