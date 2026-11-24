package ui;

import service.HistoryService;

import java.time.format.DateTimeFormatter;
import java.util.List;

import model.Order;
import model.OrderItem;

public class HistoryMenu {
    private final HistoryService historyService;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy hh:mm a");

    public HistoryMenu(HistoryService historyService) {
        this.historyService = historyService;
    }

    public void displayHistory() {
        List<Order> orders = historyService.getOrders();
        if(orders.isEmpty()) {
            System.out.println("No checkouts have been made yet.");
            return;
        }

        for(Order order : orders) {
            System.out.println("========== Order #" + order.getOrderNumber() + "==========");
            System.out.println("Date: " + order.getOrderTime().format(formatter));
            for(OrderItem item : order.getItems()) {
                System.out.printf("%s x %d     ₱%.2f%n",
                    item.getProductName(),
                    item.getQuantity(),
                    item.getSubtotal()
                );
            }
            System.out.printf("Total: ₱%.2f%n", order.getTotal());
            System.out.println();
            System.out.println("====================");
        }
    }

}
