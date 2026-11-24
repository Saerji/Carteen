package service;

import model.Order;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {
    private final List<Order> orders = new ArrayList<>();


    public void addOrder(Order order) {
        if(order == null) {
            throw new IllegalArgumentException("Order can't be null.");
        }

        orders.add(order);
    }

    public List<Order> getOrders() {
        return new ArrayList<Order>(orders);
    }
}
