package service;

import model.Order;
import model.OrderItem;
import model.CartItem;
import java.util.ArrayList;
import java.util.List;

public class CheckoutService {
    private final CartService cartService;
    private int nextOrderNumber;

    public CheckoutService (CartService cartService) {
        if (cartService == null) {
            throw new IllegalArgumentException("Cart can't be empty.");
        }
        this.cartService = cartService;
        this.nextOrderNumber = nextOrderNumber;
    }

    public Order checkout() {
        List<CartItem> cartItems = cartService.getCartItems();
        List<OrderItem> orderItems = new ArrayList<>();

        if(cartItems.isEmpty()) {
            return null;
        }

        for(CartItem item : cartItems) {

            OrderItem orderItem = new OrderItem(item.getProduct(), item.getQuantity());
            orderItems.add(orderItem);
        }

        Order order = new Order(nextOrderNumber, orderItems);
        nextOrderNumber++;
        cartService.clearCart();
        
        return order;
    }
}
