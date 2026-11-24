package service;

import model.Order;
import model.OrderItem;
import model.CartItem;
import java.util.ArrayList;
import java.util.List;

public class CheckoutService {
    private final CartService cartService;
    private int nextOrderNumber = 1;

    public CheckoutService (CartService cartService) {
        if (cartService == null) {
            throw new IllegalArgumentException("Cart service can't be null.");
        }
        this.cartService = cartService;
    }

    public Order createOrder() {
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
        
        return order;
    }

    public void completeCheckout() {
        nextOrderNumber++;
        cartService.clearCart();
    }
}
