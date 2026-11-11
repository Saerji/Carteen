package model;

public class Product {
    private final String name;
    private final double price;

    public Product(String name, double price, FoodCategory category, Shop shop) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can't be blank");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price can't be negative nor zero.");
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