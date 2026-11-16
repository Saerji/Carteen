
package service;

import model.CartItem;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartService {

    private final List<CartItem> cartItems = new ArrayList<>();

    public void addProduct(Product product) {
        if(product == null) {
            throw new IllegalArgumentException("Product can't be null.");
        }

        for(CartItem item : cartItems) {
            if(item.getProduct() == product) {
                item.increaseQuantity();
                return;
            }
        }
        cartItems.add(new CartItem(product, 1));
    }

    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    public double getTotalPrice() {
        double total = 0;

        for(CartItem item : cartItems) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void removeProduct(Product product) {
        if(product == null) {
            throw new IllegalArgumentException("Product can't be null.");
        }

        for(int i = 0; i < cartItems.size(); i++) {
            CartItem item = cartItems.get(i);
            if(item.getProduct() == product) {
                cartItems.remove(i);
                return;
            }
        }
    }

    private final List<String> addHistory = new ArrayList<>();

    public void logAdd(Product product) {
        this.addHistory.add(product.getName() + " - PHP " + product.getPrice());
    }

    public List<String> getAddHistory() {
        return new ArrayList<>(this.addHistory);
    }
}
