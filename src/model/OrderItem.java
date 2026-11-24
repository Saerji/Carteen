package model;

public class OrderItem {
    private final String productName;
    private final double unitPrice;
    private final int quantity;

    public OrderItem (Product product, int quantity){
        if(product == null) {
            throw new IllegalArgumentException("Product cant be null.");
        }
        if(quantity <= 0) {
            throw new IllegalArgumentException("Quantity can't be less than or equal to zero.");
        }

        this.productName = product.getName();
        this.unitPrice = product.getPrice();
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getSubtotal() {
        return quantity * unitPrice;
    }
}
