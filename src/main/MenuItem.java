package main;

public class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price){
        this.name = name != null && !name.isEmpty()? name: "ITEM NAME WAS NOT GIVEN";
        this.price = price >= 0? ((int)price * 100)/100: 0;
    }
}
