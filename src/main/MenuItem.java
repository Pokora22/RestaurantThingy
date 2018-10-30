package main;

public class MenuItem {
    private String name;
    private double price;

    public MenuItem(String name, double price){
        this.name = name != null && !name.isEmpty()? name: "ITEM NAME WAS NOT GIVEN";
        this.price = price >= 0? ((int)price * 100)/100: 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) this.price = price;
    }
}
