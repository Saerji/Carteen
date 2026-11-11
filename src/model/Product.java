package model;

public class Product {
    private final String name;
    private final double price;
    private final FoodCategory category;
    private final Shop shop;

    public Product(String name, double price, FoodCategory category, Shop shop) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can't be blank");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price can't be negative nor zero.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category can't be empty.");
        }
        if (shop == null) {
            throw new IllegalArgumentException("Shop can't be empty.");
        }
        this.name = name;
        this.price = price;
        this.category = category;
        this.shop = shop;
    }

    public String getName()  { 
        return name; 
    }
    public double getPrice() { 
        return price; 
    }
    public FoodCategory getCategory()  { 
        return category; 
    }
    public Shop getShop() { 
        return shop; 
    }
}