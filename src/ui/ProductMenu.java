package ui;

import java.util.List;

import model.Product;
import model.Shop;
import service.MenuService;

public class ProductMenu {

    private MenuService menuService;

    public ProductMenu(MenuService menuService) {
        this.menuService = menuService;
    }

    public void displayProducts(Shop shop) {

        List<Product> products = menuService.getProductsByShop(shop);

        System.out.println("\n===== " + shop.name() + " MENU =====");

        for (Product product : products) {
            System.out.println(
                product.getName() + " - PHP " + product.getPrice()
            );
        }
    }
}