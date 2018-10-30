package test;

import main.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class TableTest {
    private Table table_n100ID_n100Seats;
    private Table table_p100ID_n100Seats;
    private Table table_n100ID_p5Seats;
    private Table table_p100ID_p5Seats;
    private Table table_n100ID_p100Seats;
    private Table table_p100ID_p100Seats;

    @BeforeEach
    void setUp() {
        table_n100ID_n100Seats = new Table(-100, -100) ;
        table_p100ID_n100Seats = new Table(100, -100);
        table_n100ID_p5Seats = new Table(-100, 5);
        table_p100ID_p5Seats = new Table(100, 5);
        table_n100ID_p100Seats = new Table(-100, 100);
        table_p100ID_p100Seats = new Table(100, 100);
    }

    @Test
    void getTableID() {
        assertEquals(0, table_n100ID_n100Seats.getTableID());
        assertEquals(100, table_p100ID_n100Seats.getTableID());
    }

    @Test
    void setTableID() {
        table_n100ID_n100Seats.setTableID(50);
        assertEquals(50, table_n100ID_n100Seats.getTableID());
        table_n100ID_n100Seats.setTableID(0);
        assertEquals(50, table_n100ID_n100Seats.getTableID());
        table_p100ID_n100Seats.setTableID(-100);
        assertEquals(100, table_p100ID_n100Seats.getTableID());
        table_p100ID_n100Seats.setTableID(50);
        assertEquals(50, table_p100ID_n100Seats.getTableID());
    }

    @Test
    void getNumOfSeats() {
        assertEquals(0, table_n100ID_n100Seats.getNumOfSeats());
        assertEquals(0, table_p100ID_n100Seats.getNumOfSeats());
        assertEquals(5, table_n100ID_p5Seats.getNumOfSeats());
        assertEquals(5, table_p100ID_p5Seats.getNumOfSeats());
        assertEquals(0, table_n100ID_p100Seats.getNumOfSeats());
        assertEquals(0, table_p100ID_p100Seats.getNumOfSeats());
    }

    @Test
    void setNumOfSeats() {
        table_n100ID_p5Seats.setNumOfSeats(-10);
        assertEquals(5, table_n100ID_p5Seats.getNumOfSeats());
        table_n100ID_n100Seats.setNumOfSeats(5);
        assertEquals(5, table_n100ID_n100Seats.getNumOfSeats());
        table_n100ID_n100Seats.setNumOfSeats(55);
        assertEquals(5, table_n100ID_n100Seats.getNumOfSeats());
    }
}