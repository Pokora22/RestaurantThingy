public class Table {
    private int tableID, numOfSeats;

    public Table(int tableID, int numOfSeats) {
        this.tableID = tableID;
        this.numOfSeats = numOfSeats;
    }

    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public String toString(){
        return "Table no: " + getTableID() + " Seats: " + getNumOfSeats();
    }


    public boolean equals(Table table){
        return (this.getTableID() == table.getTableID() && this.getNumOfSeats() == table.getNumOfSeats());
    }
}
