package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int orderNumber;
    private final List<OrderItem> items;
    private final LocalDateTime orderTime;
    private final double total;
    
    public Order(int orderNumber, List<OrderItem> items) {
        if(orderNumber <= 0) {
            throw new IllegalArgumentException("Order number can not be negative or zero.");
        }
        if(items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Cart can't be empty.");
        }

        this.orderNumber = orderNumber;
        this.items = new ArrayList<>(items);
        this.orderTime = LocalDateTime.now();
        double calculatedTotal = 0;

        for(OrderItem item : items) {
            calculatedTotal += item.getSubtotal();
        }
        this.total = calculatedTotal;
    }

    public int getOrderNumber(){
        return orderNumber;
    }
    public List<OrderItem> getItems(){
        return new ArrayList<>(items);
    }
    public LocalDateTime getOrderTime(){
        return orderTime;
    }
    public double getTotal(){
        return total;
    }
}
