package ui;

import java.util.Scanner;
import java.util.List;

import service.CartService;
import service.MenuService;

public class MainMenu {
    private final Scanner sc;
    private MenuService menuService;
    private CartService cartService;

    public MainMenu(Scanner sc, MenuService menuService, CartService cartService){
        this.sc = sc;
        this.menuService = menuService;
        this.cartService = cartService;
    }

    public int displayMenu() {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║           CANTEEN MENU           ║");
        System.out.println("╠══════════════════════════════════╣");
        System.out.println("║  Welcome User!                   ║");
        System.out.println("║                                  ║");
        System.out.println("║  [1] Add Foods to Cart           ║");
        System.out.println("║  [2] View / Edit Cart            ║");
        System.out.println("║  [3] View Cart Adding History    ║");
        System.out.println("║  [4] Exit                        ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.print("Enter your choice: ");
        
        int choice = sc.nextInt();
        return choice;
    }

    public void runMenu(){
        boolean isRunning = true;

        while(isRunning) {
            int choice = displayMenu();
            switch(choice) {
                case 1:
                    orderfood();
                    break;
                case 2:
                    viewCart();
                    break;
                case 3:
                    viewHistory();
                    break;
                case 4:
                    exitProgram();
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid selection!");
            }
        }
    }

    public void orderfood(){
        ShopsMenu shopsMenu = new ShopsMenu(menuService, cartService, sc);

        shopsMenu.displayShops();
    }

    public void viewCart(){
        CartMenu cartMenu = new CartMenu(cartService);
        cartMenu.displayCart();
    }

    public void viewHistory(){
        System.out.println("\n===== CART ADDING HISTORY =====");
        List<String> history = cartService.getAddHistory();

        if (history.isEmpty()) {
            System.out.println("No items added yet.");
        } else {
            int number = 1;
            for (String entry : history) {
                System.out.println("[" + number + "] " + entry);
                number++;
            }
        }
        System.out.println();
    }

    public void exitProgram(){
        System.out.println("Thank you for using Carteen!");
    }

}