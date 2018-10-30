import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Booking {
    private Table table;
    private CustomLinkedList<MenuItem> order;
    private int bookingID, customerAmnt;
    private String customerName;
    private LocalDate startDate;
    private LocalDateTime endDate;
    private LocalTime startTime;
    private Duration duration;

    Booking(int bookingID, LocalDate startDate, Table table){
        this.table = table;
        this.bookingID = bookingID;
        this.startDate = startDate;
        order = new CustomLinkedList<>();
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingID + " for " + customerAmnt + " people.\nMade by: " + customerName;
    }
}
