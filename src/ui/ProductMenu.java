package ui;

import service.MenuService;
import model.Product;
import model.Shop;

import java.util.ArrayList;
import java.util.List;

class ProductMenu {
    private final MenuService menuService;
    public ProductMenu(MenuService menuService) {
        this.menuService = menuService;
    }

    public void displayProducts(int choice) {
        Shop selectedShop = Shop.values()[choice - 1];
        List<Product> productList = menuService.getProductsByShop(selectedShop);
        for(Product product : productList) {
            System.out.println(product.getName() + " - " + product.getPrice());
        }
    }
}