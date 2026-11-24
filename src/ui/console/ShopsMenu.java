
package ui.console;

import model.Shop;
import service.CartService;
import service.MenuService;

import java.util.Scanner;

public class ShopsMenu {
    private final Scanner sc;
    private final MenuService menuService;
    private final CartService cartService;

    // Center alignment
    private static final int WIDTH = 100;

    public ShopsMenu(MenuService menuService, CartService cartService, Scanner sc) {
        this.sc = sc;
        this.menuService = menuService;
        this.cartService = cartService;
    }
    private String center(String text) {
        int padding = (WIDTH - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text;
    }

    public void displayShops(){

        System.out.println();

        // ASCII ART TITLE
        System.out.println(center("▄█████  ▄▄▄  ▄▄▄▄  ▄▄▄▄▄▄ ▄▄▄▄▄ ▄▄▄▄▄ ▄▄  ▄▄   ▄█████ ▄████▄ █████▄  ██████ ██████ ██████ ███  ██"));
        System.out.println(center("██     ██▀██ ██▄█▄   ██   ██▄▄  ██▄▄  ███▄██   ██     ██▄▄██ ██▄▄██▄   ██   ██▄▄   ██▄▄   ██ ▀▄██"));
        System.out.println(center("▀█████ ██▀██ ██ ██   ██   ██▄▄▄ ██▄▄▄ ██ ▀██   ▀█████ ██  ██ ██   ██   ██   ██▄▄▄▄ ██▄▄▄▄ ██   ██"));

        System.out.println();
        System.out.println(center("=================================================="));
        System.out.println(center("AVAILABLE SHOPS"));
        System.out.println(center("=================================================="));
        System.out.println();

        Shop[] shops = Shop.values();

        // Display choices in two columns
        for(int i = 0; i < shops.length; i += 2) {

            String left = String.format("[ %d ] %-25s",
                    i + 1,
                    shops[i].toString().replace("_", " "));

            String right = "";

            if(i + 1 < shops.length) {
                right = String.format("[ %d ] %-25s",
                        i + 2,
                        shops[i + 1].toString().replace("_", " "));
            }

            System.out.println(center(left + "          " + right));
            System.out.println();
        }

        System.out.println(center("--------------------------------------------------"));
        System.out.println(center("[ 0 ] BACK TO MAIN MENU"));
        System.out.println(center("--------------------------------------------------"));

        System.out.println();
        System.out.print(center("ENTER YOUR CHOICE: "));

        int shopChoice = sc.nextInt();

        if(shopChoice == 0) {
            return;
        }

        if(shopChoice < 1 || shopChoice > shops.length) {
            System.out.println(center("Invalid shop selection!"));
            return;
        }

        ProductMenu productMenu = new ProductMenu(
            this.menuService,
            this.cartService,
            this.sc
        );

        productMenu.displayProducts(shopChoice);
    }
}
