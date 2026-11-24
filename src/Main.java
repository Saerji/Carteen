import ui.*;
import ui.console.MainMenu;

import java.util.Scanner;

import service.CartService;
import service.CheckoutService;
import service.HistoryService;
import service.MenuService;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();
        CartService cartService = new CartService();
        CheckoutService checkoutService = new CheckoutService(cartService);
        HistoryService historyService = new HistoryService();

        MainMenu mainMenu = new MainMenu(sc, menuService, cartService, checkoutService, historyService);

        mainMenu.runMenu();
        sc.close();
    }
}