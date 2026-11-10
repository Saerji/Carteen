package model;

public class Meal {
    private final String name;
    private final double price;

    public Meal(String name, double price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can't be blank");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be negative");
        }
        this.name = name;
        this.price = price;
    }

    public String getName()  { 
        return name; 
    }
    public double getPrice() { 
        return price; 
    }
}