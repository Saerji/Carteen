import ui.*;

import java.util.Scanner;

import service.CartService;
import service.MenuService;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();
        CartService cartService = new CartService();

        MainMenu mainMenu = new MainMenu(sc, menuService, cartService);

        mainMenu.runMenu();
        sc.close();
    }
}