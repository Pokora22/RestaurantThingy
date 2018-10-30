package main;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Booking {
    private Table table;
    private CustomLinkedList<MenuItem> order;
    private int customerAmnt;
    private String customerName;
    private LocalDate startDate;
    private LocalTime startTime;
    private int duration;

    Booking(Table table, int customerAmnt, String customerName, LocalDate startDate, LocalTime startTime, int duration){
        this.table = table != null? table: new Table(0, 0);
        this.customerAmnt = customerAmnt > 0? customerAmnt: 0;
        this.customerName = (customerName != null && !customerName.isEmpty())? customerName : "CUSTOMER NAME WAS OMITTED";
        this.startDate = startDate != null && startDate.isAfter(LocalDate.now().minusDays(1))? startDate : LocalDate.EPOCH;
        this.startTime = startTime != null && startTime.isAfter(LocalTime.now().minusHours(1))? startTime: LocalTime.MIDNIGHT;
        this.duration = duration > 0? duration: 0;
        order = new CustomLinkedList<>();
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        if(table != null) this.table = table;
    }

    public CustomLinkedList<MenuItem> getOrder() {
        return order;
    }

    public void setOrder(CustomLinkedList<MenuItem> order) {
        if(order != null) this.order = order;
    }

    public int getCustomerAmnt() {
        return customerAmnt;
    }

    public void setCustomerAmnt(int customerAmnt) {
        if(customerAmnt > 0) this.customerAmnt = customerAmnt;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        if(customerName != null && !customerName.isEmpty()) this.customerName = customerName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate != null) this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        if (startTime != null) this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if (duration > 0) this.duration = duration;
    }

    @Override
    public String toString() {
        return "Booking for " + customerAmnt + " people, at " + startDate + ":" + startTime + "for " + duration + "hours.\nMade by: " + customerName;
    }
}
