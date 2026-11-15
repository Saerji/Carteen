package ui;

import model.CartItem;
import service.CartService;

import java.util.List;

public class CartMenu {
    private final CartService cartService;

    public CartMenu (CartService cartService){
        this.cartService = cartService;
    }

    public void displayCart(){
        List<CartItem> items = cartService.getCartItems();
        System.out.println("- - - Cart - - -");

        if(items.isEmpty()){
            System.out.println("Cart is empty.");
            return;
        }

        for (CartItem item : items) {
            System.out.println("[1] " + item.getProduct() + " x "
            + item.getQuantity() + " = ₱" + item.getSubtotal());
        }
        System.out.println("- - - - -");
        System.out.println("Total: " + cartService.getTotalPrice());
    }
}
