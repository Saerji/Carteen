package model;

public class CartItem {
    private final Product cartProduct;
    private int quantity;

    public CartItem (Product cartProduct, int quantity){
        this.cartProduct = cartProduct;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return cartProduct;
    }
    public int getQuantity(){
        return quantity;
    }

    public int increaseQuantity(){
        return quantity++;
    }

    public double getSubtotal(){
        return cartProduct.getPrice() * quantity;
    }
}
