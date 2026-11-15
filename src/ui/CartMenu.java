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
        int number = 1;
        System.out.println("- - - Cart - - -");

        if(items.isEmpty()){
            System.out.println("Cart is empty.");
            return;
        }

        for (CartItem item : items) {
            System.out.println("[" + number + "]: " + item.getProduct().getName() + " x "
            + item.getQuantity() + " = ₱" + item.getSubtotal());
            number++;
        }
        System.out.println("- - - - -");
        System.out.println("Total: " + cartService.getTotalPrice());
    }

    public void removeProduct(int choice){
        List<CartItem> items = cartService.getCartItems();

        if(choice < 1 || choice > items.size()) {
            System.out.println("Invalid product selection.");
            return;
        }
        cartService.removeProduct(items.get(choice - 1).getProduct());
    }
}
