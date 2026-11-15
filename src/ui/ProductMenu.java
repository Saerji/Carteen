package ui;

import service.MenuService;
import model.Product;
import model.Shop;
import service.CartService;
import java.util.Scanner;

import java.util.List;

class ProductMenu {
    private final MenuService menuService;
    private final CartService cartService;
    Scanner sc = new Scanner(System.in);
    public ProductMenu(MenuService menuService, CartService cartService, Scanner sc) {
        this.menuService = menuService;
        this.cartService = cartService;
        this.sc = sc;
    }

    public void displayProducts(int choice) {
        Shop selectedShop = Shop.values()[choice - 1];

        List<Product> productList =
            menuService.getProductsByShop(selectedShop);

        if (productList.isEmpty()) {
            System.out.println("No products available in this shop.");
            return;
        }

        System.out.println("\n- - - Available Products - - -");

        int number = 1;

        for (Product product : productList) {
            System.out.println(
                "[" + number + "] "
                + product.getName()
                + " - ₱" + product.getPrice()
            );

            number++;
        }

        System.out.print("Enter product number to add: ");
        int productChoice = sc.nextInt();

        if (productChoice < 1 || productChoice > productList.size()) {
            System.out.println("Invalid product selection.");
            return;
        }

        Product selectedProduct = productList.get(productChoice - 1);

        cartService.addProduct(selectedProduct);

        System.out.println(
            selectedProduct.getName() + " added to cart!"
        );

    }
}