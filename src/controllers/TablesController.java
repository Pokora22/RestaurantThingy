package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import main.Main;
import main.Table;

import java.time.Duration;

public class TablesController extends Controller{
    @FXML
    private TextField textfieldNewTableID, textfieldNewTableNoSeats;
    //TODO: View tables (edit as well later) + adding the tables (with checks)

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
        textfieldNewTableID.clear();
        textfieldNewTableNoSeats.clear();
        for(Table t:Main.database.getTables()){
            System.out.println(t.toString());
        }
    }

}

