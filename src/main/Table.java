package main;

public class Table {
    private int tableID, numOfSeats;

    public Table(int tableID, int numOfSeats) {
        this.tableID = tableID > 0? tableID: 0;
        this.numOfSeats = numOfSeats > 0 && numOfSeats < 9? numOfSeats: 0; //8 seat max?
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        if (tableID > 0) this.tableID = tableID;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        if (numOfSeats > 0 && numOfSeats < 9) this.numOfSeats = numOfSeats;
    }

    public String toString(){
        return "main.Table no: " + getTableID() + " Seats: " + getNumOfSeats();
    }


    public boolean equals(Table table){
        return (this.getTableID() == table.getTableID() && this.getNumOfSeats() == table.getNumOfSeats());
    }
}
