package ui;

import model.Shop;
import service.MenuService;

import java.util.Scanner;

public class ShopsMenu {
    Scanner sc = new Scanner(System.in);

    private final MenuService menuService;

    public ShopsMenu(MenuService menuService) {
        this.menuService = menuService;
    }

    public void displayShops(){
        System.out.println("- - - Available Shops - - -");
        int number = 1;
        for(Shop shopList : Shop.values()) {
            System.out.println("[" + number + "] " + shopList);
            number++;
        }
        System.out.print("Enter choice: ");
        int shopChoice = sc.nextInt();

        if(shopChoice < 1 || shopChoice > Shop.values().length) {
            System.out.println("Invalid shop selection!");
            return;
        }

        ProductMenu productMenu = new ProductMenu(menuService);
        productMenu.displayProducts(shopChoice);
    }
}
